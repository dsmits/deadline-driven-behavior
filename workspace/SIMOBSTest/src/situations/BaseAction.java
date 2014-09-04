package situations;

public class BaseAction {

	private double rotation;
	private double speed;
	
	public BaseAction(){
		this(0,0);
	
	}
	
	public BaseAction(double rotation, double speed){
		this.rotation = rotation;
		this.speed = speed;
	}
	
	public double getRotation() {
		return rotation;
	}
	public double getSpeed() {
		return speed;
	}	
	
	
}
