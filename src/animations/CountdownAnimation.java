package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * @author Ben Binyamin Eli
 * CountdownAnimation class, displays a countdown animation.
 *  The CountdownAnimation will display the given gameScreen,
 *  for numOfSeconds seconds, and on top of them it will show
 *  a countdown from countFrom back to 1, where each number will
 *  appear on the screen for (numOfSeconds / countFrom) seconds, before
 *  it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private static final int SIZE_OF_TEXT = 80;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long startMillisecond;
    private boolean firstFrame;

    /**
     * constructor.
     * @param numOfSeconds Num of seconds
     * @param countFrom count form
     * @param gameScreen sprite collection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.firstFrame = true;
    }

    /**
     * doOneFrame method, prints 3 2 1 accordingly for 2 seconds on the screen at the start of a level.
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {

        //if its the start of the game.
        if (this.firstFrame) {
            this.firstFrame = false;
            this.startMillisecond = System.currentTimeMillis();
        }
        this.gameScreen.drawAllOn(d);
        double tickTime = this.numOfSeconds / (double) this.countFrom;
        long currentMillisecond = System.currentTimeMillis();
        int numOnScreen = (int) ((double) (this.countFrom + 1)
                - (double) (currentMillisecond - this.startMillisecond) / (tickTime * 1000));
        //text on top of screen with the number.
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 2 - 180, SIZE_OF_TEXT, "Starts in : " + numOnScreen, SIZE_OF_TEXT - 20);
        //checks if time passed is greater than then the number of seconds provided.
        if ((double) (currentMillisecond - this.startMillisecond) > this.numOfSeconds * 1000) {
            this.stop = true;
        }
    }

    /**
     * return true if should stop and false if it shouldn't stop.
     * @return returns this.stop
     */
    public boolean shouldStop() {
        return this.stop;
        }
    /**
     * createBackground creates the game balls.
     * @param d drawsurface.
     */
}
