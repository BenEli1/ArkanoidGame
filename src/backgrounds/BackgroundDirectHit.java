//319086435
package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * @author Ben Binyamin Eli.
 * BackgroundDirectHit Class.
 */
public class BackgroundDirectHit implements Sprite {
    /**
     * draw the sprite to the screen.
     * @param surface draw Surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(Color.BLUE);
        surface.drawCircle(400, 150, 50);
        surface.drawCircle(400, 150, 80);
        surface.drawCircle(400, 150, 110);
        surface.drawLine(400, 300, 400, 30);
        surface.drawLine(250, 150, 550, 150);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }
}
