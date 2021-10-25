//319086435
package interfaces;

import collision.Velocity;
import sprites.Block;

import java.util.List;

/**
 * @author Ben-Binyamin Eli
 * Level Information interface.
 */
public interface LevelInformation {
    /**
     * number of balls method.
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed.
     * @return paddle speed dx.
     */
    int paddleSpeed();

    /**
     * the paddle's width.
     * @return the paddle's width in int number.
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return string level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return background sprite.
     */
    Sprite getBackground();


    /**
     *  The Blocks that make up this level, each block contains,
     *  its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();
    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();.
     * @return number of blocks to be removed.
     */
    int numberOfBlocksToRemove();
}
