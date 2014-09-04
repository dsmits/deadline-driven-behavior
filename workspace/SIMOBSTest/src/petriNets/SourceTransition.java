package petriNets;

import pipe.common.dataLayer.Transition;

/**
 * @author smitsds
 * 
 *         Transition subclass with some source transition specific functionality.
 */
public class SourceTransition extends PedestrianSimTransition {

    private SinkTransition attachedSink;
    private PedestrianSimDataLayer attachedDataLayer;

    public SourceTransition(double positionXInput, double positionYInput) {
        super(positionXInput, positionYInput);
    }

    public SourceTransition(double positionXInput, double positionYInput, String idInput,
            String nameInput, double nameOffsetXInput, double nameOffsetYInput, double rateInput,
            boolean timedTransition, boolean infServer, int angleInput, int priority) {
        super(positionXInput, positionYInput, idInput, nameInput, nameOffsetXInput,
                nameOffsetYInput, rateInput, timedTransition, infServer, angleInput, priority);
    }

    public SourceTransition(Transition transition) {
        super(transition);
    }

    public void setAttachedSink(SinkTransition transition) {
        attachedSink = transition;
        // attachedDataLayer = dataLayer;
    }

    public SinkTransition getAttachedSource() {
        return attachedSink;
    }

    public void setAttachedPetriNet(PedestrianSimDataLayer attachedDataLayer) {
        this.attachedDataLayer = attachedDataLayer;
    }

    public PedestrianSimDataLayer getAttachedPetriNet() {
        return attachedDataLayer;
    }

    public boolean hasAttachedSource() {
        return attachedSink != null;
    }

}
