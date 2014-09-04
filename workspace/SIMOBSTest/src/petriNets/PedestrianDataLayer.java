package petriNets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import pipe.common.dataLayer.ArcFactory;
import pipe.common.dataLayer.DataLayerWriter;
import pipe.common.dataLayer.Marking;
import pipe.common.dataLayer.NormalArc;
import pipe.common.dataLayer.Place;
import pipe.common.dataLayer.Transition;
import situations.IPedestrianBrain;
import time.IClock;
import time.StepClock;

public class PedestrianDataLayer extends PedestrianSimDataLayer {

    private Place basePlace;
    private IPedestrianBrain brain;


    // private IClock goalTime;

    // private HashMap<String, SituationDataLayer> situationNets;

    // protected HashMap<String, Transition> situationSlots;

    public IPedestrianBrain getBrain() {
        return brain;
    }

    public PedestrianDataLayer(IClock clock, IPedestrianBrain brain) {
        super(clock);
        init(clock, new StepClock(DEADLINE), brain);
    }

    public PedestrianDataLayer(IClock clock, File pnmlFile, IPedestrianBrain brain) {
        super(clock, pnmlFile);
        init(clock, new StepClock(DEADLINE), brain);
    }

    public PedestrianDataLayer(IClock clock, String pnmlFile, IPedestrianBrain brain) {
        super(clock, new File(pnmlFile));
        init(clock, new StepClock(DEADLINE), brain);
    }

    public PedestrianDataLayer(IClock clock, String pnmlFileName, IClock goalTime,
            IPedestrianBrain brain) {
        super(clock, goalTime, pnmlFileName);
        init(clock, goalTime, brain);
    }

    /*
     * public void addSituation(SituationDataLayer situation) {
     * getSituationNets().put(situation.getSituationId(), situation);
     * 
     * }
     */

    private void init(IClock clock, IClock goalTime, IPedestrianBrain brain) {
        this.brain = brain;
        this.clock = clock;
        this.goalTime = goalTime;
        basePlace = getPlaceByName("base");
    }

    /**
     * Creates new source and sink (slot) that can be connected to a situation.
     * 
     * @return A newly created slot
     */
    public Slot createSlot(double rate) {
        Slot slot = null;
        try {
            //DataLayerWriter writer1 = new DataLayerWriter(this);

            //writer1.savePNML(new File("petrinets/pedestrianNetBefore.xml"));

            SinkTransition sink = new SinkTransition(0, 0);
            String sinkName = "sink:" + slotNameCounter;
            sink.setName(sinkName);
            sink.setName(sinkName);
            sink.setRate(rate);

            addPetriNetObject(sink);
            NormalArc outwardsArc = ArcFactory.createNormalArc(basePlace);
            // outwardsArc.addLabelToContainer();

            // TODO: Add support for tokens of different color;
            Marking m = new Marking(getActiveTokenClass());
            m.setCurrentMarking(1);
            LinkedList<Marking> markings = new LinkedList<Marking>();
            markings.add(m);
            outwardsArc.setWeight(markings);

            addPetriNetObject(outwardsArc);

            outwardsArc.setTarget(sink);
            sink.addConnectFrom(outwardsArc);
            basePlace.addConnectTo(outwardsArc);

            SourceTransition source = new SourceTransition(0, 0);
            String sourceName = "source:" + slotNameCounter;
            source.setName(sourceName);
            source.setId(sourceName);
            addPetriNetObject(source);
            NormalArc inwardsArc = ArcFactory.createNormalArc(source);
            // inwardsArc.addLabelToContainer(;
            markings = new LinkedList<Marking>();

            markings.add(m);
            inwardsArc.setWeight(markings);
            addPetriNetObject(inwardsArc);
            inwardsArc.setTarget(basePlace);
            source.addConnectTo(inwardsArc);
            basePlace.addConnectFrom(inwardsArc);
            slot = new Slot(this, source, sink, slotNameCounter, brain);

            slotNameCounter++;
            setChanged();
           DataLayerWriter writer2 = new DataLayerWriter(this);
           writer2.savePNML(new File("petrinets/pedestrianNetAfter.xml"));
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return slot;
    }


    /*
     * public void setSituationNets(HashMap<String, SituationDataLayer> situationNets) {
     * this.situationNets = situationNets; }
     * 
     * public HashMap<String, SituationDataLayer> getSituationNets() { return situationNets; }
     */

}
