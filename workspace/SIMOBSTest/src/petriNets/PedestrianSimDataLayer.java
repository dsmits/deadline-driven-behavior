package petriNets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import pipe.common.dataLayer.Arc;
import pipe.common.dataLayer.ArcFactory;
import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.DataLayerInterface;
import pipe.common.dataLayer.DataLayerWriter;
import pipe.common.dataLayer.Marking;
import pipe.common.dataLayer.PetriNetObject;
import pipe.common.dataLayer.Transition;
import situations.SituationManager.UpdateMessage;
import time.IClock;
import time.StepClock;

/**
 * PedestrianSimDataLayer is the superclass of all datalayers used in the
 * pedestrian sim. It extends the basic PIPE2 data with some functionalities
 * needed for the pedestrian simulation. These functionalities include keeping
 * track of sink and source transitions, dealing with slots and dealing with
 * time.
 * 
 * @author smitsds
 * 
 */
/**
 * @author Djura
 *
 */
public class PedestrianSimDataLayer extends DataLayer {

	protected Collection<SinkTransition> sinks;
	protected Collection<SourceTransition> sources;
	protected Transition lastFiredTransition;
	protected int slotNameCounter;
	// protected ArrayList<Slot> slots;
	protected ArrayList<SlotConnection> slotConnections;

	private ITimeProbabilityFunction iTimeProbabilityFunction;
	private ITimeProbabilityFunction goalTimeProbabilityFunction;
	protected IClock clock;
	// private double goalTime;
	// private double currentTime;
	protected HashMap<Transition, Double> probabilities;
	private HashMap<Transition, Double> durations;
	protected IClock goalTime;
	protected DataLayerLogger logger;
	protected final static int DEADLINE = 500;




	/**
	 * Get the list of Slotconnections that connect this datalayer to another.
	 * @return an ArrayList with SlotConnections that are in use at the moment.
	 */
	public ArrayList<SlotConnection> getSlotConnections() {
		return slotConnections;
	}

	/**
	 * Constructor that instantiates the PedestrianSimDataLayer with a default deadline.
	 * @param clock The universal clock of the simulation.
	 */
	public PedestrianSimDataLayer(IClock clock) {
		super();
		init(clock, new StepClock(DEADLINE));
	}


	/**
	 * PedestrianSimDataLayer constructor for which a petrinet file has to be defined.
	 * 
	 * @param clock The universal clock of the simulation.
	 * @param pnmlFile The petri net file.
	 */
	public PedestrianSimDataLayer(IClock clock, File pnmlFile) {
		super(pnmlFile);
		init(clock, new StepClock(DEADLINE));

	}

	/**
	 * Constructor for which, apart from the universal clock, a deadline in the form of an IClock and a petri net file has to be defined.
	 * @param clock The universal clock of the simulation.
	 * @param goalTime The deadline.
	 * @param pnmlFileName The petri net file.
	 */
	public PedestrianSimDataLayer(IClock clock, IClock goalTime,
			String pnmlFileName) {
		super(pnmlFileName);
		init(clock, goalTime);
	}


	public PedestrianSimDataLayer(IClock clock, File pnmlFile,
			ITimeProbabilityFunction iTimeProbabilityFunction,
			ITimeProbabilityFunction goalTimeProbabilityFunction) {
		super(pnmlFile);
		init(clock, new StepClock(DEADLINE), iTimeProbabilityFunction,
				goalTimeProbabilityFunction);

	}

	public PedestrianSimDataLayer(IClock clock, String pnmlFileName,
			ITimeProbabilityFunction iTimeProbabilityFunction,
			ITimeProbabilityFunction goalTimeProbabilityFunction) {
		super(pnmlFileName);
		init(clock, new StepClock(DEADLINE), iTimeProbabilityFunction,
				goalTimeProbabilityFunction);
	}

