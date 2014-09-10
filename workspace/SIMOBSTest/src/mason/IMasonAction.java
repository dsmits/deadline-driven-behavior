package mason;


public interface IMasonAction {

	/**
	 * Init sets all parameters that should be set at the start of the execution of the action.
	 * 
	 */
	public void init();
	
    /**
     * This method does the actual execution of the action. The list of agents and the current agent
     * which has to execute the action are given so the environment can be modified.
     * 
     * @param agents
     *            The complete collection of agents and their wereabouts.
     * @param agent
     *            The current agent, so the agent which has to execute the action can be found in
     *            the agents collection.
     */
    public void act(PedestrianSimState state, PedestrianAgent agent);

    /**
     * isFinished() indicates whether an action has been executed fully or is still not completed.
     * This enables the framework to create actions that encompass multiple steps.
     * 
     * @return a boolean value indicating whether an action has been completed.
     */
    public boolean isFinished();

    // public Color getColor();

}
