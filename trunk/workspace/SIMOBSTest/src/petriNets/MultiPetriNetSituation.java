package petriNets;

import pipe.common.dataLayer.DataLayer;
import situations.AbstractBaseSituation;

public class MultiPetriNetSituation extends AbstractBaseSituation {

    SituationDataLayer dataLayer;

    public MultiPetriNetSituation(String situationId, String fileName) {
        super(situationId);
        this.dataLayer = new SituationDataLayer(fileName);
    }

    public MultiPetriNetSituation(String situationId, SituationDataLayer dataLayer) {
        super(situationId);
        this.dataLayer = dataLayer;
    }

    @Override
    public SituationDataLayer getSituationDataLayer() {
        DataLayer situationDataLayer = dataLayer.clone();
        return (SituationDataLayer) dataLayer.clone();
    }

}
