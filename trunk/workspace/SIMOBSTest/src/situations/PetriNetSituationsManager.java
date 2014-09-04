package situations;

import java.util.ArrayList;

public class PetriNetSituationsManager implements IManagerCollection {

    ArrayList<IPedestrianSimManager> managers;

    public PetriNetSituationsManager() {
        init();
    }

    private void init() {
        managers = new ArrayList<IPedestrianSimManager>();
    }

    @Override
    public void step(IPedestrianBrain brain) {
        for (IPedestrianSimManager manager : managers) {
            manager.step(brain);
        }

    }
}
