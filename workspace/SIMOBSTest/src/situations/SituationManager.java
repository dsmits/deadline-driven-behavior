package situations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.media.Log;

import petriNets.ExclusivePetriNetSituation;
import petriNets.PedestrianDataLayer;
import petriNets.PedestrianSimDataLayer;
import petriNets.SharedPetriNetSituation;
import petriNets.SinkTransition;
import petriNets.SituationDataLayer;
import petriNets.Slot;
import petriNets.SlotConnection;
import petriNets.SourceTransition;
import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.DataLayerWriter;
import pipe.common.dataLayer.Transition;
import protobuf.ObservationMessage.Observation;
import time.IClock;
import time.StepClock;
import time.TimePlanningTools;


/**
 * @author djura SituationManager manages the connections between the Petri nets
 *         of the pedestrians and situations. It also contains methods to remove
 *         or add connections.
 */
public class SituationManager implements IPedestrianSimManager, Observer {

	private static final String BASE_PEDESTRIAN_NET_FILE = "petrinets/pedestrianNet.xml";
	private static final String LOG_FILE_PATH = "evaluation_log2.csv";
	private IClock clock;

	public enum UpdateMessage {
		SINK_FIRED, SINK_NOT_FIRED
	}

	// private List<Transition> sinks;
	// private List<Transition> sources;

	// TODO: Convert HashMaps to hashTables

	/*
	 * Considered putting situationPetriNets and pedestrianPetriNets together in
	 * one big map or list, but there are some essential differences in our
	 * pedestrian and situation Petri nets. While situation petri nets have a
	 * fixed amount of sinks and sources, pedestrian petri nets will extend or
	 * remove arcs from their base place depending in which situation they are
	 * currently in.
	 */
	private HashMap<String, SituationDataLayer> situationPetriNets;
	private HashMap<String, PedestrianDataLayer> pedestrianPetriNets;
	private HashMap<Slot, SlotConnection> connections;
	// private BiMap<Slot, Slot> connections;
	// I believe only the connections from the pedestrians to the situations are
	// important.
	private Hashtable<PedestrianDataLayer, ArrayList<SituationDataLayer>> connectedNets;
	private Set<PedestrianSimDataLayer> processedPetriNets;

	// TODO: Decide on data structure
	private ISituationCollection situations;
	private Document config;

	public SituationManager(Document config, IClock clock) {
		this.clock = clock;
		this.config = config;
		int width = getWidth(config);
		int height = getHeight(config);
		situations = new GridSituationCollection(width, height);

		situationPetriNets = new HashMap<String, SituationDataLayer>();
		// pedestrianPetriNets = new HashMap<String, PedestrianDataLayer>();
		processedPetriNets = new HashSet<PedestrianSimDataLayer>();
		connectedNets = new Hashtable<PedestrianDataLayer, ArrayList<SituationDataLayer>>();
		// this.context = context;
		connections = new HashMap<Slot, SlotConnection>();

		// loadSituations(config);
		loadPetriNetSituations(config);
		// int port = getServerPort(config);
	}

	/**
	 * getWidth retrieves the desired width of the area from the document
	 * 
	 * @param config2
	 *            is the document which contains the width information
	 * @return
	 */
	private int getWidth(Document config2) {
		Element areaNode = (Element) config.getElementsByTagName("area")
				.item(0);
		Element sizeNode = (Element) areaNode.getElementsByTagName("size")
				.item(0);
		Element widthNode = (Element) sizeNode.getElementsByTagName("width")
				.item(0);
		return Integer.parseInt(widthNode.getTextContent());

	}

	/**
	 * getHeight retrieves the desired height of the area from the document
	 * 
	 * @param config2
	 *            is the document which contains the height information
	 * @return
	 */
	private int getHeight(Document config2) {
		Element areaNode = (Element) config.getElementsByTagName("area")
				.item(0);
		Element sizeNode = (Element) areaNode.getElementsByTagName("size")
				.item(0);
		Element heightNode = (Element) sizeNode.getElementsByTagName("height")
				.item(0);
		return Integer.parseInt(heightNode.getTextContent());

	}

	/*
	 * private int getServerPort(Document config) { Element serverConfig =
	 * (Element) config.getElementsByTagName("serverConfig").item(0); String
	 * port =
	 * serverConfig.getElementsByTagName("port").item(0).getTextContent();
	 * 
	 * return Integer.parseInt(port); }
	 */
	/*
	 * Loads situations based on the class names given in situations.xml
	 */

