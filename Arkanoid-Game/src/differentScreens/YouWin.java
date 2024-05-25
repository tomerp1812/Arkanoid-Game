package differentScreens;

import Others.Counter;
import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class YouWin implements Animation {
    private final boolean stop;
    private final Counter scoreCounter;

    /**
     * constructor of GameOver.
     * @param counter the score of the game.
     */
    public YouWin(Counter counter) {
        this.stop = false;
        this.scoreCounter = counter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + scoreCounter.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
