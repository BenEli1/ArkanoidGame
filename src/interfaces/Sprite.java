//319086435
package interfaces;
import biuoop.DrawSurface;
/**
 * @author Ben-Binyamin Eli
 * This represents Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d draw Surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}