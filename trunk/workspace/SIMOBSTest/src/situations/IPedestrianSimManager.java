package situations;

import protobuf.ObservationMessage.Observation;

public interface IPedestrianSimManager {

    /**
     * Modifies the pedestrian brain so it behaves according to the latest petri net configuration.
     * When step has not been called before for this IPedestrianBrain.
     * 
     * @param brain
     */
//    public void step(IPedestrianBrain brain);

	void step(IPedestrianBrain pedestrian, Observation observation);

}
