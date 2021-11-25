package interfaces;
import sprites.Ball;
import sprites.Block;
/**
 * @author Ben-Binyamin Eli
 * This represents HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that's being hit.
     * @param hitter the ball that hits the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}