package situations;

import java.util.ArrayList;

import needs.INeed;

public abstract class AbstractBaseSituation implements ISituation {
    private SituationArea situationArea;

    // protected List<IPedestrianState> states;
    // protected List<IPedestrianTransition> transitions;
    protected String situationId;
    protected int estimatedTime;

    public AbstractBaseSituation(String situationId) {
        System.out.println("Initializing " + this.getClass().getName() + "...");
        // states = new ArrayList<IPedestrianState>();
        // transitions = new ArrayList<IPedestrianTransition>();
        this.situationId = situationId;
    }

    // @Override
    // public List<IPedestrianState> getStateList() {
    // return states;
    // }

    // @Override
    // public List<IPedestrianTransition> getTransitionList() {
    // return transitions;
    // }

    @Override
    public void setArea(int x1, int y1, int x2, int y2) {
        SituationArea area = new SituationArea(x1, y1, x2, y2);
        setArea(area);

    }

    @Override
    public void setArea(SituationArea area) {
        situationArea = area;

    }

    @Override
    public SituationArea getArea() {
        if (situationArea == null) {
            throw new RuntimeException("Area has not been set");
        }
        return situationArea;
    }

    /*
     * @Override public void setSituationId(String situationId){ this.situationId = situationId; }
     */

    @Override
    public String getSituationId() {
        return situationId;
    }

    public boolean equals(ISituation situation) {
        return this.getSituationId() == situation.getSituationId();
    }

    @Override
    public ArrayList<INeed> getNeeds() {
        return new ArrayList<INeed>();
    }

    @Override
    public String getId() {
        return situationId;
    }

    @Override
    public int getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public void setEstimatedTime(int time) {
        estimatedTime = time;
    }

}
