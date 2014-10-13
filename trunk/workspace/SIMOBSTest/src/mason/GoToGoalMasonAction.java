package mason;

import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class GoToGoalMasonAction extends AbstractBaseMasonAction {
    private static final int GOAL_LOCATION_X = 2;
    private static final int GOAL_LOCATION_Y = 2;
    private static final int MARGIN = 1;
    private double speed;

    public GoToGoalMasonAction() {
    	init();
    }
    
    public void init(){
    	   speed = DEFAULT_SPEED;
    }

    @Override
    public void act(PedestrianSimState state, PedestrianAgent agent) {
        Continuous2D agents = getAgents(state);
        double previousOrientation = agent.orientation2D();
        double currentX = agent.getLocation().getX();
        double currentY = agent.getLocation().getY();
        boolean xIsNearGoal = (Math.abs(currentX - GOAL_LOCATION_X) - MARGIN) <= 0;
        boolean yIsNearGoal = (Math.abs(currentY - GOAL_LOCATION_Y) - MARGIN) <= 0;

        boolean hasReachedGoal = xIsNearGoal && yIsNearGoal;

        if (!hasReachedGoal) {
            finished = false;

            double newOrientation = Math.atan2((GOAL_LOCATION_Y - currentY),
                    (GOAL_LOCATION_X - currentX));

            newOrientation = limitOrientation(newOrientation, previousOrientation);

//            System.out.println("New orientation: " + newOrientation / Math.PI);

            Double2D shift = getRelativeLocation(newOrientation, speed);
            Double2D newLocation = new Double2D(currentX + shift.getX(), currentY + shift.getY());

            agent.setLocation(newLocation);
            agent.setOrientation2D(newOrientation);

        } else {
            finished = true;
        }

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
