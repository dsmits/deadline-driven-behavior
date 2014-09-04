package situations;

import java.util.List;

/**
 * An object that holds information about which situations are present at every location in the
 * environment.
 * 
 */
public interface ISituationCollection {

    /**
     * Add a new situation to the collection.
     * 
     * @param situation
     *            The situation to be added. This ISituation object holds the information about
     *            where this situation is located.
     */
    public void add(ISituation situation);

    /**
     * The method get retrieves a list of ISituation objects
     * 
     * @param x
     *            The x coordinate for which the list of situations needs to be retrieved
     * @param y
     *            The y coordinate for which the list of situations needs to be retrieved
     * @return A list of ISituation objects that are present at location (x,y)
     */
    public List<ISituation> get(double x, double y);

    public List<ISituation> getSituationList();

}
