package differentScreens;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collections.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class CountdownAnimation implements Animation {
    private int counterFrom;
    private final SpriteCollection spriteCollection;
    private boolean stop;
    private final Sleeper sleeper;
    private final double sleepTime;
    private boolean isFirstRun;

    /**
     * constructor of CountdownAnimation.
     *
     * @param numOfSeconds number of seconds to count.
     * @param countFrom    how many seconds to count.
     * @param gameScreen   the screen of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.counterFrom = countFrom;
        this.spriteCollection = gameScreen;
        this.stop = false;
        this.isFirstRun = true;
        sleeper = new Sleeper();
        this.sleepTime = (numOfSeconds / (double) countFrom);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draw all the sprites.
        spriteCollection.drawAllOn(d);
        //sets a color by custom design.
        Color color = new Color(66, 158, 148);
        d.setColor(color);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 2 + 100, "" + counterFrom, 300);
        if (this.counterFrom == 0) {
            this.stop = true;
        }
        //to not sleep in first run.
        if (!this.isFirstRun) {
            //sleep for 2/3 of a second in our case.
            sleeper.sleepFor((long) ((long) 1000 * sleepTime));
        }
        this.isFirstRun = false;
        this.counterFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}