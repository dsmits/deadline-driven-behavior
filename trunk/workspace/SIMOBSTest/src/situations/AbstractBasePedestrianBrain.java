package situations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import petriNets.DataLayerLogger;
import petriNets.PedestrianDataLayer;
import pipe.common.dataLayer.Transition;
import time.IClock;
import time.StepClock;

/**
 * @author smitsds
 * 
 *         Base implementation of IPedestrianBrain. Contains implementations of
 *         the basic functionality.
 * 
 */

public abstract class AbstractBasePedestrianBrain implements IPedestrianBrain {
	protected String id;
	// protected IPedestrianState currentState;
	private Collection<ISituation> currentSituations;
	protected ArrayList<Transition> lastTransitions;
	protected PedestrianDataLayer pedestrianDataLayer;
	protected static final int DEFAULT_GOAL_TIME = 200;
	protected IClock goalTime;
	protected String LOG_FILE_PATH = "evaluation_log.csv";
	protected FileWriter log;
	protected DataLayerLogger dataLayerLogger;

	// The clock counts back to zero. When zero is reached, and the pedestrian
	// hasn't reached the
	// goal, he missed the deadline.
	protected IClock clock;

	/*
	 * public AbstractBasePedestrianBrain(String id) { this(id,
	 * DEFAULT_GOAL_TIME); }
	 */

	public AbstractBasePedestrianBrain(String id, IClock clock) {
		// Changed so that pedestrian has its own clock.
		this(id, new StepClock(), new StepClock(DEFAULT_GOAL_TIME));
	}

	public AbstractBasePedestrianBrain(String id, IClock clock, IClock goalTime) {
		this.clock = clock;
		this.id = id;
		this.goalTime = goalTime;
		currentSituations = new ArrayList<ISituation>();
		this.lastTransitions = new ArrayList<Transition>();

	}

	@Override
	public String step() {
		// IPedestrianTransition transition = currentState.transition();
		// currentState = transition.getTargetState();
		// return transition.getAction();
		log();
		//clock.step();
		
//		try {
//			dataLayerLogger
//					.log("Last executed transitions: " + lastTransitions);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		
		Transition lastActionTransition = chooseLastActionTransition();

		clearLastTransitions();
		String transitionActionName = getTransitionActionName(lastActionTransition);
		// reduceClock(getTransitionDuration(lastActionTransition));
		clock.step();

		return transitionActionName;
	}

	public IClock getClock() {
		return clock;
	}


