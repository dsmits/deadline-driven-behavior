package petriNets;

import pipe.common.dataLayer.Transition;

public class PedestrianSimTransition extends Transition {

    /**
     * 
     */
    private static final long serialVersionUID = 6030944205282487264L;

    // protected int slotNumber;
    protected Slot slot;

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public PedestrianSimTransition(double positionXInput, double positionYInput) {
        super(positionXInput, positionYInput);
        slot = null;
    }

    public PedestrianSimTransition(double positionXInput, double positionYInput, Slot slot) {
        super(positionXInput, positionYInput);
        this.slot = slot;
    }

    public PedestrianSimTransition(Transition transition) {
        this(transition.getPositionX(), transition.getPositionY(), transition.getId(), transition
                .getName(), transition.getNameOffsetX(), transition.getNameOffsetY(), transition
                .getRate(), transition.isTimed(), transition.isInfiniteServer(), transition
                .getAngle(), transition.getPriority());

    }

    public PedestrianSimTransition(double positionXInput, double positionYInput, String idInput,
            String nameInput, double nameOffsetXInput, double nameOffsetYInput, double rateInput,
            boolean timedTransition, boolean infServer, int angleInput, int priority) {
        super(positionXInput, positionYInput, idInput, nameInput, nameOffsetXInput,
                nameOffsetYInput, rateInput, timedTransition, infServer, angleInput, priority);
        slot = null;
        // getSlotNumber(nameInput);
    }

    protected int getSlotNumber(String name) {
        try {
            String slotNumberString = name.split(":")[1];
            return Integer.parseInt(slotNumberString);
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

}
