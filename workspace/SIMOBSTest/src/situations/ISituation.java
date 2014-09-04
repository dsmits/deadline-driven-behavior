package situations;

import java.util.ArrayList;

import needs.INeed;
import petriNets.SituationDataLayer;

public interface ISituation {

    public String getId();

    public SituationDataLayer getSituationDataLayer();

    public void setArea(int x1, int y1, int x2, int y2);

    public void setArea(SituationArea area);

    public SituationArea getArea();

    // public List<IPedestrianState> getStateList();

    // public List<IPedestrianTransition> getTransitionList();

    // public void setSituationId(String situationId);

    public String getSituationId();

    public ArrayList<INeed> getNeeds();

    public int getEstimatedTime();

    public void setEstimatedTime(int time);

}
