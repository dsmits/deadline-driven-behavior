package mason;

import sim.field.continuous.Continuous2D;

public class DifferentStandStillMasonAction extends AbstractBaseMasonAction {
    private static final int STEP_NUMBER = 5;
    private static final int DEFAULT_SPEED = 3;
    private static final double DEFAULT_ROTATION = 0.1;
    //boolean active;
    int steps;
    double orientation;
    double speed;
    double rotation;

    public DifferentStandStillMasonAction() {
        init();
    }
    
    public void init(){
    	finished = false;
        steps = 0;
        speed = DEFAULT_SPEED;
        rotation = DEFAULT_ROTATION;
    }

    public void act(PedestrianSimState state, PedestrianAgent agent) {
        Continuous2D agents = getAgents(state);
        System.out.println("Executing standing still");
        System.out.println("Location of agent: " + agents.getObjectLocation(agent));
        if (steps > STEP_NUMBER) {
        init();
            finished = true;
            steps = 0;
        } else {
            finished = false;
            steps++;
        }

    }

    @Override
    public boolean isFinished() {
        return finished;
    }

}
