//319086435
package game;
import animations.AnimationRunner;
import animations.GameOverScreen;
import animations.KeyPressStoppableAnimation;
import animations.WinScreen;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import listeners.Counter;
import java.util.List;
/**
 * @author Ben Binyamin Eli
 * GameFlow class , connects the game levels and allows the game to run smoothly.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    /**
     * constructor.
     * @param ar animation runner
     * @param ks keyboard sensor
     * @param score game score
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = score;
    }

    /**
     * getter for the game score.
     * @return score
     */
    public Counter getScore() {
        return score;
    }
    /**
     * runs the levels.
     * @param levels the game levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int counter = 0;
        for (LevelInformation levelInfo : levels) {
            counter++;
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            while (level.getRemainingBalls().getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }
            if (level.getRemainingBlocks().getValue() == 0) {
                this.score.increase(100);
                if (counter == levels.size()) {
                    this.animationRunner.run(new KeyPressStoppableAnimation(
                            this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                            new WinScreen(this.score)));
                }
            }
            if (level.getRemainingBalls().getValue() <= 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.score)));
                break;
            }

        }
    }
}

