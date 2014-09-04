package mason;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;


public class AskQuestionMasonAction extends AbstractBaseMasonAction{
	private static final int STEP_NUMBER = 5;
	private static final int DEFAULT_SPEED = 3;
	private static final double DEFAULT_ROTATION = 0.1;
	boolean active;
	int steps;
	double orientation;
	double speed;
	double rotation;
	
	public AskQuestionMasonAction(){
		active = false;
		steps = 0;
		speed = DEFAULT_SPEED;
		rotation = DEFAULT_ROTATION;
	}

	public void act(Continuous2D agents, PedestrianAgent agent) {
		System.out.println("Executing standing still");
		System.out.println("Location of agent: " + agents.getObjectLocation(agent));
		if(steps > STEP_NUMBER){
			active = false;
			steps = 0;			
		}else{
			active = true;
			steps++;
		}
		
	}
	
	@Override
	public boolean isFinished() {
		return !active;
	}

}
