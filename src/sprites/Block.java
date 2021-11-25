package sprites;

import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.Sprite;
import interfaces.HitListener;
import interfaces.HitNotifier;
import collision.Velocity;
import game.GameLevel;
import geomatry.Point;
import geomatry.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben-Binyamin Eli
 * This class represents Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color colorOfBlock;
    private java.awt.Color colorOfBorder;
    private List<HitListener> hitListeners = new ArrayList<>();;
    /**
     * constructor.
     * @param block block
     * @param colorOfBlock color of block
     * @param colorOfBorder color of the border
     */
    public Block(Rectangle block, java.awt.Color colorOfBlock, java.awt.Color colorOfBorder) {
        this.block = block;
        this.colorOfBlock = colorOfBlock;
        this.colorOfBorder = colorOfBorder;
    }

    /**
     * drawOn draws the block.
     * @param surface draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.colorOfBlock);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
        surface.setColor(this.colorOfBorder);
        //blue borders.
        surface.drawRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(),
                (int) this.block.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * getCollisionRectangle.
     * @return collision shape which is a rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * hit method.
     * @param collisionPoint  the collision point.
     * @param currentVelocity current velocity.
     * @return retruns new speed according to the collision point.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
            if (this.block.getLeftLine().checkOnLine(collisionPoint) && currentVelocity.getDx() > 0) {
                currentVelocity = new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            } else if (this.block.getRightLine().checkOnLine(collisionPoint) && currentVelocity.getDx() < 0) {
                currentVelocity = new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            }
            if (this.block.getUpperLine().checkOnLine(collisionPoint) && currentVelocity.getDy() > 0) {
                currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            } else if (this.block.getBottomLine().checkOnLine(collisionPoint) && currentVelocity.getDy() < 0) {
                currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * adds block to game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes block from game.
     * @param gameLevel game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * updates the listener array with a new hit.
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
