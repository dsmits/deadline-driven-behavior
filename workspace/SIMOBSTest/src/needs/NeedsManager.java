package needs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Document;

import situations.ISituation;

public class NeedsManager {

    private ArrayList<INeed> needs;

    public NeedsManager(Document config) {
        needs = new ArrayList<INeed>();
    }

    private Collection<INeed> collectNeeds(List<ISituation> situations) {
        ArrayList<INeed> currentNeedList;

        for (ISituation situation : situations) {
            currentNeedList = situation.getNeeds();
            needs.addAll(currentNeedList);
        }

    }

    /**
     * Distributes the needs over the pedestrians, sampled as a normal distribution from the need
     * intensity at the current time.
     */
    public void distributeNeeds() {

    }

}
