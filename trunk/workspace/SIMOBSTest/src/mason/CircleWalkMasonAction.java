package mason;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;


public class CircleWalkMasonAction extends AbstractBaseMasonAction{
	private static final int STEP_NUMBER = 500;
	private static final int DEFAULT_SPEED = 3;
	private static final double DEFAULT_ROTATION = 0.1;
	boolean active;
	int steps;
	double orientation;
	double speed;
	double rotation;
	
	public CircleWalkMasonAction(){
		init();
	}
	
	public void init(){
		active = false;
		steps = 0;
		speed = DEFAULT_SPEED;
		rotation = DEFAULT_ROTATION;
	}

	public void act(PedestrianSimState state, PedestrianAgent agent) {
		Continuous2D agents = state.agents;
		System.out.println("Executing circlewalk\n\n");
		if(steps > STEP_NUMBER){
			active = false;
			steps = 0;			
		}else{
			steps++;
			double newOrientation = (agent.orientation2D() + rotation )% 2 * Math.PI;
			Double2D location = agent.getLocation();
			Double2D shift = getRelativeLocation(newOrientation , speed);
			Double2D newLocation = new Double2D(location.getX() + shift.getX(), location.getY() + shift.getY());
			
			agents.setObjectLocation(agent, newLocation);
			agent.setOrientation2D(newOrientation);
		}
		
	}
	
//	private Double2D getRelativeLocation(double rotation, double speed){
//		return new Double2D(Math.cos(rotation) * speed, Math.sin(rotation) * speed);
//	}
	


	@Override
	public boolean isFinished() {
		return !active;
	}

}
