package petriNets;

import situations.AbstractBaseSituation;

public class ExclusivePetriNetSituation extends AbstractBaseSituation {

    SituationDataLayer dataLayer;

    public ExclusivePetriNetSituation(String situationId, String fileName) {
        super(situationId);
        this.dataLayer = new SituationDataLayer(null, fileName, false);
    }

    public ExclusivePetriNetSituation(String situationId, SituationDataLayer dataLayer) {
        super(situationId);
        this.dataLayer = dataLayer;
    }

    @Override
    public SituationDataLayer getSituationDataLayer() {

        // System.out.println("URI: " + dataLayer.getURI());
        SituationDataLayer situationDataLayer = new SituationDataLayer(dataLayer.clock,
                dataLayer.getFullURI(), false);
        
        // (SituationDataLayer) dataLayer.clone();
        
        
        return situationDataLayer;
    }

}
