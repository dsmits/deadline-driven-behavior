package situations;
import mason.PedestrianAgent;
import sim.field.continuous.Continuous2D;


public interface MasonAction {

	public void act(Continuous2D agents, PedestrianAgent agent);
	
}
