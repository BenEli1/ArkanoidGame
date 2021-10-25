//319086435
import animations.AnimationRunner;
import game.GameFlow;
import interfaces.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;
import listeners.Counter;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Ben Binyamin Eli
 * This class runs the game of ass3.
 */
public class Ass6Game {
    /**
     * main game class.
     * @param args arguments.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(runner, runner.getGui().getKeyboardSensor(), new Counter(0));
        List<LevelInformation> l1 = new ArrayList<>();
        LevelInformation directHit = new DirectHit();
        LevelInformation wideEasy = new WideEasy();
        LevelInformation green3 = new Green3();
        LevelInformation finalFour = new FinalFour();
        l1.add(directHit);
        l1.add(wideEasy);
        l1.add(green3);
        l1.add(finalFour);
        List<LevelInformation> l2 = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 1 && Integer.parseInt(args[i]) >= 1 && Integer.parseInt(args[i]) <= 4) {
                l2.add(l1.get(Integer.parseInt(args[i]) - 1));
            }
        }
        if (l2.size() > 0) {
            gameFlow.runLevels(l2);
        } else {
            gameFlow.runLevels(l1);
        }
        runner.getGui().close();
    }
}
