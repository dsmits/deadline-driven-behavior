package petriNets;

public class SlotConnection {

    Slot pedestrianSlot;
    Slot situationSlot;

    PedestrianDataLayer pedestrian;
    SituationDataLayer situation;

    public Slot getPedestrianSlot() {
        return pedestrianSlot;
    }

    public Slot getSituationSlot() {
        return situationSlot;
    }

    public SlotConnection(PedestrianDataLayer pedestrian, Slot pedestrianSlot,
            SituationDataLayer situation, Slot situationSlot) {
        this.pedestrianSlot = pedestrianSlot;
        this.situationSlot = situationSlot;
        this.pedestrian = pedestrian;
        this.situation = situation;

    }

    /**
     * Gives the petri net that this slot is attached to, so not the petri net the given slot is in.
     * 
     * @param slot
     * @return
     */
    public PedestrianSimDataLayer getAttachedPetriNet(Slot slot) {
        PedestrianSimDataLayer attachedNet = null;

        if (slot.equals(pedestrianSlot)) {
            attachedNet = situation;
        } else if (slot.equals(situationSlot)) {
            attachedNet = pedestrian;
        }

        return attachedNet;
    }
    
   
     
    public PedestrianSimDataLayer getPetriNetOfSlot(Slot slot) {
        PedestrianSimDataLayer attachedNet = null;

        if (slot.equals(situationSlot)) {
            attachedNet = situation;
        } else if (slot.equals(pedestrianSlot)) {
            attachedNet = pedestrian;
        }

        return attachedNet;
    }

    public Slot getAttachedSlot(Slot slot) {
        Slot attachedSlot = null;

        if (slot.equals(pedestrianSlot)) {
            attachedSlot = situationSlot;
        } else if (slot.equals(situationSlot)) {
            attachedSlot = pedestrianSlot;
        }
        return attachedSlot;
    }

    public boolean connectsPedestrian(PedestrianDataLayer pedestrian) {
        return pedestrianSlot.getDataLayer() == pedestrian;
    }

    public boolean connectsSituation(SituationDataLayer situation) {
        return ((SituationDataLayer) situationSlot.getDataLayer()).getSituationId().equals(
                situation.getSituationId());
    }

    public String toString() {
        String slotConnectionString = "Pedestrian: " + pedestrian;
        slotConnectionString += "\nPedestrianSlot: " + pedestrianSlot;
        slotConnectionString += "\nSituation: " + situation;
        slotConnectionString += "\nSituationSlot: " + situationSlot;
        return slotConnectionString;
    }

}
