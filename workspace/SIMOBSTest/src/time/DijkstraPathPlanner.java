package time;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import pipe.common.dataLayer.Arc;
import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.DataLayerWriter;
import pipe.common.dataLayer.Place;
import pipe.common.dataLayer.PlaceFactory;
import pipe.common.dataLayer.PlaceTransitionObject;
import pipe.common.dataLayer.Transition;

public class DijkstraPathPlanner {

    // TODO: Don't use rate any more but another time indicator.
    /**
     * Based on the pseudocode at @url http://en.wikipedia.org/wiki/Dijkstra's_algorithm
     * 
     */
    public ArrayList<SearchNode> computeDistances(DataLayer dataLayer,
            PlaceTransitionObject source, boolean forwards) {

        ArrayList<SearchNode> nodes = initializeGraph(dataLayer);
        SearchNode dummySourceNode;

        // Assuming a sink transition has only one incoming arrow and the firing does not take any
        // time.
        if (source instanceof Transition) {
            Arc arc = (Arc) source.getArcsFrom().next();
            dummySourceNode = new SearchNode((Place) arc.getSource());
        } else {
            dummySourceNode = new SearchNode((Place) source);

        }

        int sourceIndex = nodes.indexOf(dummySourceNode);
        nodes.get(sourceIndex).setDistance(0);

        ArrayList<SearchNode> q = (ArrayList<SearchNode>) nodes.clone();
        Collections.sort(q, new NodeComparator());
        SearchNode u;
        int alt;

        while (!q.isEmpty()) {
            u = q.get(0);
            if (u.getDistance() == Integer.MAX_VALUE) {
                break;
            }
            q.remove(u);

            ArrayList<NodeNeighbor> neighbors = getNonRemovedNeighbors(u, q, forwards);

            for (NodeNeighbor v : neighbors) {
                alt = u.getDistance() + v.distance;

                if (alt < v.node.getDistance()) {
                    v.node.setDistance(alt);
                    v.node.setPrevious(u);
                    Collections.sort(q, new NodeComparator());
                }

            }

        }
        return nodes;
    }

    public ArrayList<SearchNode> computeDistances(DataLayer dataLayer, boolean forwards) {
        Place source = dataLayer.getPlaceByName("goal");
        return computeDistances(dataLayer, source, forwards);
    }

    private ArrayList<NodeNeighbor> getNonRemovedNeighbors(SearchNode node,
            ArrayList<SearchNode> nodeList) {
        return getNonRemovedNeighbors(node, nodeList, true);
    }

    // TODO: Check if we need places connected with only outwards arcs or all
    // arcs.
    private ArrayList<NodeNeighbor> getNonRemovedNeighbors(SearchNode node,
            ArrayList<SearchNode> nodeList, boolean forwards) {
        ArrayList<NodeNeighbor> neighbors = new ArrayList<NodeNeighbor>();
        Iterator<Arc> arcIterator;
        Arc currentArc;
        Place currentTargetPlace;
        SearchNode currentNode;
        int nodeIndex;
        Transition currentTargetTransition;
        Iterator transitionArcIterator;
        Arc currentTransitionArc;
        int currentDistance;

        if (forwards) {
            arcIterator = node.getPlace().getArcsFrom();
        } else {
            arcIterator = node.getPlace().getArcsTo();
        }

        /*
         * TODO: Figure out what to do with transitions that produce several tokens at several
         * places. Currently I just assume that the shortest path will always only be through the
         * relevant places.
         */
        while (arcIterator.hasNext()) {
            currentArc = arcIterator.next();

            if (forwards) {
                currentTargetTransition = (Transition) currentArc.getTarget();
            } else {
                currentTargetTransition = (Transition) currentArc.getSource();
            }
            // Assuming that the time rate is always given integers.
            currentDistance = getDistance(currentTargetTransition);

            // TODO: There probably exists a nicer way to do this.
            if (forwards) {
                transitionArcIterator = currentTargetTransition.getArcsFrom();
            } else {
                transitionArcIterator = currentTargetTransition.getArcsTo();
            }

            while (transitionArcIterator.hasNext()) {
                currentTransitionArc = (Arc) transitionArcIterator.next();
                if (forwards) {
                    currentTargetPlace = (Place) currentTransitionArc.getTarget();
                } else {
                    currentTargetPlace = (Place) currentTransitionArc.getSource();
                }

                // Use dummy SearchNode to find real SearchNode
                currentNode = new SearchNode(currentTargetPlace);
                nodeIndex = nodeList.indexOf(currentNode);

                // Add the real SearchNode to the list of neighbors
                if (nodeIndex >= 0) {
                    neighbors.add(new NodeNeighbor(nodeList.get(nodeIndex), currentDistance));
                }

            }
        }

        return neighbors;
    }

