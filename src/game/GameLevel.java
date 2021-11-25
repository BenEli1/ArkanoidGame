package game;

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.LevelInformation;
import listeners.BallRemover;
import interfaces.Collidable;
import listeners.BlockRemover;
import listeners.Counter;
import geomatry.Point;
import geomatry.Rectangle;
import listeners.ScoreTrackingListener;
import interfaces.Sprite;
import sprites.LevelNameIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.SpriteCollection;
import sprites.LifeIndicator;
import sprites.Block;
import sprites.Ball;

import java.awt.Color;

/**
 * @author Ben-Binyamin Eli
 * This represents Game class,this class builds the game and the running settings.
 */
public class GameLevel implements Animation {
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final int BORDER_SIZE = 15;
    public static final int BALL_RADIUS = 5;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_X = 385;
    private static final int BALL_Y = 565;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelNameIndicator levelNameIndicator;
    private LifeIndicator lifeCount;
    private LevelInformation levelInfo;

    /**
     * getter for the number of remaning balls.
     * @return remaning blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * getter for the number of remaning balls.
     * @return remaning balls.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * constructor for gameLevel.
     * @param levelInfo the level's information.
     * @param ks keyboard sensor
     * @param run animation runner
     * @param score game score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner run, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(levelInfo.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(levelInfo.numberOfBalls());
        this.score = score;
        this.scoreIndicator = new ScoreIndicator(score);
        this.runner = run;
        this.running = true;
        this.keyboard = ks;
        this.levelNameIndicator = new LevelNameIndicator(levelInfo.levelName());
        this.levelInfo = levelInfo;
    }

    /**
     * adds a collidable to game environment.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * removes a collidable from the game environment.
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * add sprite to SpriteCollection sprites.
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removes sprite from SpriteCollection sprites.
     *
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInfo.getBackground());
        //borders.
        createBorders();
        //blocks
        createBlocks();
        //paddle.
        createPaddle();
        //Indicators.
        createIndicators();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        //balls.
        createBallsOnPaddle();
        this.running = true;
        this.runner.run(this);
    }

    /**
     * in charge of the game logic.
     *
     * @param d draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        createBackground(d);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() <= 0) {
            this.running = false;
        }
    }

    /**
     * shouldStop method,in charge of the stopping condition,
     * returns true if the game should stop else returns false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * getEnvironment.
     *
     * @return the games game environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * getSprites.
     *
     * @return the game's sprite collection.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * createBallsOnPaddle creates the game balls on the paddle.
     */
    private void createBallsOnPaddle() {
        //balls.
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(PADDLE_X + 15 + i * 10, BALL_Y, BALL_RADIUS, Color.white);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * createIndicators creates the game Indicators.
     */
    private void createIndicators() {
        //score.
        this.scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        //levelName.
        this.levelNameIndicator = new LevelNameIndicator(this.levelInfo.levelName());
        levelNameIndicator.addToGame(this);
        //lives
        this.lifeCount = new LifeIndicator(new Counter(7));
        lifeCount.addToGame(this);
    }

    /**
     * createBackground creates the game background.
     * @param d draw srface
     */
    private void createBackground(DrawSurface d) {
        this.levelInfo.getBackground().drawOn(d);
    }

    /**
     * createBorders creates the game borders.
     */
    private void createBorders() {
        BallRemover ballRemover1 = new BallRemover(this, remainingBalls);
        Block topBorder = new Block(new Rectangle(new Point(0, BORDER_SIZE),
                GUI_WIDTH, BORDER_SIZE), Color.lightGray, Color.BLACK);
        topBorder.addToGame(this);
        Block bottomBorder = new Block(new Rectangle(
                new Point(0, GUI_HEIGHT), GUI_WIDTH, BORDER_SIZE), Color.blue, Color.blue);
        bottomBorder.addHitListener(ballRemover1);
        bottomBorder.addToGame(this);
        Block leftBorder = new Block(new Rectangle(
                new Point(0, 2 * BORDER_SIZE),
                BORDER_SIZE, GUI_HEIGHT - 2 * BORDER_SIZE), Color.lightGray, Color.BLACK);
        leftBorder.addToGame(this);
        Block rightBorder = new Block(new Rectangle(
                new Point(GUI_WIDTH - BORDER_SIZE,
                        2 * BORDER_SIZE), BORDER_SIZE, GUI_HEIGHT - 2 * BORDER_SIZE),
                Color.lightGray, Color.BLACK);
        rightBorder.addToGame(this);
    }

    /**
     * createPaddle creates the game paddle.
     */
    private void createPaddle() {
        this.paddle = new Paddle(
                this.keyboard, new Rectangle(new Point(PADDLE_X - levelInfo.paddleWidth() / 2,
                GUI_HEIGHT - PADDLE_HEIGHT - BORDER_SIZE),
                levelInfo.paddleWidth(), PADDLE_HEIGHT), this.levelInfo.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * createBlocks creates the game blocks.
     */
    private void createBlocks() {
        BlockRemover blockRemover1 = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(score);
        for (int i = 0; i < this.levelInfo.blocks().size(); i++) {
            Block block = (this.levelInfo.blocks().get(i));
            block.addHitListener(blockRemover1);
            block.addHitListener(scoreTrack);
            block.addToGame(this);
        }
    }

    /**
     * getter for runner.
     * @return runner
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }
}