package situations;
import java.util.Random;


public class RandomBrain implements IPedestrianBrain {

	@Override
	public BaseAction step() {
		Random random = new Random();		
			
		BaseAction action = new BaseAction(random.nextDouble()*2 * Math.PI, random.nextDouble());
		
		return action;

		
	}

}
