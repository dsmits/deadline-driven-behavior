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
    protected static final double MAX_X_COORDINATE = 100;
    protected static final double MAX_Y_COORDINATE = 100;
    

    public AbstractBaseMasonAction() {
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
    
    public void setFinished(boolean finished){
    	this.finished = finished;
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
    
    protected boolean withinBounds(double x, double y){
    	return x > 0 && x < MAX_X_COORDINATE && y > 0 && y < MAX_Y_COORDINATE;
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
