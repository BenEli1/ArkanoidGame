//319086435
package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * @author Ben Binyamin Eli.
 * PauseScreen Class.
 */
public class PauseScreen implements Animation {
    /**
     * in charge of the game logic.
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        }
    /**
     * shouldStop method,in charge of the stopping condition.
     * @return returns true if the game should stop else returns false.
     */
    public boolean shouldStop() {
        return false;
    }
}
