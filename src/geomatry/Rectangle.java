//319086435
package geomatry;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Ben-Binyamin Eli
 * This class represents Rectangle, Rectangle contains location width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private Line leftLine;
    private Line rightLine;
    private Line bottomLine;
    private Line upperLine;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft upperLeft location
     * @param width     width of rectangle
     * @param height    height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.upperRight = new Point((getUpperLeft().getX() + this.getWidth()), (this.getUpperLeft().getY()));
        this.bottomLeft = new Point((getUpperLeft().getX()), (this.getUpperLeft().getY() + this.getHeight()));
        this.bottomRight = new Point((getUpperLeft().getX() + this.getWidth()), (this
                .getUpperLeft().getY() + this.getHeight()));
        this.leftLine = new Line(this.getBottomLeft(), this.getUpperLeft());
        this.bottomLine = new Line(this.getBottomLeft(), this.getBottomRight());
        this.rightLine = new Line(this.getBottomRight(), this.getUpperRight());
        this.upperLine = new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * setUpperLeft.
     * @param upLeft  new upper left point
     */
    public void setUpperLeft(Point upLeft) {
        this.upperLeft = upLeft;
        this.upperRight = new Point((getUpperLeft().getX() + this.getWidth()), (this.getUpperLeft().getY()));
        this.bottomLeft = new Point((getUpperLeft().getX()), (this.getUpperLeft().getY() + this.getHeight()));
        this.bottomRight = new Point((getUpperLeft().getX() + this.getWidth()), (this
                .getUpperLeft().getY() + this.getHeight()));
        this.leftLine = new Line(this.getBottomLeft(), this.getUpperLeft());
        this.bottomLine = new Line(this.getBottomLeft(), this.getBottomRight());
        this.rightLine = new Line(this.getBottomRight(), this.getUpperRight());
        this.upperLine = new Line(this.getUpperLeft(), this.getUpperRight());
    }
    /**
     * getUpperRight().
     * @return upperRight
     */
    public Point getUpperRight() {
        return this.upperRight;
    }
    /**
     * get leftLine.
     * @return leftLine
     */
    public Line getLeftLine() {
        return this.leftLine;
    }
    /**
     * get rightLine.
     * @return rightLine
     */
    public Line getRightLine() {
        return this.rightLine;
    }
    /**
     * get bottomLine.
     * @return bottomLine
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**
     * get upperLine.
     * @return upperLine
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * getBottomRight().
     *
     * @return bottomleft
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * getBottomRight().
     *
     * @return bottomright
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }
    /**
     * getWidth().
     * @return Return the width of the rectangle
     */
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight().
     * @return Return the width of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft().
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * @param line line
     * @return Return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();
        if (this.leftLine.isIntersecting(line)) {
            pointList.add(this.leftLine.intersectionWith(line));
        }
        //if the line intersects and the point wasn't in the list then it adds to the array of points.
        if (this.bottomLine.isIntersecting(line) && !checkInList(
                this.bottomLine.intersectionWith(line), pointList)) {
            pointList.add(this.bottomLine.intersectionWith(line));
        }
        if (this.rightLine.isIntersecting(line) && !checkInList(
                this.rightLine.intersectionWith(line), pointList)) {
            pointList.add(this.rightLine.intersectionWith(line));
        }
        if (this.upperLine.isIntersecting(line) && !checkInList(
                this.upperLine.intersectionWith(line), pointList)) {
            pointList.add(this.upperLine.intersectionWith(line));
        }
        return pointList;
    }

    /**
     * Checks if the point exists in array of points.
     * @param p specific point
     * @param pointList an array of points
     * @return true if the point already exists in pointList else false.
     */
    public boolean checkInList(Point p, List<Point> pointList) {
        for (Point point : pointList) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }
}