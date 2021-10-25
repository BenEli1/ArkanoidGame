//319086435
package listeners;

import game.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * @author Ben-Binyamin Eli
 * This represents BlockRemover class.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * removes blocks from remaning blocks.
     * @param gameLevel game
     * @param remainingBlocks the removed blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the block that's being hit.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}
