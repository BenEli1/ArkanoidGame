//319086435
package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;
import game.GameLevel;
import java.awt.Color;
/**
 * @author Ben-Binyamin Eli
 * This class represents LevelNameIndicator class that indicates the LevelName.
 */
public class LevelNameIndicator implements Sprite {
    public static final int SIZE_OF_TEXT = 13;
    private String levelName;

    /**
     * constuctor for LevelNameIndicator.
     * @param levelName the level's name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * draw the sprite to the screen.
     * @param d draw Surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(600, SIZE_OF_TEXT, "Level Name : " + this.levelName, SIZE_OF_TEXT);
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
