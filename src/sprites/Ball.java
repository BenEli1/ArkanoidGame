package sprites;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import interfaces.Sprite;
import interfaces.HitListener;
import interfaces.HitNotifier;
import collision.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geomatry.Line;
import geomatry.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Binyamin Eli
 * Ball class and his methods,contains center point, radius and color.
 */
public class Ball implements Sprite, HitNotifier {
    public static final int MIN_RADIUS = 2;
    public static final int MAX_RADIUS = 50;
    public static final int MAX_COUNTER = 3;
    public static final double EPSILON = 0.9998;
    private Point center;
    private double radius;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners = new ArrayList<>();;

    /**
     * constructor of Ball when given a center point,radius and color.
     * @param center center point
     * @param r      radius
     * @param color  color of ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = checkRadius(r);
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * setGameEnvironment.
     * @param gameEnv game env.
     */
    public void setGameEnvironment(GameEnvironment gameEnv) {
        this.gameEnvironment = gameEnv;
    }

    /**
     * getGameEnvironment.
     * @return the balls game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * constructor of Ball when given x and y of the center point,radius and color.
     * @param x     x parameter
     * @param y     y parameter
     * @param r     radius
     * @param color color of ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = checkRadius(r);
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * gets the x of the center of this ball.
     * @return the x coordinate of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * gets the y of the center of this ball.
     * @return the y coordinate of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * gets the size of the radius.
     * @return the size of this ball's radius.
     */
    public double getSize() {
        return this.radius;
    }

    /**
     * gets the color of this ball.
     * @return the color of this ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface the drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(),
                (int) this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * sets velocity to be the given velocity.
     * @param v1 velocity.
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * sets velocity to be the given velocity by dx/dy.
     * @param dx dx of ball
     * @param dy dy of ball
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getVelocity method.
     * @return the balls velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moveOneStep method moves the ball.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.v.applyToPoint(this.center));
        CollisionInfo collisionInf = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInf == null) {
            this.setCenter(trajectory.end());
        } else {
                this.moveBall(collisionInf.getCollsionPoint());
                Velocity newVelocity =
                        collisionInf.getCollisionObject().hit(this, collisionInf.getCollsionPoint(), this.v);
                this.v = newVelocity;
            }
        }

    /**
     * moves ball to the new location after the hit.
     * @param destination points destination.
     */
    public void moveBall(Point destination) {
        double difY = destination.getY() - this.center.getY();
        double difX = destination.getX() - this.center.getX();
        double newY = this.center.getY() + difY * EPSILON;
        double newX = this.center.getX() + difX * EPSILON;
        this.center = new Point(newX, newY);
    }
    /**
     * sets center.
     * @param center2 center point
     */
    public void setCenter(Point center2) {
        this.center = center2;
    }

    /**
     * checks if radius is between 2-50, fixes it to be between this range.
     * @param r radius
     * @return radius between 2-50
     */
    private int checkRadius(int r) {
        if (r <= MIN_RADIUS) {
            return MIN_RADIUS;
        }
        if (r >= MAX_RADIUS) {
            return MAX_RADIUS;
        }
        return r;
    }

    /**
     * adds ball to game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.gameEnvironment = g.getEnvironment();
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}