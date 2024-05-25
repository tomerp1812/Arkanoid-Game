package BackGroundDecorator;

import biuoop.DrawSurface;
import differentSprites.Ball;
import differentSprites.Block;
import interfaces.HitListener;
import interfaces.Sprite;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class BackGround implements Sprite, HitListener {
    @Override
    public void drawOn(DrawSurface d) {
        d.fillRectangle(0, 0, 800, 600);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
    }
}