    // At the moment assume that the rate is the place where the user fills in how long an action
    // takes.
    private int getDistance(Transition currentTargetTransition) {
        return (int) currentTargetTransition.getRate();
    }

    private ArrayList<SearchNode> initializeGraph(DataLayer dataLayer) {

        ArrayList<Place> places = dataLayer.getPlacesArrayList();
        SearchNode currentNode;
        ArrayList<SearchNode> nodes = new ArrayList<SearchNode>();

        for (Place place : places) {
            currentNode = new SearchNode(place);
            nodes.add(currentNode);
        }
        return nodes;

    }

    public class SearchNode {

        public SearchNode(Place place) {
            this(place, Integer.MAX_VALUE, null);
        }

        public SearchNode(Place place, int distance, SearchNode previous) {
            super();
            this.place = place;
            this.distance = distance;
            this.previous = previous;
        }

        private Place place;
        private int distance;
        private SearchNode previous;

        public SearchNode getPrevious() {
            return previous;
        }

        public void setPrevious(SearchNode previous) {
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

        public boolean equals(Object object) {
            if (this.getClass().isInstance(object)) {
                SearchNode node = (SearchNode) object;
                return this.getPlace().equals(node.getPlace())
                        || getPlace().getId().equals(node.getPlace().getId());
            } else {
                return false;
            }
        }

        public String toString() {
            String string = "Place: " + getPlace() + ", Distance: " + getDistance()
                    + ", Previous: " + getPrevious();
            return string;
        }

    }

    private class NodeComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode arg0, SearchNode arg1) {
            int comparisonResult = 0;
            SearchNode node0 = (SearchNode) arg0;
            SearchNode node1 = (SearchNode) arg1;

            if (node0.getDistance() < node1.getDistance()) {
                comparisonResult = -1;
            }
            if (node0.getDistance() > node1.getDistance()) {
                comparisonResult = 1;
            }
            return comparisonResult;
        }

    }

    private class NodeNeighbor {
        SearchNode node;
        int distance;

        private NodeNeighbor(SearchNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }

    }

    // Test
    public static void main(String args[]) {
        DijkstraPathPlanner planner = new DijkstraPathPlanner();
        Place testPlace = PlaceFactory.createPlace(4, 4);
        SearchNode testNode1 = planner.new SearchNode(testPlace);
        SearchNode testNode2 = planner.new SearchNode(testPlace, 5, null);

        System.out.println("Equals: " + testNode1.equals(testNode2));

        DataLayer dataLayer = new DataLayer("petrinets/dijkstratest.xml");
        Place source = dataLayer.getPlaceByName("goal");

        ArrayList<SearchNode> nodeList = planner.computeDistances(dataLayer, source, false);
        setNameToDistance(nodeList);
        System.out.println(nodeList);
        DataLayerWriter writer = new DataLayerWriter(dataLayer);

        try {
            writer.savePNML(new File("petriNets/dijkstraTestResult.xml"));
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void setNameToDistance(ArrayList<SearchNode> nodeList) {
        for (SearchNode node : nodeList) {
            node.getPlace().setName(String.valueOf(node.getDistance()));
        }

    }

}
