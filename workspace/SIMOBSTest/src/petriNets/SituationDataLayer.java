package petriNets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import pipe.common.dataLayer.DataLayerWriter;
import pipe.common.dataLayer.Transition;
import time.IClock;

//TODO: Create situation datalayer with infinite amount of slots
public class SituationDataLayer extends PedestrianSimDataLayer {

	private String situationId;
	private HashMap<Integer, Slot> slots;
	private LinkedList<Slot> availableSlots;
	private int estimatedTime;
	private HashMap<Slot, Integer> estimatedTimes;
	private boolean isShared;

	public boolean isShared() {
		return isShared;
	}

	/*
	 * private boolean multiPetriNet;
	 * 
	 * // private HashMap<Integer, Slot> slotMap;
	 * 
	 * public boolean isMultiPetriNet() { return multiPetriNet; }
	 */
	public SituationDataLayer(IClock clock, File pnmlFile, boolean isShared) {
		super(clock, pnmlFile);
		this.isShared = isShared;
		situationId = pnmlFile.getName();
		// this.multiPetriNet = multiPetrinet;
		init();
	}

	public SituationDataLayer(IClock clock, String pnmlFileName, boolean isShared) {
		super(clock, new File(pnmlFileName));
		this.isShared = isShared;
		situationId = pnmlFileName;
		// this.multiPetriNet = multiPetrinet;
		init();
	}

	private void init() {
		estimatedTimes = new HashMap<Slot, Integer>();
		availableSlots = new LinkedList<Slot>();
		slots = new HashMap<Integer, Slot>();
		// Assuming that there is a sink for every source, and they both are listed in numeric
		// order.
		Iterator<SourceTransition> sourceIterator = sources.iterator();
		Iterator<SinkTransition> sinkIterator = sinks.iterator();

		SourceTransition currentSource;
		SinkTransition currentSink;
		Slot currentSlot;
		// Starts at one because that is more intuitive when designing the petri net
		int currentSlotNumber = 1;

		// TODO: Make the list inverse
		while (sinkIterator.hasNext()) {
			currentSource = sourceIterator.next();
			currentSink = sinkIterator.next();
			currentSlot = new Slot(this, currentSource, currentSink, currentSlotNumber, null);
			// newSlot = new Slot(this, currentSource, currentSink, currentSlotNumber);
			slots.put(currentSlotNumber, currentSlot);
			availableSlots.push(currentSlot);
			// slotMap.put(currentSlotNumber, newSlot);
			currentSlotNumber++;
		}

	}

	public String getSituationId() {
		return situationId;
	}

	public void setSituationid(String situationId) {
		this.situationId = situationId;
	}

	/**
	 * Returns a slot (a sink and source) to which a pedestrian can be attached.
	 * 
	 * @return An available slot
	 */
	public Slot getAvailableSlot() {

		// return availableSlots.peek();
		try {
			return availableSlots.pop();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/*
	 * public Slot getSlotByNumber(int slotNumber) { return slotMap.get(slotNumber); }
	 */

	public void removeSlot(Slot slot) {
		//super.removeSlot(slot);
		if(slot!=null){
			availableSlots.push(slot);
		}
	}

	public void setEstimatedTime(Slot slot, int estimatedTime) {
		this.estimatedTimes.put(slot, estimatedTime);
	}

	public int getEstimatedTime(Slot slot) {
		return estimatedTimes.get(slot);
	}

	@Override
	public void fireTransition(Transition transition) {
		super.fireTransition(transition);
		Slot slot = getCorrespondingSlot(transition);

		if (slot != null && slot.getBrain() != null && transition != null) {
			slot.getBrain().addLastTransition(transition);
		}

		DataLayerWriter writer = new DataLayerWriter(this);

		try {
			File testPetriNetFile = new File("petrinets/situationTestNetbla.xml");
			testPetriNetFile.createNewFile();
			writer.savePNML(testPetriNetFile);
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

	public Slot getCorrespondingSlot(Transition transition) {
		try {
			String name = transition.getName();
			int slotNumber = Integer.parseInt(name.split(":")[1]);

			return slots.get(slotNumber);
		} catch (Exception e) {
			return null;
		}
	}
}
