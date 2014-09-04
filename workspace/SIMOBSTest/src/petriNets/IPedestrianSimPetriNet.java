package petriNets;

import java.util.Collection;

import pipe.common.dataLayer.Transition;

/**
 * @author smitsds
 * 
 *         Wrapper around the DataLayerInterface used to incorporate information about situations
 *         such as pointers to the sink transitions.
 *         TODO: OBSOLETE
 * 
 */
public interface IPedestrianSimPetriNet {

    // public DataLayerInterface getDataLayer();

    public Collection<Transition> getSinks();

    public Transition getAvailableSink();

    /**
     * Returns a list of all sources that have not yet been attached to the sink of another
     * IPedestrianPetriNet
     * 
     * @return A list of all available sources
     */
    public Collection<Transition> getAvailableSources();

    /**
     * Returns a source that is not attached to a sink of another DataLayer yet. Dependent on the
     * implementation, it could be an arbitrary source, or there could be a fixed order in which
     * sources are given, depending on the kind of Petri net. For some situation Petri nets, it
     * could be relevant to offer the sources in a certain order.
     * 
     * @return A source transition to which the sink of another datalayer can be attached.
     */
    public Transition getAvailableSource();

}