	private void init(IClock clock, IClock goalTime) {

		// Checked matlab for this one:
		//init(clock, goalTime, new Sigmoid(clock.getTime()+goalTime.getTime()/2, -0.5, 10, 0.5), new Constant(0.01));
		/*
		 * Time probability for non-goal actions should go from low to high, because this function gets as input how much time there is still left. When there is little time left, the probability of doing non-goal actions should be lower. 
		 */
		init(clock, goalTime, new Linear(0, 1, 200), new Constant(0));
		//init(clock, goalTime, new Constant(1), new Gaussian(0, 10, 0.2));

		//init(clock, goalTime, new Constant(1), new Linear(0, 1, 200));

		//init(clock, goalTime,new Constant(1),  new Sigmoid(
		//init(clock, goalTime, new Constant(1), new Sigmoid((clock.getTime() + goalTime.getTime()/2), 0,-1, 0.1));
		//init(clock, goalTime, new Sigmoid(clock.getTime()+goalTime.getTime()/2, 0, 1, 1), new Sigmoid((clock.getTime() + goalTime.getTime()/2), 0,-1, 0.0001));
		//init(clock, goalTime, new Constant(1), new Sigmoid(
		//	(clock.getTime() + goalTime.getTime() / 2), 0, -1, 0.2));

		// init(clock, new Sigmoid(), new Sigmoid(1, 1, -1, 1));
	}



