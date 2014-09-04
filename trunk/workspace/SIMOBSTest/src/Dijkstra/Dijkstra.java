package Dijkstra;

import java.util.ArrayList;
import java.util.Comparator;

import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.Place;

public class Dijkstra {
	
	public void computeDijkstraPath(DataLayer dataLayer, Place source){
		ArrayList<Node> nodes = initializeNodes(dataLayer, source);
		
		
	}

	private ArrayList<Node> initializeNodes(DataLayer dataLayer, Place source) {
		ArrayList<Dijkstra.Node> nodes = new ArrayList<Dijkstra.Node>();
		
		for(Place place : dataLayer.getPlacesArrayList()){
			nodes.add(new Node(place));
		}
		
		int sourceIndex = nodes.indexOf(source);
		nodes.get(sourceIndex).setDistance(0);
		
		ArrayList<Node> q = (ArrayList<Node>) nodes.clone();
		while(!q.isEmpty()){
			u 
		}
		
	}

	public class Node{
		private Place place;
		private int distance;
		private Node previous;
		
		public Node(Place place){
			this(place, Integer.MAX_VALUE, null);
		}
		
		public Node(Place place, int distance, Node previous) {
			super();
			this.place = place;
			this.distance = distance;
			this.previous = previous;
		}
		
		public Place getPlace() {
			return place;
		}
		public void setPlace(Place place) {
			this.place = place;
		}
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
		public Node getPrevious() {
			return previous;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		
		
		
	}
	
	private class NodeComparator implements Comparator{

		@Override
		public int compare(Object arg0, Object arg1) {
			int comparisonResult = 0;
			Node node0 = (Node)arg0;
			Node node1 = (Node)arg1;
			
			if(node0.getDistance() < node1.getDistance()){
				comparisonResult = -1;
			}
			if(node0.getDistance() > node1.getDistance()){
				comparisonResult = 1;
			}
			return comparisonResult;
		}
		
	}
	
}
