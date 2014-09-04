package mason;

import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

/**
 * @author smitsds
 * 
 */
public abstract class AbstractBaseMasonAction implements IMasonAction {

    protected boolean finished;
    protected double maxRotation = 0.25 * Math.PI;
    protected static final double DEFAULT_SPEED = 0.5;
    private static final String LOG_FILE_PATH = "actionlog.csv";
    
    

    public AbstractBaseMasonAction() {
        finished = true;
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    protected double limitOrientation(double orientation, double previousOrientation) {
        double newOrientation = orientation;
        if ((orientation < previousOrientation)
                && (orientation - previousOrientation) > maxRotation) {
            newOrientation = previousOrientation - maxRotation;
        } else if ((orientation > previousOrientation)
                && (orientation - newOrientation) > maxRotation) {
            newOrientation = previousOrientation + maxRotation;
        }

        return newOrientation;

    }

    protected Double2D getRelativeLocation(double rotation, double speed) {
        return new Double2D(Math.cos(rotation) * speed, Math.sin(rotation) * speed);
    }

    protected Continuous2D getAgents(PedestrianSimState state) {
        return state.agents;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
    
    

    // @Override
    // public Color getColor() {
    //
    // String actionName = this.getClass().getName();
    // Color color = new Color(actionName.hashCode());
    //
    // return color;
    // }
}
