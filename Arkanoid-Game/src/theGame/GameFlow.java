package theGame;

import Others.Counter;
import differentScreens.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import differentScreens.GameOver;
import differentScreens.YouWin;
import interfaces.LevelInformation;

import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private final Counter scoreCounter;

    /**
     * constructor of GameFlow.
     *
     * @param ar  animation runner.
     * @param ks  the sensor of the keyboard.
     * @param gui the gui of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        setAnimationRunner(ar);
        setKeyboardSensor(ks);
        setGui(gui);
        scoreCounter = new Counter(0);
    }

    /**
     * setter of gui.
     *
     * @param gui the gui parmater of the game.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * setter of animation runner.
     *
     * @param animationRunner the animation runner of the game.
     */
    public void setAnimationRunner(AnimationRunner animationRunner) {
        this.animationRunner = animationRunner;
    }

    /**
     * getter of animation runner.
     *
     * @return the animation runner.
     */
    public AnimationRunner getAnimationRunner() {
        return animationRunner;
    }

    /**
     * setter of keyboard sensor.
     *
     * @param keyboardSensor the sensor of keyboard.
     */
    public void setKeyboardSensor(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * getter of keyboard sensor.
     *
     * @return the keyboard sensor.
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    /**
     * creates the levels and runs them.
     *
     * @param levels list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            //create the level to play it.
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, gui, scoreCounter);
            //initialize the specific level.
            level.initialize();
            //runs the game while balls and blocks are left in the game.
            while (level.getRemainingBlocksCounter().getValue() > 0
                    && level.getRemainingBallsCounter().getValue() > 0) {
                level.run();
            }
            //if the game is lost.
            if (level.getRemainingBallsCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new GameOver(scoreCounter)));
                gui.close();
            }

        }
        //if we won all the levels.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new YouWin(scoreCounter)));
        gui.close();
    }
}

