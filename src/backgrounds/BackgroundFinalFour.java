//319086435

package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Ben Binyamin Eli.
 * BackgroundFinalFour Class.
 */
public class BackgroundFinalFour implements Sprite {
    /**
     * draw the sprite to the screen.
     * @param surface draw Surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
            surface.setColor(new Color(51, 153, 255));
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(Color.white);
        //lines
        for (int i = 0; i < 10; i++) {
            surface.drawLine(680 - i * 10, 450,  620 - i * 10, 800);
            surface.drawLine(180 - i * 10, 400, 120 - i * 10, 800);
        }
        surface.setColor(new Color(210, 210, 210));
        //left cloud
        surface.fillCircle(100, 400, 25);
        surface.fillCircle(120, 420, 29);
        //right cloud
        surface.fillCircle(600, 450, 25);
        surface.fillCircle(620, 490, 29);
        surface.setColor(new Color(200, 200, 200));
        //left
        surface.fillCircle(140, 390, 32);
        //right
        surface.fillCircle(640, 460, 31);
        surface.setColor(new Color(180, 180, 180));
        //left
        surface.fillCircle(160, 420, 24);
        surface.fillCircle(180, 400, 35);
        //right
        surface.fillCircle(660, 490, 24);
        surface.fillCircle(680, 470, 35);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }
}
