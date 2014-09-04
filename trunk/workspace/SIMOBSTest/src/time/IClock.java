package time;

// TODO: Make singleton?
/**
 * @author smitsds
 * 
 *         Clock object that should be used in the whole simulation to keep track of the time.
 */
public interface IClock extends Comparable<IClock> {

    public void step();

    public void steps(long timesteps);

    public long getTime();
    

}
