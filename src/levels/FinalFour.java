package levels;

import backgrounds.BackgroundFinalFour;
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
public class FinalFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l1 = new ArrayList<>();
        l1.add(new Velocity(3, -3));
        l1.add(new Velocity(-3, -3));
        l1.add(new Velocity(3, -3));
        return l1;
    }

    /**
     * the paddle speed.
     * @return paddle speed dx.
     */
    @Override
    public int paddleSpeed() {
        return 12;
    }

    /**
     * the paddle's width.
     * @return the paddle's width in int number.
     */
    @Override
    public int paddleWidth() {
        return 200;
    }

    /**
     * the level name will be displayed at the top of the screen.
     * @return string level name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     * Returns a sprite with the background of the level.
     * @return background sprite.
     */
    @Override
    public Sprite getBackground() {
        return new BackgroundFinalFour();
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> l1 = new ArrayList<>();
        Color[] colors = new Color[]{Color.GRAY,
                Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < this.numberOfBlocksToRemove() / 7; j++) {
                l1.add(new Block(new Rectangle(new Point(15 + 55 * j,
                        80 + 20 * i), 55, 20), colors[i], Color.BLACK));
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
        return 98;
    }
}
