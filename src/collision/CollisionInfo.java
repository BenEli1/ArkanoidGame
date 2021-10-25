//319086435
package collision;

import interfaces.Collidable;
import geomatry.Point;

/**
 * @author Ben-Binyamin Eli
 * This represents CollisionInfo Class.
 */
public class CollisionInfo {
    private Point collsionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param collsionPoint collsionPoint
     * @param collisionObject collisionObject
     */
    public CollisionInfo(Point collsionPoint, Collidable collisionObject) {
        this.collsionPoint = collsionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * getCollsionPoint.
     * @return the collison point
     */
    public Point getCollsionPoint() {
        return this.collsionPoint;
    }

    /**
     * getCollisionObject.
     * @return the collisionObject.
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}