package differentScreens;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class PauseScreen implements Animation {
    private final boolean stop;

    /**
     * constructor of the PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * @return to stop if needed.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
