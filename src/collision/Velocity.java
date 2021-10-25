//319086435
package collision;

import geomatry.Point;

/**
 * @author Ben-Binyamin Eli
 * This class represents Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * This is the constructor.
     * @param dx dx
     * @param dy dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * sets dx.
     * @param dx1 dx
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }
    /**
     * sets dy.
     * @param dy1 dy
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * getDx function.
     * Getter function,returns this.dx.
     * @return dx of Velocity.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * getDy function, getter function,returns this.dy.
     * @return dy of Velocity.
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * applyToPoint function.
     * This function takes a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p point.
     * @return new Point with position (x+dx, y+dy) .
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     * fromAngleAndSpeed function.
     * This function returns the velocity by calculating the angle an speed.
     * @param angle angle in degrees.
     * @param speed speed.
     * @return Velocity(dx,dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
            double dx = speed * Math.sin(Math.toRadians(angle));
            double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}