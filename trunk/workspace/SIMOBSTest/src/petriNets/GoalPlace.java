package petriNets;

import java.util.LinkedList;
import pipe.common.dataLayer.Marking;
import pipe.common.dataLayer.Place;

// TODO: Reconsider whether a GoalPlace should have a separate class or if just some info someplace else should be sufficient. 
public class GoalPlace extends Place{

	/**
	 * 
	 */
	private static final long serialVersionUID = 138510778459501328L;

	GoalPlace(double positionXInput, double positionYInput) {
		super(positionXInput, positionYInput);
		// TODO Auto-generated constructor stub
	}

	public GoalPlace(double positionXInput, double positionYInput,
			String idInput, String nameInput, double nameOffsetXInput,
			double nameOffsetYInput, LinkedList<Marking> initialMarkingInput,
			double markingOffsetXInput, double markingOffsetYInput,
			int capacityInput, boolean tagged) {
		super(positionXInput, positionYInput, idInput, nameInput, nameOffsetXInput,
				nameOffsetYInput, initialMarkingInput, markingOffsetXInput,
				markingOffsetYInput, capacityInput, tagged);
		// TODO Auto-generated constructor stub
	}

	public GoalPlace(double positionXInput, double positionYInput,
			String idInput, String nameInput, double nameOffsetXInput,
			double nameOffsetYInput, LinkedList<Marking> initialMarkingInput,
			double markingOffsetXInput, double markingOffsetYInput,
			int capacityInput) {
		super(positionXInput, positionYInput, idInput, nameInput, nameOffsetXInput,
				nameOffsetYInput, initialMarkingInput, markingOffsetXInput,
				markingOffsetYInput, capacityInput);
		// TODO Auto-generated constructor stub
	}
	
	public GoalPlace(Place place){
		this(place.getPositionX(), place.getPositionY(), place.getId(), place.getName(), place.getNameOffsetX(), place.getNameOffsetY(), place.getInitialMarking(), place.getMarkingOffsetXObject(), place.getMarkingOffsetYObject(), place.getCapacity());
	}



}
