package situations;

import java.util.ArrayList;
import java.util.Collection;

import petriNets.PedestrianDataLayer;
import pipe.common.dataLayer.Transition;

/**
 * @author smitsds
 * 
 *         IPedestrianBrain is the interface of the server-side decision making unit of a single
 *         pedestrian. The most important functions here are step() which decides on an action based
 *         on the current situations, and updateSituations(Collection<ISituation> situations), which
 *         modifies the pedestrians FSM based on which situations the pedestrian is currently in.
 *         The PedestrianManager is supposed to first update the situations first, and then retrieve
 *         the appropriate action.
 */
public interface IPedestrianBrain {

    /**
     * Step decides which action should be taken given the current FSM.
     * 
     * @return An action, as a string. It is the task of the client to interpret this action
     *         appropriately.
     */
    public String step();

    /**
     * In order to differentiate between the different pedestrians, and enable the system to link
     * server- and client-side pedestrian together, all pedestrians should have a unique id.
     * 
     * @return A unique id.
     */
    public String getId();

    /**
     * Updates the FSM of the pedestrian given a new collection of situations. It could be that this
     * list overlaps with the previous list that has been given to the pedestrian. This will be
     * checked, and only the changes between the new and old list will be used to adapt the
     * pedestrian's FSM.
     * 
     * 
     * @param situations
     *            The new list of situations the pedestrian is currently in.
     */
    // public void updateSituations(Collection<ISituation> situations);

    public void setLastTransitions(ArrayList<Transition> transition);

    public ArrayList<Transition> getLastTransitions();

    // public void addLastTransition(Transition transition);

    public Collection<ISituation> getCurrentSituations();

    public PedestrianDataLayer getPedestrianDataLayer();

    public long getRemainingTime();

    public void clearLastTransitions();

    void addLastTransition(Transition transition);

}
