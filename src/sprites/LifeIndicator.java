//319086435
package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;
import listeners.Counter;
import game.GameLevel;
import java.awt.Color;
/**
 * @author Ben-Binyamin Eli
 * This class represents LifeIndicator class that indicates the LevelName.
 */
public class LifeIndicator implements Sprite {
    public static final int SIZE_OF_TEXT = 13;
    private Counter lifeCount;

    /**
     * constructor.
     * @param lifeCount life remaining.
     */
    public LifeIndicator(Counter lifeCount) {
        this.lifeCount = lifeCount;
    }

    /**
     * draw the sprite to the screen.
     * @param d draw Surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, SIZE_OF_TEXT, "Lives : " + this.lifeCount.getValue(), SIZE_OF_TEXT);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * adding the Level Name to the game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
