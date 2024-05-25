package theGame;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor of Animation Runner.
     *
     * @param g the gui object.
     */
    public AnimationRunner(GUI g) {
        framesPerSecond = 60;
        sleeper = new Sleeper();
        setGui(g);
    }

    /**
     * setter of FPS.
     *
     * @param framesPerSecond frame per second object.
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * setter of gui.
     *
     * @param gui gui object.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * setter of sleeper.
     *
     * @param sleeper the sleep object.
     */
    public void setSleeper(Sleeper sleeper) {
        this.sleeper = sleeper;
    }

    /**
     * the function runs the screen, either level or pause or win or lose screens.
     *
     * @param animation the animation that runs.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
