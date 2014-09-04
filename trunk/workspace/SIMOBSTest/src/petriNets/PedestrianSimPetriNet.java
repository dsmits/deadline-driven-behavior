package petriNets;

import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.DataLayerInterface;

public class PedestrianSimPetriNet implements IPedestrianSimPetriNet {

    DataLayerInterface dataLayer;

    public PedestrianSimPetriNet(String dataLayerFile) {
        DataLayerInterface dataLayer = new DataLayer(dataLayerFile);
        init(dataLayer);
    }

    public PedestrianSimPetriNet(DataLayerInterface dataLayer) {
        init(dataLayer);
    }

    private void init(DataLayerInterface dataLayer) {
        this.dataLayer = dataLayer;
    }

    public DataLayerInterface getDataLayer() {
        return dataLayer;
    }

}
