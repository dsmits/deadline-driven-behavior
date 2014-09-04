package situations;

import java.util.ArrayList;
import java.util.List;

/**
 * In this implementation, the situation pointers are put into a grid, and every location in the
 * grid that falls into the area of the situation has an arrayList including the pointer to that
 * particular situation. This will result in a datastructure that might take long to load all the
 * situation in, but once they're in, retrieving the appropriate situations will take very little
 * time.
 */
public class GridSituationCollection implements ISituationCollection {

    ArrayList<ArrayList<ArrayList<ISituation>>> situationGrid;
    private int width;
    private int height;
    ArrayList<ISituation> situationList;

    public GridSituationCollection(int width, int height) {
        situationGrid = new ArrayList<ArrayList<ArrayList<ISituation>>>(width);
        situationList = new ArrayList<ISituation>();
        this.width = width;
        this.height = height;
        fillRow(situationGrid, width);

    }

    @Override
    public void add(ISituation situation) {
        situationList.add(situation);
        SituationArea area = situation.getArea();
        for (int x = area.getTopLeftX(); x < area.getBottomRightX(); x++) {
            for (int y = area.getTopLeftY(); y < area.getBottomRightY(); y++) {
                addSituationOnLocation(situation, x, y);
            }
        }
    }

    private void addSituationOnLocation(ISituation situation, int i, int j) {
        ArrayList<ArrayList<ISituation>> column = situationGrid.get(i);
        if (column.isEmpty()) {
            fillColumn(i, height);
        }
        ArrayList<ISituation> location = column.get(j);
        location.add(situation);

    }

    /*
     * (non-Javadoc)
     * 
     * @see situations.ISituationCollection#get(double, double)
     */
    @Override
    public List<ISituation> get(double x, double y) {
        int intX = (int) Math.round(x);
        int intY = (int) Math.round(y);
        List<ISituation> situations;
        try {
            ArrayList<ArrayList<ISituation>> column = situationGrid.get(intX);
            situations = column.get(intY);

        } catch (IndexOutOfBoundsException e) {
            System.err
                    .printf("Index out of bounds while retrieving situations at (%d, %d), returning new ArrayList\n",
                            intX, intY);
            situations = new ArrayList<ISituation>();
        }

        return situations;
    }

    private void fillRow(ArrayList<ArrayList<ArrayList<ISituation>>> situationGrid, int width) {
        for (int i = 0; i < width; i++) {
            situationGrid.add(new ArrayList<ArrayList<ISituation>>());
            fillColumn(i, height);
        }
    }

    private void fillColumn(int x, int height) {
        ArrayList<ArrayList<ISituation>> column = situationGrid.get(x);

        for (int i = 0; i < height; i++) {
            column.add(new ArrayList<ISituation>());
        }
    }

    public String toString() {
        return situationGrid.toString();
    }

    public static void main(String args[]) {
        ISituationCollection collection = new GridSituationCollection(100, 100);
        // ISituation situation = new SimpleWalkInCircleSituation();
        // situation.setArea(50, 0, 100, 50);
        // collection.add(situation);

        System.out.println(collection);
        System.out.println("(5,5): " + collection.get(5, 5));
        System.out.println("(60,40): " + collection.get(60, 40));

    }

    @Override
    public List<ISituation> getSituationList() {
        return situationList;
    }

}
