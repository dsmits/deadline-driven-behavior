package petriNets;

import situations.IPedestrianBrain;

/**
 * 
 * @author smitsds
 * 
 */
public class Slot {

    private PedestrianSimDataLayer dataLayer;
    // TODO: Rename to source and sink?
    private SourceTransition situationSource;
    private SinkTransition situationSink;
    private int slotNumber;
    private IPedestrianBrain brain;

    // TODO: Find a more standardized way to connect slots, at the moment it's rather arbitrary.
    public Slot(PedestrianSimDataLayer dataLayer, SourceTransition situationSource,
            SinkTransition situationSink, int slotNumber, IPedestrianBrain brain) {
        this.dataLayer = dataLayer;
        this.situationSource = situationSource;
        this.situationSink = situationSink;
        this.situationSink.setSlot(this);
        this.situationSource.setSlot(this);
        this.brain = brain;
        // this.situationSink.setAttachedSource(this.situationSource);
        // this.situationSource.setAttachedSink(this.situationSink, dataLayer);
        this.setSlotNumber(slotNumber);
    }

    public PedestrianSimDataLayer getDataLayer() {
        return dataLayer;
    }

    public void setDataLayer(PedestrianSimDataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    public SourceTransition getSituationSource() {
        return situationSource;
    }

    public void setSituationSource(SourceTransition situationSource) {
        this.situationSource = situationSource;
    }

    public SinkTransition getSituationSink() {
        return situationSink;
    }

    public void setSituationSink(SinkTransition situationSink) {
        this.situationSink = situationSink;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public IPedestrianBrain getBrain() {
        return brain;
    }

    public void setBrain(IPedestrianBrain brain) {
        this.brain = brain;
    }

    public void clearBrain() {
        this.brain = null;
    }
    
    public String toString(){
    	return "Source: " + situationSource + " Sink: " + situationSink;    	
    }

}
