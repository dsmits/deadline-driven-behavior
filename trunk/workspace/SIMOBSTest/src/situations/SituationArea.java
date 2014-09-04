package situations;

public class SituationArea {

    private int topLeftX;
    private int topLeftY;

    private int bottomRightX;
    private int bottomRightY;

    public SituationArea() {
        this(0, 0, 0, 0);
    }

    public SituationArea(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.bottomRightX = bottomRightX;
        this.bottomRightY = bottomRightY;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    public int getBottomRightX() {
        return bottomRightX;
    }

    public void setBottomRightX(int bottomRightX) {
        this.bottomRightX = bottomRightX;
    }

    public int getBottomRightY() {
        return bottomRightY;
    }

    public void setBottomRightY(int bottomRightY) {
        this.bottomRightY = bottomRightY;
    }

    public int getWidth() {
        return bottomRightX - topLeftX;
    }

    public int getHeight() {
        return bottomRightY - topLeftY;
    }

    public String toString() {
        return "Top left: (" + topLeftX + ", " + topLeftY + ")\n" + "Bottom right: ("
                + bottomRightX + ", " + bottomRightY + ")";
    }
}