	/**
	 * 
	 * 
	 * @param config
	 *            the DOM document parsed from the situations.xml file.
	 */
	private void loadPetriNetSituations(Document config) {
		// TODO: Switch to JAXB (or JAXP)
		NodeList situations = config.getElementsByTagName("situations").item(0)
				.getChildNodes();
		Node currentSituationNode;
		ISituation currentSituation;
		// Class<ISituation> currentSituationClass;
		String petriNetFileName;
		String situationId;
		SituationArea situationArea;
		SituationDataLayer currentPetriNet;
		// A multi petrinet is a net which is instantiated anew for every new
		// connection.
		boolean exclusivePetrinet;

		for (int i = 0; i < situations.getLength(); i++) {
			currentSituationNode = situations.item(i);
			// If statement is needed to ignore empty Text nodes
			if (currentSituationNode.getNodeType() == Node.ELEMENT_NODE) {
				petriNetFileName = getSituationPetriNet((Element) currentSituationNode);
				situationId = getSituationId((Element) currentSituationNode);
				situationArea = getSituationArea((Element) currentSituationNode);
				exclusivePetrinet = isExclusivePetriNet((Element) currentSituationNode);
				currentPetriNet = new SituationDataLayer(clock,
						petriNetFileName, !exclusivePetrinet);
				if (exclusivePetrinet) {
					currentSituation = new ExclusivePetriNetSituation(
							situationId, currentPetriNet);
				} else {
					currentSituation = new SharedPetriNetSituation(situationId,
							currentPetriNet);
				}
				currentPetriNet.addObserver(this);
				currentSituation.setArea(situationArea);

				this.situations.add(currentSituation);
			}

			// currentSituationClass = Class.forName(arg0)
		}
		// System.out.println("Situation map: " + this.situations);
		TimePlanningTools timePlanningTools = new TimePlanningTools();
		timePlanningTools.preprocessSituations(this.situations
				.getSituationList());

	}

	private boolean isExclusivePetriNet(Element currentSituationNode) {
		Element situationTypeNode = (Element) currentSituationNode
				.getElementsByTagName("situationType").item(0);
		String situationType = situationTypeNode.getTextContent();

		return situationType.equals("exclusive");
	}

	private String getSituationPetriNet(Element currentSituationNode) {
		Element className = (Element) currentSituationNode
				.getElementsByTagName("petriNetFile").item(0);
//		System.out.println("petriNetFile: " + className.getTextContent());
		return className.getTextContent();
	}

	private SituationArea getSituationArea(Element currentSituationNode) {
		Element areaNode = (Element) currentSituationNode.getElementsByTagName(
				"situationArea").item(0);
		SituationArea area = new SituationArea();
		int topLeftX = Integer.parseInt(areaNode.getElementsByTagName("x1")
				.item(0).getTextContent());
		area.setTopLeftX(topLeftX);
		int upperLeftY = Integer.parseInt(areaNode.getElementsByTagName("y1")
				.item(0).getTextContent());
		area.setTopLeftY(upperLeftY);
		int bottomRightX = Integer.parseInt(areaNode.getElementsByTagName("x2")
				.item(0).getTextContent());
		area.setBottomRightX(bottomRightX);
		int bottomRightY = Integer.parseInt(areaNode.getElementsByTagName("y2")
				.item(0).getTextContent());
		area.setBottomRightY(bottomRightY);
		return area;
	}

	private String getSituationId(Element currentSituationNode) {
		Element situationId = (Element) currentSituationNode
				.getElementsByTagName("id").item(0);
//		System.out.println("SituationId: " + situationId.getTextContent());
		return situationId.getTextContent();
	}

	private String getSituationName(Element currentSituationNode) {
		Element className = (Element) currentSituationNode
				.getElementsByTagName("className").item(0);
//		System.out.println("ClassName: " + className.getTextContent());
		return className.getTextContent();
	}

	public List<ISituation> getSituations(double locationX, double locationY) {
//		System.out.println("locationX: " + locationX + " locationY: "
				//+ locationY);
		List<ISituation> situations = this.situations.get(locationX, locationY);
		/*
		 * if(!situations.isEmpty()){ System.out.println("Situations found at ("
		 * + locationX + "," + locationY + ") : " + situations); }
		 */return situations;

	}

