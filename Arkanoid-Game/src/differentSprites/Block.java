package differentSprites;

import biuoop.DrawSurface;
import Others.Velocity;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;

import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import interfaces.Comparator;
import theGame.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Block implements Collidable, Sprite, HitNotifier, Comparator<HitListener> {
    private Rectangle collisionRectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * a constructor of block.
     *
     * @param color     the color that the we set.
     * @param rectangle a rectangle.
     */
    public Block(Color color, Rectangle rectangle) {
        setColor(color);
        setCollisionRectangle(rectangle);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * a constructor without a color, it will make gray as default color.
     *
     * @param rectangle the rectangle.
     */
    public Block(Rectangle rectangle) {
        setColor(Color.gray);
        setCollisionRectangle(rectangle);
    }

    /**
     * getter of color.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * setter of color.
     *
     * @param color the color of the surface.
     */
    private void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * setter of collision rectangle.
     *
     * @param collisionRectangle the rectangle we collide with.
     */
    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //checks if we collide with the left or the right line of the rectangle.
        if (collisionPoint.getX() == this.collisionRectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.collisionRectangle.getUpperLeft().getX()
                + collisionRectangle.getWidth()) {
            //changes the velocity of the ball.
            currentVelocity.setDx(currentVelocity.getDx() * -1);
        }
        //checks if we collide with the top or bottom of the rectangle.
        if (collisionPoint.getY() == this.collisionRectangle.getUpperLeft().getY()
                || collisionPoint.getY() == this.collisionRectangle.getUpperLeft().getY()
                + collisionRectangle.getHeight()) {
            //changes the velocity of the ball.
            currentVelocity.setDy(currentVelocity.getDy() * -1);
        }
        this.notifyHit(hitter);
        //returns the updated velocity of the object.
        return currentVelocity;
    }

    /**
     * draws the block.
     *
     * @param surface the draw surface object.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        //draws the surface of the rectangle.
        surface.fillRectangle((int) collisionRectangle.getUpperLeft().getX(),
                (int) collisionRectangle.getUpperLeft().getY(),
                (int) collisionRectangle.getWidth(),
                (int) collisionRectangle.getHeight());
        //makes the differences between two blocks.

        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(), (int) getCollisionRectangle().getHeight());
    }

    @Override
    //it is empty because expecting the blocks to not move.
    public void timePassed() {

    }

    /**
     * removes a block from sprite and collidable list.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * we add the block to the game.
     *
     * @param g the game variable
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        if (hitListeners == null) {
            hitListeners = new ArrayList<>();
        }
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        List<HitListener> tmp = new ArrayList<>(hitListeners);
        for (HitListener hitListener : tmp) {
            if (compare(hitListener, hl)) {
                hitListeners.remove(hitListener);
                break;
            }
        }
    }

    /**
     * @param hitter the ball that hit the block.
     */
    void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public boolean compare(HitListener o1, HitListener o2) {
        return o1.equals(o2);
    }
}
