package interfaces;

import biuoop.DrawSurface;

/**
 * @author Ben-Binyamin Eli
 * Animation interface.
 */
public interface Animation {
    /**
     * in charge of the game logic.
     * @param d draw surface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * shouldStop method,in charge of the stopping condition.
     * @return returns true if the game should stop else returns false.
     */
    boolean shouldStop();
}
