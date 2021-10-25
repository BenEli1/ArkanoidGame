//319086435
package levels;

import backgrounds.BackgroundGreen3;
import collision.Velocity;
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
public class Green3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l1 = new ArrayList<>();
        l1.add(new Velocity(-4, -3));
        l1.add(new Velocity(-4, -3));
        return l1;
    }

    /**
     * the paddle speed.
     * @return paddle speed dx.
     */
    @Override
    public int paddleSpeed() {
        return 15;
    }

    /**
     * the paddle's width.
     * @return the paddle's width in int number.
     */
    @Override
    public int paddleWidth() {
        return 150;
    }

    /**
     * the level name will be displayed at the top of the screen.
     * @return string level name.
     */
    @Override
    public String levelName() {
        return "Green3";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return background sprite.
     */
    @Override
    public Sprite getBackground() {
            return new BackgroundGreen3();
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> l1 = new ArrayList<>();
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.blue, Color.white};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8 - i; j++) {
                l1.add(new Block(new Rectangle(new Point(730 - 55 * j, 150 + 20 * i), 55, 20), colors[i], Color.BLACK));
            }
        }
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
        return 30;
    }
}
