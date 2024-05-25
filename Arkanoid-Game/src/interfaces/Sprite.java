package interfaces;

import biuoop.DrawSurface;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the drawSurface object.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}