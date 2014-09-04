package situations;

import java.util.List;
import java.util.Map;
import java.util.Set;

import fsm.IPedestrianState;
import fsm.IPedestrianTransition;
import fsm.TransitionMap;

/* TODO: Obsolete */
public class SituationTools {

    /*
     * State 1 and 2 are supposed to be equal.
     */
    public static IPedestrianState combineFSMs(IPedestrianState state1, IPedestrianState state2) {
        List<IPedestrianTransition> newIncomingTransitions = state1.getIncomingTransitions();
        List<IPedestrianTransition> incomingTransitions2 = state2.getIncomingTransitions();
        List<IPedestrianTransition> newOutgoingTransitions = state1.getOutgoingTransitions();
        List<IPedestrianTransition> outgoingTransitions2 = state2.getOutgoingTransitions();

        for (IPedestrianTransition transition : incomingTransitions2) {
            if (!newIncomingTransitions.contains(transition)) {
                state1.addOutgoingTransition(transition);
            }
        }

        for (IPedestrianTransition transition : outgoingTransitions2) {
            if (newOutgoingTransitions.contains(transition)) {
                state1.addIncomingTransition(transition);
            }
        }

        state1.addTransitionMaps(state2.getTransitionMaps());
        state1.addSituationIds(state2.getSituationIds());
        // TODO: Prevent state 2 from being added in the first place
        state1.getStates().remove(state2);
        return state1;
    }

    /**
     * Combines the different transitionProbabilities given by situations and pedestrians. When a
     * transition is available in more than one list, those probabilities are multiplied.
     * 
     * @param transitionMaps
     * @return
     */
    public static TransitionMap getCombinedTransitionProbabilities(
            Map<String, TransitionMap> transitionMaps) {
        Set<String> keys = transitionMaps.keySet();
        TransitionMap combinedMap = new TransitionMap();
        Set<IPedestrianTransition> transitions;
        double oldProb;

        for (String key : keys) {
            TransitionMap currentTransitionMap = transitionMaps.get(key);
            transitions = currentTransitionMap.keySet();
            for (IPedestrianTransition transition : transitions) {
                if (combinedMap.contains(transition)) {
                    oldProb = combinedMap.get(transition);
                    combinedMap.put(transition, oldProb * currentTransitionMap.get(transition));
                } else {
                    combinedMap.put(transition, currentTransitionMap.get(transition));
                }

            }
        }

        return combinedMap;
    }

}
