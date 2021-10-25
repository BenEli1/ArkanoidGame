//319086435
package listeners;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * @author Ben-Binyamin Eli
 * This represents ScoreTrackingListener class.
 * represents the scoring of the game,5 points are added for every block being removed,
 * and 100 points when all of the blocks are removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * constructor.
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * updates the score whenever a ball hits a block.
     * @param beingHit the block that's being hit.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
