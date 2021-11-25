package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Collidable;
import collision.Velocity;
import game.GameLevel;
import interfaces.Sprite;
import geomatry.Point;
import geomatry.Rectangle;
import java.awt.Color;

import static game.GameLevel.BORDER_SIZE;
import static game.GameLevel.GUI_WIDTH;

/**
 * @author Ben-Binyamin Eli
 * This class represents the Paddle.
 */
public class Paddle implements Sprite, Collidable {
    public static final int DX_PADDLE = 12;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private int speed;
    /**
     * Constructor.
     * @param keyb keyboard sensor
     * @param rect paddle rectangle
     * @param speed paddle's speed
     */
    public Paddle(KeyboardSensor keyb, Rectangle rect, int speed) {
        this.keyboard = keyb;
        this.paddleRectangle = rect;
        this.speed = speed;
    }

    /**
     * moves paddle left if pressed on left key, if possible(not reaching the end of the screen).
     */
    public void moveLeft() {
        Velocity v = new Velocity(-speed, 0);
        Point paddlePoint = v.applyToPoint(this.paddleRectangle.getUpperLeft());
        if (paddlePoint.getX() > BORDER_SIZE - speed) {
            this.paddleRectangle.setUpperLeft(paddlePoint);
        }
    }
    /**
     * moves paddle right if pressed on right key, if possible(not reaching the end of the screen).
     */
    public void moveRight() {
        Velocity v = new Velocity(speed, 0);
        Point paddlePoint = v.applyToPoint(this.paddleRectangle.getUpperLeft());
        if (paddlePoint.getX() < GUI_WIDTH - this.paddleRectangle.getWidth() - BORDER_SIZE + speed) {
            this.paddleRectangle.setUpperLeft(paddlePoint);
        }
    }

    /**
     * draws the paddle.
     * @param d draw Surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
                (int) this.paddleRectangle.getUpperLeft().getY(),
                (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
        d.setColor(Color.yellow);
        //yellow borders.
        d.drawRectangle((int) this.paddleRectangle.getUpperLeft().getX(),
                (int) this.paddleRectangle.getUpperLeft().getY(),
                (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
    }

    /**
     * checks if keyboard pressed left key or right key and moves the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * getter for collision rectangle.
     * @return the paddle rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * hit method.
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity current velocity.
     * @return new velocity according to the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (checkIfStuck(hitter, collisionPoint)) {
            hitter.setCenter(new Point(hitter.getX(), this.paddleRectangle.getUpperLeft().getY() - hitter.getSize()));
            collisionPoint = new Point(hitter.getX(), hitter.getY());
        }
        if ((collisionPoint.getX() >= this.paddleRectangle.getUpperLeft().getX()
                && collisionPoint.getY() <= this.paddleRectangle.getUpperLeft().getY()
                && collisionPoint.getX() <= this.paddleRectangle.getUpperLeft().getX()
                + this.paddleRectangle.getWidth())) {
            double collisionX = collisionPoint.getX() - this.paddleRectangle.getUpperLeft().getX();
            //calculating speed vector using pythagorean theorem.
            double speedVector = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
            double fifthOfPaddle = this.paddleRectangle.getWidth() / (double) 5;
            //first section
            if (collisionX <= fifthOfPaddle && collisionX >= 0
                    || this.paddleRectangle.getUpperLeft().equals(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(300, speedVector);
            }
            //second section
            if (collisionX <= fifthOfPaddle * 2) {
                return Velocity.fromAngleAndSpeed(330, speedVector);
            }
            //third section
            if (collisionX <= fifthOfPaddle * 3) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            }
            //fourth section
            if (collisionX <= fifthOfPaddle * 4) {
                return Velocity.fromAngleAndSpeed(30, speedVector);
            }
            //fifth section
            if (collisionX <= fifthOfPaddle * 5 || this.paddleRectangle.getUpperRight().equals(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(60, speedVector);
            }
        }
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
    }

    /**
     * sets keyboard sensor.
     * @param sensor keyboard sensor
     */
    public void setKeyboard(KeyboardSensor sensor) {
        this.keyboard = sensor;
    }
    /**
     * Add this paddle to given game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * checks if the ball is stuck inside the paddle.
     * @param hitter hitter ball
     * @param collisionPoint collision point
     * @return returns true if inside the paddle else false.
     */
    public boolean checkIfStuck(Ball hitter, Point collisionPoint) {
        //checks if the ball is inside the x and y parameters of the ball and returns true if it is.
        if ((collisionPoint.getX() >= this.paddleRectangle.getUpperLeft().getX()
                && collisionPoint.getY() > this.paddleRectangle.getUpperLeft().getY()
                && collisionPoint.getX() <= this.paddleRectangle.getUpperLeft().getX()
                + this.paddleRectangle.getWidth() && collisionPoint.getY() <= this.paddleRectangle.getUpperLeft().getY()
                + this.paddleRectangle.getHeight() + hitter.getSize())) {
            return true;
        }
        return false;
    }
}