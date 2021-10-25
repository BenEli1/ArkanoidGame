//319086435
package interfaces;

import collision.Velocity;
import sprites.Ball;
import geomatry.Point;
import geomatry.Rectangle;
/**
 * @author Ben-Binyamin Eli
 * This represents Collidable interface.
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     * @return  the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity current velocity.
     * @return The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}