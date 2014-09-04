package petriNets;

import situations.AbstractBaseSituation;

public class PetriNetSituation extends AbstractBaseSituation {

    SituationDataLayer dataLayer;

    public PetriNetSituation(String situationId, String fileName) {
        super(situationId);
        this.dataLayer = new SituationDataLayer(fileName);
    }

    public PetriNetSituation(String situationId, SituationDataLayer dataLayer) {
        super(situationId);
        this.dataLayer = dataLayer;
    }

    @Override
    public SituationDataLayer getSituationDataLayer() {
        return dataLayer;
    }

}