	private void init(IClock clock, IClock goalTime,
			ITimeProbabilityFunction iTimeProbabilityFunction,
			ITimeProbabilityFunction goalTimeProbabilityFunction) {
		// situationNets = new HashMap<String, DataLayerInterface>();
		// situationSlots = new HashMap<String, Transition>();
		this.iTimeProbabilityFunction = iTimeProbabilityFunction;
		this.goalTimeProbabilityFunction = goalTimeProbabilityFunction;
		this.clock = clock;
		// FIXME: wrong goalTime
		this.goalTime = goalTime; // new StepClock(DEADLINE);
		listSinks();
		listSources();
		createSlots();
		initProbabilities();
		slotNameCounter = sinks.size() + 1;
		slotConnections = new ArrayList<SlotConnection>();
		try {

			logger = new DataLayerLogger(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createSlots() {
		// TODO Auto-generated method stub

	}

	/**
	 * Creates a list of all transitions in the datalayer that start with the
	 * same specified suffix and a number as postfix.
	 * 
	 * @param name
	 * @return
	 */
	private Collection<SourceTransition> listNumberedSourceTransitions() {
		Collection<SourceTransition> numberedTransitionCollection = new TreeSet<SourceTransition>(
				new TransitionComparator());

		int i = 1;

		Transition transition;
		SourceTransition convertedTransition;

		while ((transition = getTransitionByName("source:" + i)) != null) {
			convertedTransition = new SourceTransition(transition);
			replaceTransition(transition, convertedTransition);
			numberedTransitionCollection.add(convertedTransition);
			i++;
		}
		return numberedTransitionCollection;
	}

	/**
	 * Creates a list of all transitions in the datalayer that start with the
	 * same specified suffix and a number as postfix.
	 * 
	 * @param name
	 * @return
	 */
	private Collection<SinkTransition> listNumberedSinkTransitions() {
		Collection<SinkTransition> numberedTransitionCollection = new TreeSet<SinkTransition>(
				new TransitionComparator());

		int i = 1;

		Transition transition;
		SinkTransition convertedTransition;

		while ((transition = getTransitionByName("sink:" + i)) != null) {

			convertedTransition = new SinkTransition(transition);
			convertedTransition.setName(transition.getName());

			replaceTransition(transition, convertedTransition);
			numberedTransitionCollection.add(convertedTransition);
			i++;
		}
		return numberedTransitionCollection;
	}

	private void replaceTransition(Transition transition,
			Transition convertedTransition) {
		//		System.out.println("Replacing " + transition + " with "
		//				+ convertedTransition);
		if (transition.equals(convertedTransition)) {
			return;
		}
		// this.addPetriNetObject(convertedTransition);
		Iterator arcsFromIterator = transition.getArcsFrom();
		Arc currentArc;
		Arc newArc;
		ArrayList<PetriNetObject> toBeRemoved = new ArrayList<PetriNetObject>();
		while (arcsFromIterator.hasNext()) {
			currentArc = (Arc) arcsFromIterator.next();
			newArc = ArcFactory.createNormalArc(convertedTransition);
			newArc.setTarget(currentArc.getTarget());
			// newArc.setSource(convertedTransition);
			convertedTransition.addConnectTo(newArc);
			// newArc.addLabelToContainer();
			Marking m = new Marking(getActiveTokenClass());
			m.setCurrentMarking(1);
			LinkedList<Marking> markings = new LinkedList<Marking>();
			markings.add(m);
			newArc.setWeight(markings);

			toBeRemoved.add(currentArc);
			addPetriNetObject(newArc);

		}

		Iterator arcsToIterator = transition.getArcsTo();

		while (arcsToIterator.hasNext()) {
			currentArc = (Arc) arcsToIterator.next();
			newArc = ArcFactory.createNormalArc(currentArc.getSource());

			newArc.setTarget(convertedTransition);
			convertedTransition.addConnectFrom(newArc);
			// newArc.addLabelToContainer();
			Marking m = new Marking(getActiveTokenClass());
			m.setCurrentMarking(1);
			LinkedList<Marking> markings = new LinkedList<Marking>();
			markings.add(m);
			newArc.setWeight(markings);

			// System.out.println("Replacing "+ currentArc + " with " + newArc);
			toBeRemoved.add(currentArc);

			addPetriNetObject(newArc);

		}
		toBeRemoved.add(transition);

		for (PetriNetObject pnObject : toBeRemoved) {
			removePetriNetObject(pnObject);
		}
		this.addPetriNetObject(convertedTransition);
		/*
		 * DataLayerWriter writer = new DataLayerWriter(this); try {
		 * writer.savePNML(new File(
		 * "C://Documents and Settings/smitsds/workspace/SIMOBSTest/petrinets/testNet3.xml"
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

	}

	/**
	 * Fills the sink list
	 */
	private void listSinks() {
		sinks = listNumberedSinkTransitions();
	}

	/**
	 * Fills the source list
	 */
	private void listSources() {
		sources = listNumberedSourceTransitions();

	}

	private void addAllPetriNetObjects(DataLayerInterface newNet,
			DataLayerInterface situationNet) {

		Iterator<PetriNetObject> petrinetObjectIterator = situationNet
				.getPetriNetObjects();

		while (petrinetObjectIterator.hasNext()) {
			newNet.addPetriNetObject(petrinetObjectIterator.next());
		}

	}

	/*
	 * private NormalArc getSlotArc(DataLayerInterface situationNet) {
	 * Transition slotTransition = situationNet.getTransitionByName("slot");
	 * NormalArc slotArc = ArcFactory.createNormalArc(slotTransition);
	 * slotArc.setTarget(slotTransition);
	 * slotTransition.addConnectFrom(slotArc);
	 * 
	 * Arc arc = (Arc) slotTransition.getArcsFrom().next();
	 * slotArc.setWeight(arc.getWeight()); return slotArc; }
	 */

	/**
	 * Comparator to store transitions in such a way that the one with a lower
	 * number as postfix will be offered sooner than ones with a higher postfix.
	 */
	public class TransitionComparator implements Comparator<Transition> {

		@Override
		public int compare(Transition arg0, Transition arg1) {
			return String.CASE_INSENSITIVE_ORDER.compare(arg0.getName(),
					arg1.getName());
		}

	}

	// @Override
	public Collection<SinkTransition> getSinks() {
		return sinks;
	}

	// @Override
	/*
	 * public Transition getAvailableSink() { Transition sink =
	 * sinks.iterator().next(); sinks.remove(sink); return sink; }
	 */

	// @Override
	public Collection<SourceTransition> getAvailableSources() {
		return sources;
	}

	// @Override
	public Transition getAvailableSource() {
		Transition source = sources.iterator().next();
		sources.remove(source);
		return source;
	}

	// @Override
	public void fireTransition(Transition transition) {
		try {
			super.fireTransition(transition);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}


		lastFiredTransition = transition;
		setChanged();
		UpdateMessage message;
		if (lastFiredTransition instanceof SinkTransition
				|| (lastFiredTransition != null && lastFiredTransition
				.getName().startsWith("sink"))) {
			message = UpdateMessage.SINK_FIRED;
			//writeToFile(new File("petriNets/sinkHasFired.xml"));
		} else {
			message = UpdateMessage.SINK_NOT_FIRED;
			//writeToFile(new File("petriNets/sinkHasNotFired.xml"));
		}

		notifyObservers(message);
		clearChanged();

	}

	private void writeToFile(File file) {
		DataLayerWriter writer = new DataLayerWriter(this);
		try {
			writer.savePNML(file);
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

	// To remember which action has to be taken
	public Transition getLastFiredTransition() {
		return lastFiredTransition;
	}

	public boolean sinkHasFired() {
		if (lastFiredTransition != null) {
			return SinkTransition.class.getName().equals(
					lastFiredTransition.getClass().getName());
		}
		return false;
	}

	public void removeSlot(Slot slot) throws NullPointerException {
		if (slot != null) {
			removeSlotConnections(slot);
			removeProbability(slot);
			Arc sinkArc = (Arc) (slot.getSituationSink().getArcsFrom().next());
			Transition sinkTransition = slot.getSituationSink();
			// TODO: Quick fix
			this.removePetriNetObject(sinkArc);
			this.removePetriNetObject(sinkTransition);
			// sinkArc.delete();
			// sinkTransition.delete();
			// CreateGui.getModel().removePetriNetObject((Arc)(slot.getSituationSink().getArcsFrom().next()));
			// CreateGui.getModel().removePetriNetObject(slot.getSituationSink());
			Arc sourceArc = (Arc) (slot.getSituationSource().getArcsTo().next());
			Transition sourceTransition = slot.getSituationSource();
			// TODO: Check if token is in place that is removed
			try {
				this.removePetriNetObject(sourceArc);
				this.removePetriNetObject(sourceTransition);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			// sinkArc.delete();
			// sinkTransition.delete();
			// CreateGui.getModel().removePetriNetObject((Arc)(slot.getSituationSource().getArcsTo().next()));
			// CreateGui.getModel().removePetriNetObject(slot.getSituationSource());
			setChanged();
		}
	}

	private void removeSlotConnections(Slot slot) {
		ArrayList<SlotConnection> toBeRemoved = new ArrayList<SlotConnection>();
		for (SlotConnection slotConnection : slotConnections) {
			if (slotConnection.getPedestrianSlot().equals(slot)
					|| slotConnection.getSituationSlot().equals(slot)) {
				toBeRemoved.add(slotConnection);
			}
		}
		slotConnections.removeAll(toBeRemoved);
	}

	protected void removeProbability(Slot slot) {
		probabilities.remove(slot.getSituationSink());
	}

	/*
	 * @Override public synchronized boolean hasChanged() { return
	 * super.hasChanged() || changed; }
	 */

	/*
	 * @Override protected synchronized void clearChanged() {
	 * super.clearChanged(); changed = false; }
	 */

	/*
	 * // TODO: Is this really necessary? public void initDurations() {
	 * durations = new HashMap<Transition, Double>(); ArrayList<Transition>
	 * transitions = dataLayer.getTransitionsArrayList(); double duration;
	 * 
	 * for (Transition transition : transitions) { duration =
	 * transition.getRate(); durations.put(transition, duration); }
	 * 
	 * }
	 */
	private void initProbabilities() {
		probabilities = new HashMap<Transition, Double>();
		ArrayList<Transition> transitions = getTransitionsArrayList();

		for (Transition transition : transitions) {
			if (transition instanceof SourceTransition) {
				probabilities.put(transition, 0.0);
			} else {
				probabilities.put(transition, 1.0);
			}
		}

	}

	private boolean isGoalTransition(Transition transition) {
		return transition.getName().equals("gotogoal");
	}

	public void changeTime() {

		for (Transition transition : getTransitionsArrayList()) {

//			System.out.println("Changing time for transition: "
//					+ transition.getName() + ", clock: " + clock
//					+ " goalTime: " + goalTime);
			if (isGoalTransition(transition)) {
				probabilities.put(
						transition,
						getProbability(clock, goalTime, transition,
								goalTimeProbabilityFunction));
			} else {
				double probability = getProbability(clock, goalTime, transition);
				System.out.println("Changing time for transition: " + transition.getName() + ", clock: " + clock
					+ " goalTime: " + goalTime + "\nProbability becomes: " + probability);
				probabilities.put(transition,
						getProbability(clock, goalTime, transition));
			}
		}
		printProbabilities(probabilities);
	}

	private void printProbabilities(HashMap<Transition, Double> probabilities) {
		String probabilityString = "";
		for (Transition transition : probabilities.keySet()) {
			probabilityString += transition.getName() + ": "
					+ probabilities.get(transition) + "   ";
		}
		System.out.println(probabilityString);

	}

	/**
	 * Step recomputes the probabilities based on the current time indicated by
	 * the clock.
	 * 
	 * @param time
	 */

	public void step() {
		changeTime();
	}

	/**
	 * Fires a random transition, chosen according to the probability
	 * distribution at the current timestep.
	 * 
	 */
	/*
	 * public void fire() { step(); int maxTries = 5; int tries = 0;
	 * 
	 * Transition randomTransition = getRandomTransition(); while
	 * ((randomTransition instanceof SourceTransition) && tries < maxTries) {
	 * randomTransition = getRandomTransition(); }
	 * 
	 * if (randomTransition != null) { this.fireTransition(randomTransition); }
	 * }
	 */

	/**
	 * Returns a random transition that is not a Source transition, while
	 * advancing in time. Source transitions should only be fired when the corresponding sink transition in the attached Petri net has been fired as well.
	 * 
	 * @return
	 */
	public Transition getNextRandomTransition() {
		step();
		int maxTries = 5;
		int tries = 0;
		
		//TODO: Could be waaay more efficient.
		Transition randomTransition = getRandomTransition();
		while ((randomTransition instanceof SourceTransition)
				&& tries < maxTries) {
			randomTransition = getRandomTransition();
			tries++;
		}

		if (randomTransition instanceof SourceTransition) {
			return null;
		}
		return randomTransition;
	}

	public Transition getRandomTransition() {
		Transition randomTransition = null;
		System.out.println("Probabilities for " + this + ":: "
				+ probabilities.values());
		// TODO: Provide better structure to probabilities.
		double probabilitySum = 0;

		ArrayList<Transition> transitions = getEnabledTransitions();

		for (Transition transition : transitions) {
			probabilitySum += probabilities.get(transition);
		}

		Random random = new Random();
		double randomDouble = random.nextDouble() * probabilitySum;
		double currentProbabilitySum = 0;

		for (Transition transition : transitions) {
			currentProbabilitySum += probabilities.get(transition);
			if (currentProbabilitySum >= randomDouble) {
				randomTransition = transition;
				break;
			}
		}
		// No appropriate transition was available.
		if (randomTransition == null
				|| probabilities.get(randomTransition) == 0) {
			randomTransition = null;
		}

		return randomTransition;
	}

	private double getProbability(IClock currentTime, IClock goalTime,
			Transition transition) {
		return getProbability(currentTime, goalTime, transition,
				iTimeProbabilityFunction);
	}

	private double getProbability(IClock currentTime, IClock goalTime,
			Transition transition,
			ITimeProbabilityFunction iTimeProbabilityFunction) {
		double duration = transition.getRate();
		double timeLeft = goalTime.getTime() - currentTime.getTime() - duration;

		double probability = iTimeProbabilityFunction.compute(timeLeft);

		return probability;
	}

	@Override
	public String toString() {
		return super.toString() + ":" + this.getFullURI();
	}

	private class Constant implements ITimeProbabilityFunction {

		private double y;

		private Constant(double y) {
			this.y = y;
		}

		@Override
		public double compute(double t) {
			// TODO Auto-generated method stub
			return y;
		}

	}

	private class Sigmoid implements ITimeProbabilityFunction {

		// Maybe there are a more official names than this.
		double xShift;
		double yShift;
		double xStretch;
		double yStretch;

		private Sigmoid() {
			this(1, 1, 1, 1);
		}

		private Sigmoid(double xShift, double yShift, double xStretch,
				double yStretch) {
			this.xShift = xShift;
			this.yShift = yShift;
			this.xStretch = xStretch;
			this.yStretch = yStretch;
		}

		public double compute(double t) {
			double exponent = (t - xShift) / xStretch * -1;
			double numerator = (1 + Math.exp(exponent));
			double result = yStretch / numerator - yShift;

			return result;

		}

	}

	/**
	 * @author Compaq Linear is a simple implementation of
	 *         TimeProbabilityFunction using a linear function with parameters
	 *         which indicate the value of y at x=0, the value of y at the end
	 *         (when the deadline has been reached) and the deadline.
	 * 
	 */
	private class Linear implements ITimeProbabilityFunction {

		private double a;
		private double b;
		private double startValue;
		private double endValue;
		private double endX;

		/**
		 * Constructor for Linear implementation of Time.
		 * 
		 * @param startValue
		 *            The y value of the function at x=0.
		 * @param endValue
		 *            The y value of the function at x=endX.
		 * @param endX
		 *            The x value of the deadline.
		 */
		private Linear(double startValue, double endValue, double endX) {
			this.startValue = startValue;
			this.endValue = endValue;
			this.endX = endX;

			a = (endValue - startValue) / endX;
			b = startValue;
		}

		/*
		 * y = ax + b (non-Javadoc)
		 * 
		 * @see petriNets.TimeProbabilityFunction#compute(double)
		 */
		@Override
		public double compute(double x) {
			double modifiedX;

			if (x < 0) {
				modifiedX = 0;
			} else if (x > endX) {
				modifiedX = endX;
			} else {
				modifiedX = x;
			}

			return a * modifiedX + b;

		}

	}

	private class Gaussian implements ITimeProbabilityFunction {

		private double a;
		private double b;
		private double c;
		private double max;

		private Gaussian(double mu, double sigma, double max) {
			a = 1 / (sigma * Math.sqrt(2 * Math.PI));
			b = mu;
			c = sigma;
			this.max = max;
		}

		@Override
		public double compute(double t) {
			return max * a
					* Math.exp(-1 * (Math.pow(t - b, 2) / (2 * Math.pow(c, 2))));
		}
	}

	private class Zero implements ITimeProbabilityFunction {

		@Override
		public double compute(double t) {

			return 0;
		}

	}


	public String testGaussian() {
		Gaussian gaussian = new Gaussian(0.0, 1.0, 1.0);
		return String.valueOf(gaussian.compute(1));
	}

	public String testSigmoid() {
		Sigmoid sigmoid = new Sigmoid(0, 0, 1, 1);
		return String.valueOf(sigmoid.compute(1));
	}

	public static void main(String args[]) {
		PedestrianSimDataLayer dl = new PedestrianSimDataLayer(new StepClock());
		System.out.println(dl.testGaussian());
		System.out.println(dl.testSigmoid());

	}

}
