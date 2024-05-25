import biuoop.GUI;
import theGame.AnimationRunner;
import theGame.GameFlow;
import theGame.InitializeLevels;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Ass6Game {
    /**
     * if there is no args we run the 4 levels. if they are existing we will treat them,
     * and use them.
     *
     * @param args if there are we will make levels according to what is written.
     */
    public static void main(String[] args) {
        //creates the gui.
        GUI gui = new GUI("game", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);
        InitializeLevels initializeLevels = new InitializeLevels(args);
        //initializes the levels and runs them in gameFlow.
        gameFlow.runLevels(initializeLevels.initialize());
    }
}

