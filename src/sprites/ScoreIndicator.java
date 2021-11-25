package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;
import listeners.Counter;
import game.GameLevel;
import java.awt.Color;

/**
 * @author Ben-Binyamin Eli
 * This class represents ScoreIndicator class that indicates the score.
 */
public class ScoreIndicator implements Sprite {
    public static final int SIZE_OF_TEXT = 13;
    private static final int BORDER_SIZE = 15;
    private Counter score;
    /**
     * constructor.
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * draw the sprite to the screen.
     * @param d draw Surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), BORDER_SIZE);
        d.setColor(Color.BLACK);
        d.drawRectangle(0, 0, d.getWidth(), BORDER_SIZE);
        d.drawText(d.getWidth() / 2 - 50, SIZE_OF_TEXT, "Score : " + this.score.getValue(), SIZE_OF_TEXT);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * adding the score to the game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
