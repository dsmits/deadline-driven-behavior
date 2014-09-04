package situations;

import java.io.Serializable;

//TODO: Remove class, is not used any more
public class Observation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7428452587801296755L;

    String pedestrianId;

    double locationX;
    double locationY;

    public Observation(double x, double y) {
        locationX = x;
        locationY = y;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public String getPedestrianId() {
        return pedestrianId;
    }

    public void setPedestrianId(String pedestrianId) {
        this.pedestrianId = pedestrianId;
    }

}
