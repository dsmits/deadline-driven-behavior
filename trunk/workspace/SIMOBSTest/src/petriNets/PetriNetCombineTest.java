package petriNets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import pipe.common.dataLayer.Arc;
import pipe.common.dataLayer.ArcFactory;
import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.DataLayerInterface;
import pipe.common.dataLayer.DataLayerWriter;
import pipe.common.dataLayer.NormalArc;
import pipe.common.dataLayer.PetriNetObject;
import pipe.common.dataLayer.Place;
import pipe.common.dataLayer.Transition;

public class PetriNetCombineTest {

    public DataLayerInterface combineNets(DataLayerInterface pedestrianNet,
            DataLayerInterface situationNet) {

        DataLayerInterface newNet = pedestrianNet;

        NormalArc slotArc = getSlotArc(situationNet);

        Place basePlace = newNet.getPlaceByName("base");

        basePlace.addConnectTo(slotArc);

        slotArc.setSource(basePlace);
        newNet.addArc((NormalArc) slotArc);
        

        addAllPetriNetObjects(newNet, situationNet);
        newNet.removePetriNetObject(slotArc);
        return newNet;
    }

    private void addAllPetriNetObjects(DataLayerInterface newNet, DataLayerInterface situationNet) {

        Iterator<PetriNetObject> petrinetObjectIterator = situationNet.getPetriNetObjects();

        while (petrinetObjectIterator.hasNext()) {
            newNet.addPetriNetObject(petrinetObjectIterator.next());
        }

    }

    private NormalArc getSlotArc(DataLayerInterface situationNet) {
        Transition slotTransition = situationNet.getTransitionByName("source1");
        NormalArc slotArc = ArcFactory.createNormalArc(slotTransition);
        slotArc.setTarget(slotTransition);
        slotTransition.addConnectFrom(slotArc);

        Arc arc = (Arc) slotTransition.getArcsFrom().next();
        slotArc.setWeight(arc.getWeight());
        return slotArc;
    }

    public static void main(String args[]) {
        PetriNetCombineTest combineTest = new PetriNetCombineTest();

        DataLayer pedestrianNet = new DataLayer("./petrinets/pedestrianNet.xml");
        DataLayer situationNet = new DataLayer("./petrinets/situationNet.xml");

        DataLayerInterface combinedNet = combineTest.combineNets(pedestrianNet, situationNet);

        DataLayerWriter writer = new DataLayerWriter(combinedNet);

        try {
            writer.savePNML(new File("./petriNets/combinedNet.xml"));
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

    }
}