	private void log() {

		if (hasReachedGoal()) {
			// String[] record = new String[1];
			// record[0] = String.valueOf(getRemainingTime());

			try {
				// This should work, but only because agents are actually not
				// running in parallel.
				log = new FileWriter(LOG_FILE_PATH, true);
				log.write(getRemainingTime() + "\n");
				log.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Assume for now that only one token class is used.

		// LinkedList<Marking>[] markings =
		// pedestrianDataLayer.getCurrentMarkingVector();

		// System.out.println("What the hell is this? " + markings[0]);

		// String[] logRecord = new String[]{

		// log.w
	}

	private boolean hasReachedGoal() {
		ArrayList<Transition> enabledTransitions = pedestrianDataLayer
				.getEnabledTransitions();
		boolean reachedGoal = false;

		for (Transition transition : enabledTransitions) {
			if (transition.getName().equals("respawn")) {
				reachedGoal = true;
			}
		}

		return reachedGoal;
	}

	@Override
	public long getRemainingTime() {
		return goalTime.getTime() - clock.getTime();
	}

	private int getTransitionDuration(Transition lastActionTransition) {
		return 1;
	}

	public String getTransitionActionName(Transition transition) {
		String transitionActionName;
		// TODO: Remove error handling, nullPointerException shouldn't occur in
		// the first place.
		try {
			transitionActionName = transition.getName().split(":")[0];
		} catch (NullPointerException e) {
			e.printStackTrace();
			transitionActionName = "noTransitionAction";
		}
//		System.out.println("TransitionActionName: " + transitionActionName);
		return transitionActionName;

	}

	private Transition chooseLastActionTransition() {
		return chooseActionTransition(lastTransitions);
	}

	private Transition chooseActionTransition(ArrayList<Transition> transitions) {
		// TODO: Quick fix
		Transition transition = null;
		for (Transition currentTransition : transitions) {
			if (isActionTransition(currentTransition)) {
				transition = currentTransition;
			}
		}
		// Transition transition = transitions.get(0);
		return transition;
	}

	private boolean isActionTransition(Transition currentTransition) {
		boolean actionTransition = true;
		// boolean relevantActionTransition = true;
		if (currentTransition == null) {
			actionTransition = false;
		} else {
			String name = currentTransition.getName();
			actionTransition = !(name.startsWith("sink") || name
					.startsWith("source"));
			// relevantActionTransition =
			// isRelevantActionTransition(currentTransition);
		}

		return actionTransition;
	}

	@Override
	public String getId() {
		return id;
	}

	/*
	 * @Override public void updateSituations(Collection<ISituation>
	 * newSituations) { List<ISituation> toBeRemoved = new
	 * ArrayList<ISituation>(); // First remove situations outside the current
	 * location for (ISituation oldSituation : currentSituations) { if
	 * (!newSituations.contains(oldSituation)) { removeSituation(oldSituation);
	 * toBeRemoved.add(oldSituation); } }
	 * currentSituations.removeAll(toBeRemoved);
	 * 
	 * // Then add new situations for (ISituation newSituation : newSituations)
	 * { if (!currentSituations.contains(newSituation)) {
	 * currentSituations.add(newSituation);
	 * SituationTools.combineFSMs(currentState, newSituation.getFSM()); } }
	 * 
	 * }
	 */

	/*
	 * public void updatePetriNetSituations(Collection<ISituation>
	 * newSituations) { List<ISituation> toBeRemoved = new
	 * ArrayList<ISituation>(); // First remove situations outside the current
	 * location for (ISituation oldSituation : currentSituations) { if
	 * (!newSituations.contains(oldSituation)) { removeSituation(oldSituation);
	 * toBeRemoved.add(oldSituation); } }
	 * currentSituations.removeAll(toBeRemoved);
	 * 
	 * // Then add new situations for (ISituation newSituation : newSituations)
	 * { if (!currentSituations.contains(newSituation)) {
	 * currentSituations.add(newSituation);
	 * SituationTools.combineFSMs(currentState, newSituation.getFSM()); } } }
	 */

	/*
	 * Disconnect the situation specific transitions
	 */
	// TODO: Think about cases in which multiple situations contain the same
	// transition (but with
	// different probabilities)
	/*
	 * private void removeSituation(ISituation oldSituation) {
	 * System.out.println("Removing situation: " + oldSituation.getId());
	 * 
	 * currentState.removeSituation(oldSituation, currentState);
	 * 
	 * 
	 * IPedestrianState currentConnectedState; for(IPedestrianTransition
	 * transition : oldSituation.getTransitionList()){ //Disconnect source state
	 * currentConnectedState = transition.getSourceState();
	 * if(currentConnectedState.getSituationId() !=
	 * oldSituation.getSituationId()){
	 * currentConnectedState.removeTransitionMap(oldSituation.getId());
	 * currentConnectedState.removeOutgoingTransition(transition); } //
	 * Disconnect target state currentConnectedState =
	 * transition.getTargetState(); if(currentConnectedState.getSituationId() !=
	 * oldSituation.getSituationId()){
	 * currentConnectedState.removeTransitionMap(oldSituation.getId());
	 * currentConnectedState.removeIncomingTransition(transition); }
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	@Override
	public Collection<ISituation> getCurrentSituations() {
		return currentSituations;
	}

	@Override
	public void setLastTransitions(ArrayList<Transition> transitions) {
		this.lastTransitions = transitions;
	}

	@Override
	public PedestrianDataLayer getPedestrianDataLayer() {
		return pedestrianDataLayer;
	}

	@Override
	public ArrayList<Transition> getLastTransitions() {
		return lastTransitions;
	}

	@Override
	public void clearLastTransitions() {
		lastTransitions.clear();
	}

	@Override
	public void addLastTransition(Transition transition) {
		lastTransitions.add(transition);
	}

	public class TransitionPedestrianPair {

		private Transition transition;
		private PedestrianDataLayer pedestrianDataLayer;

		public Transition getTransition() {
			return transition;
		}

		public TransitionPedestrianPair(Transition transition,
				PedestrianDataLayer pedestrianDataLayer) {

			this.transition = transition;
			this.pedestrianDataLayer = pedestrianDataLayer;
		}

		public void setTransition(Transition transition) {
			this.transition = transition;
		}

		public PedestrianDataLayer getPedestrianDataLayer() {
			return pedestrianDataLayer;
		}

		public void setPedestrianDataLayer(
				PedestrianDataLayer pedestrianDataLayer) {
			this.pedestrianDataLayer = pedestrianDataLayer;
		}

	}

}
