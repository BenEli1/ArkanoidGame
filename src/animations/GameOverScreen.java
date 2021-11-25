package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import listeners.Counter;

/**
 * @author Ben Binyamin Eli.
 * GameOverScreen Class.
 */
public class GameOverScreen implements Animation {
    private Counter score;

    /**
     * constructor for EndScreen.
     * @param score game score
     */
    public GameOverScreen(Counter score) {
        this.score = score;
    }
    /**
     * in charge of the game logic.
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {
            d.drawText(10, d.getHeight() / 2, "Game Over! - Your score is : " + this.score.getValue()
                    + "  Press space to exit", 32);
        }
    /**
     * shouldStop method,in charge of the stopping condition.
     * @return returns true if the game should stop else returns false.
     */
    public boolean shouldStop() {
        return false;
    }
}
