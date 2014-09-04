package petriNets;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import pipe.common.dataLayer.Transition;
import time.IClock;

//TODO: Create situation datalayer with infinite amount of slots
//TODO: Remove, is not used
public class MultiSituationDataLayer extends PedestrianSimDataLayer {

    private String situationId;
    private HashMap<Integer, Slot> slots;
    private LinkedList<Slot> availableSlots;
    private int estimatedTime;
    private HashMap<Slot, Integer> estimatedTimes;

    // private HashMap<Integer, Slot> slotMap;

    public MultiSituationDataLayer(IClock clock, File pnmlFile) {
        super(clock, pnmlFile);
        situationId = pnmlFile.getName();
        init();
    }

    public MultiSituationDataLayer(IClock clock, String pnmlFileName) {
        super(clock, pnmlFileName);
        situationId = pnmlFileName;
        init();
    }

    private void init() {
        estimatedTimes = new HashMap<Slot, Integer>();
        availableSlots = new LinkedList<Slot>();
        slots = new HashMap<Integer, Slot>();
        // Assuming that there is a sink for every source, and they both are listed in numeric
        // order.
        Iterator<SourceTransition> sourceIterator = sources.iterator();
        Iterator<SinkTransition> sinkIterator = sinks.iterator();

        SourceTransition currentSource;
        SinkTransition currentSink;
        Slot currentSlot;
        // Starts at one because that is more intuitive when designing the petri net
        int currentSlotNumber = 1;

        // TODO: Make the list inverse
        while (sinkIterator.hasNext()) {
            currentSource = sourceIterator.next();
            currentSink = sinkIterator.next();
            currentSlot = new Slot(this, currentSource, currentSink, currentSlotNumber, null);
            // newSlot = new Slot(this, currentSource, currentSink, currentSlotNumber);
            slots.put(currentSlotNumber, currentSlot);
            availableSlots.push(currentSlot);
            // slotMap.put(currentSlotNumber, newSlot);
            currentSlotNumber++;
        }

    }

    public String getSituationId() {
        return situationId;
    }

    public void setSituationid(String situationId) {
        this.situationId = situationId;
    }

    /**
     * Returns a slot (a sink and source) to which a pedestrian can be attached.
     * 
     * @return An available slot
     */
    public Slot getAvailableSlot() {
        // TODO: Just a quick fix for infinite slots
        // return availableSlots.peek();

        return availableSlots.pop();
    }

    /*
     * public Slot getSlotByNumber(int slotNumber) { return slotMap.get(slotNumber); }
     */

    public void removeSlot(Slot slot) {
        super.removeSlot(slot);
        availableSlots.push(slot);
    }

    public void setEstimatedTime(Slot slot, int estimatedTime) {
        this.estimatedTimes.put(slot, estimatedTime);
    }

    public int getEstimatedTime(Slot slot) {
        return estimatedTimes.get(slot);
    }

    @Override
    public void fireTransition(Transition transition) {
        super.fireTransition(transition);
        Slot slot = getCorrespondingSlot(transition);
        if (slot != null) {
            slot.getBrain().addLastTransition(transition);
        }
    }

    private Slot getCorrespondingSlot(Transition transition) {
        try {
            String name = transition.getName();
            int slotNumber = Integer.parseInt(name.split(":")[1]);

            return slots.get(slotNumber);
        } catch (Exception e) {
            return null;
        }
    }
}
