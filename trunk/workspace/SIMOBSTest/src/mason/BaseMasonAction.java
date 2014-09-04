package mason;

import situations.IPedestrianAction;

public class BaseMasonAction implements IPedestrianAction{

	private double rotation;
	private double speed;
	
	public BaseMasonAction(){
		this(0,0);
	}
	
	public BaseMasonAction(double rotation, double speed){
		this.rotation = rotation;
		this.speed = speed;
	}
	
	public double getRotation() {
		return rotation;
	}
	public double getSpeed() {
		return speed;
	}

	@Override
	public void act(Object object) {
		
		
	}	
	
	
}
