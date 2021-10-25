//319086435
package levels;

import collision.Velocity;
import backgrounds.BackgroundDirectHit;
import geomatry.Point;
import geomatry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Binyamin Eli.
 * DirectHit class.
 */
public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l1 = new ArrayList<>();
        l1.add(new Velocity(0, -6));
        return l1;
    }

    /**
     * the paddle speed.
     * @return paddle speed dx.
     */
    @Override
    public int paddleSpeed() {
        return 0;
    }

    /**
     * the paddle's width.
     * @return the paddle's width in int number.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * the level name will be displayed at the top of the screen.
     * @return string level name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return background sprite.
     */
    @Override
    public Sprite getBackground() {
        return new BackgroundDirectHit();
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> l1 = new ArrayList<>();
        l1.add(new Block(new Rectangle(new Point(385, 140), 30, 20), Color.RED, Color.BLACK));
        return l1;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return number of blocks to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
