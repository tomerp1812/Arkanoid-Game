package interfaces;

import biuoop.DrawSurface;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */

public interface Animation {
    /**
     * Run the game -- start the animation loop.
     *
     * @param d the DrawSurface variable.
     */
    void doOneFrame(DrawSurface d);

    /**
     * the stop and continue method of the game.
     *
     * @return true to stop the game, false to keep the game going.
     */
    boolean shouldStop();
}
