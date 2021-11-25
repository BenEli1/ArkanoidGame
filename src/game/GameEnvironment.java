package game;

import interfaces.Collidable;
import collision.CollisionInfo;
import geomatry.Line;
import geomatry.Point;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Ben-Binyamin Eli
 * This represents GameEnvironment Class.
 */
public class GameEnvironment {
    public static final double MIN_DISTANCE = 9999999;
    private List<Collidable> collidables = new ArrayList<>();
    /**
     * add the given collidable to the environment.
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * CollisionInfo.
     * @param trajectory line
     * @return Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection,return null.
     * Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
       if (this.collidables.isEmpty()) {
            return null;
        }
        int i = 0, minIndex = -1;
        double minDistance = MIN_DISTANCE;
        while (i < this.collidables.size()) {
                Point p = trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
                if (p != null && p.distance(trajectory.start()) < minDistance) {
                    minDistance = p.distance(trajectory.start());
                    minIndex = i;
                }
            i++;
        }
        if (minIndex == -1) {
            return null;
        }
            return new CollisionInfo(trajectory.closestIntersectionToStartOfLine(this.collidables
                    .get(minIndex).getCollisionRectangle()), this.collidables.get(minIndex));
    }
    /**
     * removes the given collidable from the environment.
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
