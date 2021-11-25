package geomatry;

import java.util.List;

/**
 * @author Ben Binyamin Eli
 * Line class and his methods,contains start and end points of a line.
 */
public class Line {
    public static final int MIN_DISTANCE = 999999;
    private Point start;
    private Point end;

    /**
     * constructor.
     * @param start start point.
     * @param end   end point.
     */
    public Line(Point start, Point end) {
            this.start = start;
            this.end = end;
    }

    /**
     * constructor if given 2 points (x1,y1) (x2,y2).
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     */
    public Line(double x1, double y1, double x2, double y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
    }

    /**
     * length method.
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * middle method.
     * @return Returns the middle point of the line
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * start method.
     * @return Returns the start point of the line
     */
    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * end method.
     * @return Returns the end point of the line
     */
    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * isIntersecting method.
     * @param other other line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    /**
     * intersectionWith method.
     * @param other other line
     * @return Returns the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        //taking care of the situation when both lines are perpendicular to x
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            if (this.start.equals(other.end) || this.start.equals(other.start)) {
                return this.start;
            }
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end;
            }
        }
        double m1 = ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
        double m2 = ((other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX()));
        //checks the case that they are parallel
        if (m1 == m2) {
            if (((this.start.equals(other.end)) && !(this.checkOnLine(other.start)) && !(other.checkOnLine(this.end)))
                    || ((this.start == other.start) && !(this.checkOnLine(other.end)))
                    && !(other.checkOnLine(this.end))) {
                return this.start;
            }
            if (((this.end.equals(other.start)) && !(this.checkOnLine(other.end)) && !(other.checkOnLine(this.start)))
                    || (this.end.equals(other.end) && !(this.checkOnLine(other.start)))
                    && !(other.checkOnLine(this.start))) {
                return this.end;
            }
            return null;
        }
        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            // first is perpendicular to x
            Point p = new Point(this.start.getX(), this.start.getX() * m2 + b2);
            if (this.checkOnLine(p) && other.checkOnLine(p)) {
                return p;
            }
        }
        if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            // second is perpendicular to x
            Point p = new Point(other.start.getX(), other.start.getX() * m1 + b1);
            if (this.checkOnLine(p) && other.checkOnLine(p)) {
                return p;
            }
        }
        double interX = (b2 - b1) / (m1 - m2);
        double interY = interX * m1 + b1;
        if (this.checkOnLine(new Point(interX, interY))
                && other.checkOnLine(new Point(interX, interY))) {
            return new Point(interX, interY);
        } else {
            if (this.start == other.end || this.start == other.start) {
                return this.start;
            }
            if (this.end == other.start || this.end == other.end) {
                return this.end;
            }
            return null;
        }
    }

    /**
     * equals method.
     * @param other other line
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * checkOnLine-method, checks if the point is on a line.
     * @param point point
     * @return true if the point is on the line
     */
    public boolean checkOnLine(Point point) {
        boolean flag1 = false;
        boolean flag2 = false;
        if ((point.getX() >= this.start.getX() && point.getX() <= this.end.getX())
                || (point.getX() <= this.start.getX() && point.getX() >= this.end.getX())) {
            flag1 = true;
        }
        if ((point.getY() >= this.start.getY() && point.getY() <= this.end.getY())
                || (point.getY() <= this.start.getY() && point.getY() >= this.end.getY())) {
            flag2 = true;
        }
        return flag1 && flag2;
    }

    /**
     * @param rect a rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        double minDistance = MIN_DISTANCE;
        int flag = -1;
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).distance(this.start) < minDistance) {
                minDistance = pointList.get(i).distance(this.start);
                flag = i;
            }
        }
        if (flag != -1) {
            return pointList.get(flag);
        }
        return null;
    }
}