	public List<ISituation> getSituationList() {
		return situations.getSituationList();
	}

	@Override
	public void update(Observable dataLayer, Object arg1) {
		// arg1 can be an arbitrary variable, there is not really a standard for
		// what kind of
		// message should be sent with update unfortunately.
		if (arg1 instanceof UpdateMessage
				&& (UpdateMessage) arg1 == UpdateMessage.SINK_FIRED) {
			SinkTransition sink = (SinkTransition) ((PedestrianSimDataLayer) dataLayer)
					.getLastFiredTransition();
			updateTokenDistribution((PedestrianSimDataLayer) dataLayer, sink);
		}

	}

	// For now, I assume sources and sinks always transport only one token, so
	// when one token is
	// transported back, the situation and pedestrian can be disconnected. But
	// they should not be
	// disconnected immediately, only when the pedestrian leaves the situation.

	private void updateTokenDistribution(PedestrianSimDataLayer dataLayer,
			SinkTransition sink) {
		// if (dataLayer.sinkHasFired()) {
		transportToken(sink);
		// }
	}

	/**
	 * Transports a token that has disappeared in a sink of a Petri net to the
	 * source of another Petri net, which has been attached to the sink.
	 * 
	 * @param transition
	 *            The sink transition that has been fired.
	 */

	private void transportToken(SinkTransition transition) {

		SlotConnection connection = connections.get(transition.getSlot());
//		System.out.println("Connection: " + connection);

		// SourceTransition attachedSource = transition.getAttachedSource();
		// TODO: Very crappy way to get the attached petri net. Should be
		// possible to search through
		// the slots and find the right one.
		PedestrianSimDataLayer attachedPetriNet = connection
				.getAttachedPetriNet(transition.getSlot());
		PedestrianSimDataLayer currentPetriNet = connection
				.getPetriNetOfSlot(transition.getSlot());
//		System.out.println("Transporting token to: " + attachedPetriNet);
		log(attachedPetriNet, currentPetriNet);
		SourceTransition attachedSource = connection.getAttachedSlot(
				transition.getSlot()).getSituationSource();
		// attachedSource.setEnabled(true);
		attachedPetriNet.fireTransition(attachedSource);
		// Exclude sink transition to prevent possibly infinite loop
		ArrayList<Class> excluded = new ArrayList<Class>();
		excluded.add(SourceTransition.class);
		excluded.add(SinkTransition.class);
		// attachedPetriNet.fireTransition(getRandomTransition(attachedPetriNet,
		// excluded));

		// Disconnect nets when pedestrian comes out of situation, so he doesn't
		// get stuck in a loop.
		// Especially an issue with shared situations

		// connection.getSituationSlot().getDataLayer().get

		if (attachedPetriNet instanceof PedestrianDataLayer
				&& ((SituationDataLayer) currentPetriNet).isShared()) {
			disconnectNets((PedestrianDataLayer) attachedPetriNet,
					(SituationDataLayer) connection
							.getPetriNetOfSlot(transition.getSlot()));

		}
		/*
		 * DataLayerWriter writer = new DataLayerWriter(attachedPetriNet);
		 * 
		 * try { writer.savePNML(new File("petrinets/testnet6.xml")); } catch
		 * (NullPointerException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (DOMException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (TransformerConfigurationException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (ParserConfigurationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (TransformerException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	private void log(PedestrianSimDataLayer situation,
			PedestrianSimDataLayer pedestrian) {
		if (situation instanceof SituationDataLayer) {

			// String[] record = new String[1];
			// record[0] = String.valueOf(getRemainingTime());

			try {
				// This should work, but only because agents are actually not
				// running in parallel.
				FileWriter log = new FileWriter(LOG_FILE_PATH, true);
				String logString = ((PedestrianDataLayer) pedestrian)
						.getBrain().getRemainingTime()
						+ ","
						+ situation.toString() + "\n";
				log.write(logString + "\n");
				log.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Assume for now that only one token class is used.

			// LinkedList<Marking>[] markings =
			// pedestrianDataLayer.getCurrentMarkingVector();

			// System.out.println("What the hell is this? " + markings[0]);

			// String[] logRecord = new String[]{

			// log.w
		}
	}

	public long getRemainingTime(PedestrianDataLayer pedestrian) {
		return pedestrian.getBrain().getRemainingTime();
	}

	/**
	 * Connects a pedestrian to a situation. For the pedestrian, a new slot is
	 * created. For the situation, one of the available slots is used. TODO: If
	 * there is no slot available for the situation, what happens?
	 * 
	 * @param pedestrian
	 * @param situation
	 */
	public boolean connectNets(PedestrianDataLayer pedestrian,
			SituationDataLayer situation, IPedestrianBrain brain, double rate) {
		boolean succeed = false;

		Slot situationSlot = situation.getAvailableSlot();

		if (situationSlot != null) {
			Slot pedestrianSlot = pedestrian.createSlot(rate);
			if (!connectedNets.containsKey(pedestrian)) {
				connectedNets.put(pedestrian,
						new ArrayList<SituationDataLayer>());
			}

			connectedNets.get(pedestrian).add(situation);

			SlotConnection connection = new SlotConnection(pedestrian,
					pedestrianSlot, situation, situationSlot);
			pedestrian.getSlotConnections().add(connection);
			connections.put(pedestrianSlot, connection);
			connections.put(situationSlot, connection);

			situationSlot.setBrain(brain);
			succeed = true;
		}

		return succeed;

	}

	private static Document getConfigDocument(String configFile) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document config = null;
		try {
			config = dbFactory.newDocumentBuilder().parse(configFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return config;
	}

	@Override
	public void step(IPedestrianBrain pedestrian, Observation observation) {

		// if (!pedestrianPetriNets.containsKey(pedestrian.getId())) {
		// loadPedestrian(pedestrian);
		// }

		PedestrianDataLayer pedestrianDataLayer = pedestrian
				.getPedestrianDataLayer();
		/*
		 * DataLayerWriter writer = new DataLayerWriter(pedestrianDataLayer);
		 * try { writer.savePNML(new File(
		 * "C:\\Documents and Settings\\smitsds\\workspace\\SIMOBSTest\\petrinets\\testnet4.xml"
		 * )); } catch (NullPointerException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (DOMException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (TransformerConfigurationException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (ParserConfigurationException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (TransformerException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// Next step in the simulation has begun.
		// TODO: This is more of a heuristic than a fact. Think of something
		// better.
		// It could even be completely wrong, but for now let's assume it isn't.
		// TODO:UPDATE: doesn't work so removed it.
		if (processedPetriNets.contains(pedestrianDataLayer)) {
			// clock.step();
			processedPetriNets.clear();
		}
		pedestrianDataLayer.step();

		if (observation.getNeedNewAction()) {

			Transition randomTransition = pedestrianDataLayer
					.getNextRandomTransition();
			ArrayList<Transition> processedTransitions = processTransition(
					pedestrianDataLayer, randomTransition);
			processedTransitions.addAll(pedestrian.getLastTransitions());
			pedestrian.setLastTransitions(processedTransitions);
		}
		// fireConnectedPetriNets(pedestrianDataLayer);
		processedPetriNets.add(pedestrianDataLayer);
	}

	public void updateSituations(IPedestrianBrain pedestrian, double xLocation,
			double yLocation) {

		Collection<ISituation> situations = this.situations.get(xLocation,
				yLocation);
		removeOldSituations(pedestrian, situations);

		addNewSituations(pedestrian, situations);

	}

	private void removeOldSituations(IPedestrianBrain pedestrian,
			Collection<ISituation> updatedSituations) {
		Collection<ISituation> currentSituations = pedestrian
				.getCurrentSituations();

		ArrayList<ISituation> toBeRemoved = new ArrayList<ISituation>();
		for (ISituation situation : currentSituations) {
			if (!updatedSituations.contains(situation)
					&& !(pedestrian.getPedestrianDataLayer()
							.getLastFiredTransition() instanceof SinkTransition)) {
				toBeRemoved.add(situation);

				disconnectNets(pedestrian.getPedestrianDataLayer(),
						situation.getSituationDataLayer());
			}
		}
		pedestrian.getCurrentSituations().removeAll(toBeRemoved);
	}

	private void writeDataLayerToFile(DataLayer dataLayer, String fileName) {
		DataLayerWriter writer = new DataLayerWriter(dataLayer);
		try {
			writer.savePNML(new File(fileName));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void disconnectNets(PedestrianDataLayer pedestrianDataLayer,
			SituationDataLayer situationDataLayer) {
		try {
			SlotConnection connection = findDataLayerConnection(
					pedestrianDataLayer, situationDataLayer);
			if (connection != null) {
				pedestrianDataLayer.getSlotConnections().remove(connection);
				// if (!situationDataLayer.isMultiPetriNet()) {
				DataLayerWriter writer1 = new DataLayerWriter(
						pedestrianDataLayer);
				// Saving Petri nets is nice for debugging but takes a shitload
				// of time if done every time for every pedestrian.
				// writer1.savePNML(new File("petrinets/testNetBefore.xml"));
				DataLayerWriter writer2 = new DataLayerWriter(
						situationDataLayer);
				// writer2.savePNML(new File(
				// "petrinets/situationTestNetBefore.xml"));

				situationDataLayer.removeSlot(connection.getSituationSlot());
				pedestrianDataLayer.removeSlot(connection.getPedestrianSlot());

				DataLayerWriter writer3 = new DataLayerWriter(
						pedestrianDataLayer);
				// writer3.savePNML(new File("petrinets/testNetAfter.xml"));
				DataLayerWriter writer4 = new DataLayerWriter(
						situationDataLayer);
				// writer4.savePNML(new
				// File("petrinets/situationTestNetAfter.xml"));
				// }
				connection.getSituationSlot().clearBrain();
				// TODO: Quick fix

				pedestrianDataLayer.removeSlot(connection.getPedestrianSlot());

				connections.remove(connection);
				connectedNets.get(pedestrianDataLayer).remove(
						situationDataLayer);
			}
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (TransformerConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}

	}

	// TODO: This method would be unnecessary with better structured DataLayer
	// and Slot classes.
	private SlotConnection findDataLayerConnection(
			PedestrianDataLayer pedestrianDataLayer,
			SituationDataLayer situationDataLayer) {

		ArrayList<SlotConnection> slotConnections = pedestrianDataLayer
				.getSlotConnections();

		for (SlotConnection slotConnection : slotConnections) {
			if (slotConnection.connectsSituation(situationDataLayer)) {
				return slotConnection;
			}
		}

		return null;
	}

	private void addNewSituations(IPedestrianBrain pedestrian,
			Collection<ISituation> updatedSituations) {
		TimePlanningTools timePlanningTools = new TimePlanningTools();
		double rate;
		boolean succeed;
		Collection<ISituation> currentSituations = pedestrian
				.getCurrentSituations();
		ArrayList<ISituation> toBeAdded = new ArrayList<ISituation>();
		for (ISituation updatedSituation : updatedSituations) {
			if (!currentSituations.contains(updatedSituation)) {
				rate = timePlanningTools.computeSituationTransitionRate(
						updatedSituation, pedestrian.getRemainingTime());
				SituationDataLayer situationDataLayer = updatedSituation
						.getSituationDataLayer();
				situationDataLayer.addObserver(this);
				succeed = connectNets(pedestrian.getPedestrianDataLayer(),
						situationDataLayer, pedestrian, rate);
				if (succeed) {
					toBeAdded.add(updatedSituation);
				}
			}
		}
		//writeSituationInfo(toBeAdded);		
		currentSituations.addAll(toBeAdded);
	}
	


	/**
	 * Processes a transition, which means that it both fires the transition in
	 * the Petri net, and communicates the corresponding action to the
	 * Pedestrian manager.
	 * 
	 * @param currentPetriNet
	 * @param randomTransition
	 * 
	 * @return The transition that corresponds to the action that should be
	 *         executed.
	 */
	private ArrayList<Transition> processTransition(
			PedestrianDataLayer currentPetriNet, Transition randomTransition) {

		if (randomTransition != null) {
			currentPetriNet.fireTransition(randomTransition);
		}
		ArrayList<Transition> firedTransitions = fireConnectedPetriNets(currentPetriNet);
		firedTransitions.add(randomTransition);
		return firedTransitions;

		// selectRelevantTransition(firedTransitions);
	}

	/**
	 * Selects one transition from the list of fired transitions which should be
	 * the action executed by the pedestrian. This implementation just chooses
	 * the first transition it encounters. The implementation is based on the
	 * assumption that only one transition fired of all the transitions
	 * currently attached to the pedestrian will be a transition that
	 * corresponds to an action.
	 * 
	 * @param firedTransitions
	 */
	/*
	 * private void selectRelevantTransition(ArrayList<Transition>
	 * firedTransitions) { String currentTransitionName; for(Transition
	 * firedTransition : firedTransitions){ currentTransitionName =
	 * firedTransition.getName(); if(getSlotNumber(firedTransition)) }
	 * 
	 * }
	 */

	/**
	 * Fires the Petri nets that are connected to the current Petri net and have
	 * not yet been fired in this step of the system.
	 * 
	 * @param currentPetriNet
	 *            A pedestrian petri net.
	 * @return The transitions that have been fired. These need to be returned
	 *         so that actions occurring in Petri nets other than the
	 *         Pedestrians personal Petri net can also be interpreted if
	 *         necessary.
	 */
	private ArrayList<Transition> fireConnectedPetriNets(
			PedestrianDataLayer currentPetriNet) {
		// SituationDataLayer currentSituationNet;
		Transition currentTransition;
		ArrayList<Transition> firedTransitions = new ArrayList<Transition>();

		// TODO: Could this be placed in a function higher up? Now connectedNets
		// is checked in two
		// methods on whether it contains currentPetriNet yet.
		if (!connectedNets.containsKey(currentPetriNet)) {
			connectedNets.put(currentPetriNet,
					new ArrayList<SituationDataLayer>());
		}
		ArrayList<SituationDataLayer> connectedSituations = (ArrayList<SituationDataLayer>) connectedNets
				.get(currentPetriNet).clone();

		for (SituationDataLayer currentSituationNet : connectedSituations) {
			if (!processedPetriNets.contains(currentSituationNet)) {
				// currentSituationNet =
				// currentPetriNet.getSituationNets().get(situationNetKey);
				// currentTransition =
				// currentSituationNet.getRandomTransition();
				currentTransition = currentSituationNet.getRandomTransition();
				currentSituationNet.fireTransition(currentTransition);
				// addToCorrespondingPedestrian(currentTransition,
				// currentSituationNet);
				// firedTransitions.add(currentTransition);
			}
		}
		return firedTransitions;
	}

	// TODO: Hmm am I sure this is not done somewhere else already?

	private void addToCorrespondingPedestrian(Transition transition,
			SituationDataLayer situationDataLayer) {
		Slot slot = situationDataLayer.getCorrespondingSlot(transition);
		slot.getBrain().addLastTransition(transition);
	}

	private Transition getRandomTransition(PedestrianSimDataLayer currentNet) {
		ArrayList<Class> excluded = new ArrayList<Class>();
		excluded.add(Source.class);
		return getRandomTransition(currentNet, excluded);
	}

	/**
	 * Chooses a random transition from the Petri net, but makes sure this
	 * transition is not a source transition. Otherwise tokens would be produced
	 * when they shouldn't be.
	 * 
	 * @param currentNet
	 * @param excluded
	 * @return An (almost) randomly chosen transition.
	 */
	private Transition getRandomTransition(PedestrianSimDataLayer currentNet,
			ArrayList<Class> excluded) {
		// TODO: There probably is a more efficient approach
		Transition randomTransition = currentNet.getRandomTransition();
		int counter = 20;
		while (excluded.contains(randomTransition.getClass()) && counter > 0) {
			randomTransition = currentNet.getRandomTransition();
			counter--;
		}
		if (isSource(randomTransition)) {
			randomTransition = null;
		}
		return randomTransition;

	}

	private boolean isSource(Transition transition) {
		return transition != null
				&& (transition.getClass() == SourceTransition.class || transition
						.getName().startsWith("source"));
	}

	/**
	 * Retrieves the number indicating the slot the current transition belongs
	 * to, if there is such number.
	 * 
	 * @param transition
	 * @return The number of the slot the transition belongs to, if there is
	 *         such number. Otherwise, it returns -1;
	 */
	private int getSlotNumber(Transition transition) {
		String transitionName = transition.getName();
		try {
			String numberString = transitionName.split(":")[1];
			int number = Integer.parseInt(numberString);
			return number;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}

	}

	// Test
	public static void main(String args[]) {
		SituationManager manager = new SituationManager(
				getConfigDocument("situations.xml"), new StepClock());
//		System.out.println("Situations: " + manager.getSituations(250, 50));

	}

	private void loadPedestrian(IPedestrianBrain pedestrianBrain) {
		PedestrianDataLayer pedestrianPetriNet = new PedestrianDataLayer(clock,
				BASE_PEDESTRIAN_NET_FILE, pedestrianBrain);
		pedestrianPetriNets.put(pedestrianBrain.getId(), pedestrianPetriNet);
	}

}
