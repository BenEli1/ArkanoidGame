package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * @author Ben Binyamin Eli
 * AnimationRunner class.
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * consturctor for Animation Runenr.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /**
     * getter for gui so you could close the program later.
     *
     * @return gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * run method - runs the animation of the game.
     * @param animation animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
               this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

