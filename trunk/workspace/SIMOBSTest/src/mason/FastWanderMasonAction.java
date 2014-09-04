package mason;

import java.util.Random;

import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class FastWanderMasonAction extends AbstractBaseMasonAction {

    boolean active;
    Random random;
    double speed;
    int steps;
    static final int INITIAL_STEPS = 30;

    public FastWanderMasonAction() {
        random = new Random();
        speed = 1;
        steps = INITIAL_STEPS;
        active = true;
    }

    public void act(PedestrianSimState state, PedestrianAgent agent) {
    	double previousOrientation = agent.orientation2D();
    	if(steps == INITIAL_STEPS){
        	//Choose initial direction
        	previousOrientation = random.nextDouble() * 2 * Math.PI;
        }
    	
    	steps--;
        if(steps < 0){
        	active = false;
        }
    	
    	Continuous2D agents = getAgents(state);
        double currentX = agent.getLocation().getX();
        double currentY = agent.getLocation().getY();

        double newOrientation = previousOrientation + (2 * maxRotation * random.nextDouble())
                - maxRotation;
        newOrientation = limitOrientation(newOrientation, previousOrientation);

        System.out.println("New orientation: " + newOrientation / Math.PI);

        Double2D shift = getRelativeLocation(newOrientation, speed);
        Double2D newLocation = new Double2D(currentX + shift.getX(), currentY + shift.getY());

        agent.setLocation(newLocation);
        agent.setOrientation2D(newOrientation);

    }

    /*
     * public void act(Continuous2D agents, PedestrianAgent agent) { double previousOrientation =
     * agent.orientation2D(); double xDestination = agents.getWidth() * random.nextDouble(); double
     * yDestination = agents.getHeight() * random.nextDouble(); double currentX =
     * agent.getLocation().getX(); double currentY = agent.getLocation().getY(); double
     * relXDestination = xDestination - currentX; double relYDestination = yDestination - currentY;
     * 
     * double newOrientation = Math.atan2(relYDestination,relXDestination); newOrientation =
     * limitOrientation(newOrientation, previousOrientation);
     * 
     * System.out.println("New orientation: " + newOrientation/Math.PI);
     * 
     * Double2D shift = getRelativeLocation(newOrientation , speed); Double2D newLocation = new
     * Double2D(currentX + shift.getX(), currentY + shift.getY());
     * 
     * agent.setLocation(newLocation); agent.setOrientation2D(newOrientation);
     * 
     * }
     */

    @Override
    public boolean isFinished() {
        return !active;
    }

}
