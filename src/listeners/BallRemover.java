//319086435
package listeners;

import game.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;
/**
 * @author Ben-Binyamin Eli
 * This represents BlockRemover class.
 * a BlockRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * removes blocks from remaning blocks.
     * @param gameLevel game
     * @param remainingBalls the removed blocks.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that's being hit.
     * @param hitter   the ball that hits the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
