package situations;

/**
 * Holds a collection of IPedestrianManagers that will together update an IPedestrianBrain to be
 * ready for the next step.
 * 
 * @author smitsds
 */
public interface IManagerCollection {

    /**
     * 
     * @param brain
     */
    public void step(IPedestrianBrain brain);

}
