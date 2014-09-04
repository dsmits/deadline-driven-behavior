package situations;
import java.util.ArrayList;
import java.util.List;

import fsm.FreeState;
import fsm.IPedestrianState;
import fsm.IPedestrianTransition;
import fsm.WalkInCircleTransition;


public class SimpleWalkInCircleSituation extends AbstractBaseSituation{
	String id;
	//IPedestrianState fsm;
	
	public SimpleWalkInCircleSituation(String id){
		super(id);
		System.out.println("Initializing SimpleWalkInCircleSituation");
		this.id = id;
		//buildFSM();		
	}
	
	private IPedestrianState buildFSM(){
		List<IPedestrianState> states = new ArrayList<IPedestrianState>();
		List<IPedestrianTransition> transitions = new ArrayList<IPedestrianTransition>();
		IPedestrianState state = new FreeState(getId());
		states.add(state);
		IPedestrianTransition transition = new WalkInCircleTransition(getId());
		transitions.add(transition);
		transition.addSituationId(getSituationId());
		state.addIncomingTransition(transition);
		state.addOutgoingTransition(transition);
		state.generateStandardTransitionMap();
		return state;
		
	}
	
	@Override
	public IPedestrianState getFSM() {
		return buildFSM();
	}

	@Override
	public String getId() {
		return id;
	}

	

}
