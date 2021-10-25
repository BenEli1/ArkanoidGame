//319086435
package geomatry;
/**
 * @author Ben-Binyamin Eli
 * This class represents Point, Point contains x and y cordinates.
 */
public class Point {
    private double x;
    private double y;
    /**
     * This is the constructor.
     * @param x x horizon
     * @param y y vertical
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * distance function.
     * @param other Point(x,y).
     * @return distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }
    /**
     * equals function.
     * @param other Point(x,y).
     * @return return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -15);
        return Math.abs(this.y - other.getY()) < epsilon && Math.abs(this.x - other.getX()) < epsilon;
    }
    /**
     * getX method.
     * @return this point x coordiante
     */
    public double getX() {
        return this.x;
    }
    /**
     * getY method.
     * @return this point y coordiante
     */
    public double getY() {
        return this.y;
    }
}