package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Ben Binyamin Eli.
 * BackgroundGreen3 Class.
 */
public class BackgroundGreen3 implements Sprite {
    /**
     * draw the sprite to the screen.
     * @param surface draw Surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GREEN);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }
}
