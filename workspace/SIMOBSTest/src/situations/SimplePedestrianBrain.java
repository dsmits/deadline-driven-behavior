package situations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

import petriNets.DataLayerLogger;
import petriNets.GoalPlace;
import petriNets.PedestrianDataLayer;
import pipe.common.dataLayer.Arc;
import pipe.common.dataLayer.Place;
import pipe.common.dataLayer.Transition;
import time.IClock;

public class SimplePedestrianBrain extends AbstractBasePedestrianBrain {
    // private String id;
    // protected IPedestrianState currentState;

    private GoalPlace goalPlace;

    // protected ArrayList<Transition> lastFiredTransitions;

    public SimplePedestrianBrain(String id, Observer observer, IClock clock) {
        super(id, clock);
        buildPetriNet(observer);
        try {
            dataLayerLogger = new DataLayerLogger(pedestrianDataLayer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * private void buildFSM() { baseState = new FreeState(id); IPedestrianTransition
     * wanderTransition = new WanderTransition(id);
     * baseState.addOutgoingTransition(wanderTransition);
     * baseState.addIncomingTransition(wanderTransition); // TODO: Do something so first
     * transitionmap is generated automatically or can't be // forgotten. TransitionMap
     * transitionMap = new TransitionMap(); transitionMap.addTransition(wanderTransition, 1);
     * baseState.addTransitionMap("base", transitionMap); currentState = baseState; }
     */

    private void buildPetriNet(Observer observer) {
        pedestrianDataLayer = new PedestrianDataLayer(clock,
                "petrinets/rotterdam/rotterdamPedestrianNet.xml", this.goalTime, this);
        pedestrianDataLayer.addObserver(observer);

        try {
            goalPlace = getGoalPlace(pedestrianDataLayer);
            GoalPlace convertedGoalPlace = new GoalPlace(goalPlace);
            replacePlace(pedestrianDataLayer, goalPlace, convertedGoalPlace);
        } catch (NullPointerException e) {
            System.err.println("No goal place defined");
            e.printStackTrace();
        }

    }

    private void replacePlace(PedestrianDataLayer pedestrianDataLayer, Place place1, Place place2) {
        Arc currentArc;
        // TODO: Check if getArcsTo works how I think it works.
        Iterator outwardsArcIterator = place1.getArcsTo();
        while (outwardsArcIterator.hasNext()) {
            currentArc = (Arc) outwardsArcIterator.next();
            currentArc.setSource(place2);
            place2.addConnectTo(currentArc);

        }
        Iterator inwardsArcIterator = place1.getArcsFrom();

        while (inwardsArcIterator.hasNext()) {
            currentArc = (Arc) inwardsArcIterator.next();
            currentArc.setTarget(place2);
            place2.addConnectFrom(currentArc);
        }

        pedestrianDataLayer.removePetriNetObject(place1);

    }

    private GoalPlace getGoalPlace(PedestrianDataLayer pedestrianDataLayer) {
        Place goalPlace = pedestrianDataLayer.getPlaceByName("goal");
        GoalPlace convertedGoalPlace;
        if (!(goalPlace instanceof GoalPlace)) {
            convertedGoalPlace = new GoalPlace(goalPlace);
        } else {
            convertedGoalPlace = (GoalPlace) goalPlace;
        }
        return convertedGoalPlace;

    }

    private Place getBasePlace(PedestrianDataLayer pedestrianDataLayer) {
        return pedestrianDataLayer.getPlaceByName("base");
    }

    public void setLastFiredTransitions(ArrayList<Transition> lastFiredTransitions) {
        this.lastTransitions = lastFiredTransitions;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Computes time to sink or goal
     * 
     * @return
     */
    private int computeTimeToEnd() {
        return 0;
    }

    /*
     * public static void main(String args[]) { SimplePedestrianBrain brain = new
     * SimplePedestrianBrain("1");
     * 
     * }
     */
}
