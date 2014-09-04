package petriNets;

import situations.AbstractBaseSituation;

public class SinglePetriNetSituation extends AbstractBaseSituation {

    SituationDataLayer dataLayer;

    public SinglePetriNetSituation(String situationId, String fileName) {
        super(situationId);
        this.dataLayer = new SituationDataLayer(fileName);
    }

    public SinglePetriNetSituation(String situationId, SituationDataLayer dataLayer) {
        super(situationId);
        this.dataLayer = dataLayer;
    }

    @Override
    public SituationDataLayer getSituationDataLayer() {
        return dataLayer;
    }

}
