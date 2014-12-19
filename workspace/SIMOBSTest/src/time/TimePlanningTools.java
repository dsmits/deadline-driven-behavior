package time;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import petriNets.SinkTransition;
import petriNets.SituationDataLayer;
import situations.ISituation;
import time.DijkstraPathPlanner.SearchNode;

public class TimePlanningTools {

	// TODO: Find out more appropriate values
	private static final double MAX_RATE = 100;
	private static final double MAX_TIME = 200;

	/**
	 * Computes how much the likelihood should be of entering this situation,
	 * based on how much time is still left to reach the goal state.
	 * 
	 * @param dataLayer
	 * @param situation
	 * @return
	 */
	public double computeSituationTransitionRate(ISituation situation,
			long timeForGoal) {

		// Seeing how I use the value rate in the rest of the code, rate should
		// only be situation.getEstimatedTime(). TimeLeft is computed elsewhere.
		// That can't be computed here, because rate is only computed once (I
		// think) and timeleft is dynamic. It changes every step.
		//double timeLeft = timeForGoal - situation.getEstimatedTime();
		// double rate = (MAX_TIME - timeLeft) / MAX_TIME * MAX_RATE;

		// Changed rate to a value between 0 and 1
		// double rate = (MAX_TIME - timeLeft)/ MAX_TIME;
		double rate = situation.getEstimatedTime();
		return rate;

	}
	


	/**
	 * Computes the shortest path from all places to the sink transition.
	 * 
	 * @param config
	 */
	public void preprocessSituations(List<ISituation> situations) {
		int currentTime;

		Collection<SinkTransition> sinks;

		for (ISituation situation : situations) {
			sinks = situation.getSituationDataLayer().getSinks();

			for (SinkTransition sink : sinks) {

				currentTime = computeEstimatedSituationTime(situation, sink);

				situation.setEstimatedTime(currentTime);
			}

		}
		//writeSituationInfo(situations);

	}
	
	private void writeSituationInfo(List<ISituation> situations){
		try{
		File ratesFile = new File("estimatedTimes.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(ratesFile));
	    for(ISituation situation : situations){
	    	writer.write("Situation: " + situation.getSituationId() + "time: " + situation.getEstimatedTime() + "\n");
	    }
	    //Close writer
	    writer.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Uses the shortest path calculations to compute an estimate of how long a
	 * token is going to be in this situation.
	 * 
	 * @param situation
	 */
	public int computeEstimatedSituationTime(ISituation situation,
			SinkTransition sink) {
		// Find place with the largest estimated time and then add some more
		// time?
		SituationDataLayer dataLayer = situation.getSituationDataLayer();
		DijkstraPathPlanner dijkstra = new DijkstraPathPlanner();

		// Place goalPlace =
		// getSituationGoalPlace(situation.getSituationDataLayer());

		ArrayList<DijkstraPathPlanner.SearchNode> times = dijkstra
				.computeDistances(dataLayer, sink, false);
		int time = getLargestTime(times);
		return time;

		// TODO: Add some extra time, now computeEstimatedSituationTime might be
		// too optimistic.
		// It would also be possible (or even better) to detect loops and do
		// something with that.
	}

	private int getLargestTime(ArrayList<SearchNode> times) {
		int largestTime = 0;
		int currentTime;
		for (DijkstraPathPlanner.SearchNode node : times) {
			currentTime = node.getDistance();
			// When currentTime is equal to Integer.MAX_VALUE, there is no path
			// from the current node to the sink that we are currently looking
			// at so we shouldn't take it into account.
			if (currentTime > largestTime && currentTime != Integer.MAX_VALUE) {
				largestTime = currentTime;
			}
		}

		return largestTime;

	}

	private int getSmallestTime(ArrayList<SearchNode> times) {
		int smallestTime = times.get(0).getDistance();
		int currentTime;
		for (DijkstraPathPlanner.SearchNode node : times) {
			currentTime = node.getDistance();
			if (currentTime < smallestTime) {
				smallestTime = currentTime;
			}
		}

		return smallestTime;

	}

}
