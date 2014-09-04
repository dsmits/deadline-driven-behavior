package petriNets;

import situations.AbstractBaseSituation;

public class SharedPetriNetSituation extends AbstractBaseSituation {

    SituationDataLayer dataLayer;

    public SharedPetriNetSituation(String situationId, String fileName) {
        super(situationId);
        this.dataLayer = new SituationDataLayer(null, fileName);
    }

    public SharedPetriNetSituation(String situationId, SituationDataLayer dataLayer) {
        super(situationId);
        this.dataLayer = dataLayer;
    }

    @Override
    public SituationDataLayer getSituationDataLayer() {
        return dataLayer;
    }

}
