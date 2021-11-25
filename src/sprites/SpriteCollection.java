package sprites;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Ben-Binyamin Eli
 * This represents SpriteCollection Class.
 */
public class SpriteCollection {
    private List<Sprite> spriteCollection = new ArrayList<>();
    /**
     * add sprite to collection.
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        spriteCollection.add(s);
    }
    /**
     *   call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteCollection.size(); i++) {
            spriteCollection.get(i).timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteCollection.size(); i++) {
            spriteCollection.get(i).drawOn(d);
        }
    }
    /**
     * add sprite to collection.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }
}