package petriNets;

import pipe.common.dataLayer.Transition;

public class SinkTransition extends PedestrianSimTransition {

    private SourceTransition attachedSource;

    public SinkTransition(double positionXInput, double positionYInput) {
        super(positionXInput, positionYInput);
    }

    public SinkTransition(double positionXInput, double positionYInput, String idInput,
            String nameInput, double nameOffsetXInput, double nameOffsetYInput, double rateInput,
            boolean timedTransition, boolean infServer, int angleInput, int priority) {
        super(positionXInput, positionYInput, idInput, nameInput, nameOffsetXInput,
                nameOffsetYInput, rateInput, timedTransition, infServer, angleInput, priority);
    }

    public SinkTransition(Transition transition) {
        super(transition);
    }

    /*
     * TODO: Think about whether source and sink transition should be attached to each other. If the
     * whole system is viewed as one large petri net, it should not be possible to attach
     * transitions to one another. On the other hand, in the examples given in the papers, sources
     * and sink transitions are used as such (I think).
     */
    public void setAttachedSource(SourceTransition transition) {
        attachedSource = transition;
    }

    public SourceTransition getAttachedSource() {
        return attachedSource;
    }

    public boolean hasAttachedSource() {
        return attachedSource != null;
    }

}
