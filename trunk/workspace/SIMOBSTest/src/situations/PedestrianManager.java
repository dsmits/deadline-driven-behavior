package situations;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import protobuf.ObservationMessage;
import time.IClock;
import time.StepClock;

/**
 * @author smitsds
 * 
 */
public class PedestrianManager {

    Map<String, IPedestrianBrain> pedestrians;
    Document config;
    SituationManager situationManager;
    IClock clock;

    public PedestrianManager(Document config, IClock clock) {
        this.clock = clock;
        pedestrians = new HashMap<String, IPedestrianBrain>();
        situationManager = new SituationManager(config, clock);
        this.config = config;
    }

    /**
     * Adds a new pedestrian to the manager.
     * 
     * @param pedestrian
     *            The pedestrian that needs to be added to the simulation.
     */
    public void addPedestrian(IPedestrianBrain pedestrian) {
        pedestrians.put(pedestrian.getId(), pedestrian);
    }

    /**
     * Method addSituations is
     * 
     * @param pedestrianId
     * @param situations
     */
    /*
     * public void addSituations(String pedestrianId, List<ISituation> situations) {
     * IPedestrianBrain pedestrian = pedestrians.get(pedestrianId);
     * situationManager.updateSituations(pedestrian, situations);
     * 
     * }
     */

    /**
     * Step checks the Petri net of the current pedestrian and computes the next action this
     * pedestrian has to take.
     * 
     * @param observation
     *            The current observation of the agent.
     * @return The appropriate action.
     */
    public String step(ObservationMessage.Observation observation) {

        String id = observation.getPedestrianId();

        if (!pedestrians.containsKey(id)) {
            addPedestrian(new SimplePedestrianBrain(id, situationManager, clock));
        }

        IPedestrianBrain pedestrianBrain = pedestrians.get(id);
        System.out.println("Pedestrian size: " + pedestrians.size());

        // Modify pedestrianBrain behavior so it reflects the latest situations.
        situationManager.updateSituations(pedestrianBrain, observation.getLocationX(),
                observation.getLocationY());
        situationManager.step(pedestrianBrain, observation);
        String action = pedestrianBrain.step();
        return action;
    }

    /**
     * Method updateSituations checks which situations the pedestrian is currently in and modifies
     * the pedestrian's FSM so that it incorporates the FSMs of the relevant situations.
     * 
     * @param pedestrianId
     *            The unique id for the pedestrian.
     * @param currentSituations
     *            The situations the pedestrian is currently in.
     */
    /*
     * public void updateSituations(String pedestrianId, List<ISituation> currentSituations) {
     * 
     * if (!pedestrians.containsKey(pedestrianId)) { pedestrians.put(pedestrianId, new
     * SimplePedestrianBrain(pedestrianId)); }
     * 
     * IPedestrianBrain pedestrian = pedestrians.get(pedestrianId);
     * pedestrian.updateSituations(currentSituations);
     * 
     * }
     */

}
