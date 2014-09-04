package mason;

import sim.display.Controller;
import situations.PedestrianSim;

public class PedestrianSimWithUITest {

	public static void main(String args[]){
		PedestrianSimWithUI simWithUI;
		Controller controller;
		PedestrianSim pedestrianSim;
		Thread pedestrianSimThread;


		//while(true){
			simWithUI = new PedestrianSimWithUI();
			pedestrianSim = new PedestrianSim();
			pedestrianSimThread = new Thread(pedestrianSim);
			pedestrianSimThread.start();
			controller = simWithUI.createController(); 
			simWithUI.start();

			try {
				Thread.sleep(10000);
				//Thread.sleep(1000*60*4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			simWithUI.finish();
			

		//}

	}

}
