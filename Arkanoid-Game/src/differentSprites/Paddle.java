package differentSprites;

import biuoop.DrawSurface;
import Others.Velocity;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import theGame.GameLevel;

import java.awt.Color;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private int paddleSpeed;
    static final int WIDTH_OF_BORDER = 20;
    static final int RIGHT_BORDER = 800;
    static final int ANGLE_SECTION_ONE = 300;
    static final int ANGLE_SECTION_TWO = 330;
    static final int MAX_ANGLE = 360;
    private Color color;

    /**
     * constructor of paddle.
     *
     * @param paddleSpeed the speed of the paddle.
     * @param color       the color of the paddle
     * @param gui         the gui.
     * @param rectangle   the paddle rectangle.
     */
    public Paddle(int paddleSpeed, biuoop.GUI gui, Rectangle rectangle, Color color) {
        setPaddleSpeed(paddleSpeed);
        setKeyboard(gui);
        setCollisionRectangle(rectangle);
        setColor(color);
    }

    /**
     * getter of paddle speed.
     *
     * @return the speed of the paddle.
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * setter of paddle speed.
     *
     * @param paddleSpeed sets the speed of the paddle.
     */
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * the setter of color.
     *
     * @param color the color of the paddle.
     */
    private void setColor(Color color) {
        this.color = color;
    }

    /**
     * setter of key board.
     *
     * @param gui the gui.
     */
    public void setKeyboard(biuoop.GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * moves the paddle left by one step.
     */
    public void moveLeft() {
        if (collisionRectangle.getUpperLeft().getX() - paddleSpeed >= WIDTH_OF_BORDER) {
            collisionRectangle.setUpperLeft(new Point(collisionRectangle.getUpperLeft().getX() - paddleSpeed,
                    collisionRectangle.getUpperLeft().getY()));
        }
    }

    /**
     * moves the paddle right by one step.
     */
    public void moveRight() {
        if (collisionRectangle.getUpperLeft().getX() + collisionRectangle.getWidth() + paddleSpeed
                <= RIGHT_BORDER - WIDTH_OF_BORDER) {
            collisionRectangle.setUpperLeft(new Point(collisionRectangle.getUpperLeft().getX() + paddleSpeed,
                    collisionRectangle.getUpperLeft().getY()));
        }
    }

    /**
     * checks if left or right key was pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * @param surface the draw surface object.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        //draws the surface of the rectangle.
        surface.fillRectangle((int) collisionRectangle.getUpperLeft().getX(),
                (int) collisionRectangle.getUpperLeft().getY(),
                (int) collisionRectangle.getWidth(),
                (int) collisionRectangle.getHeight());
    }

    /**
     * @return collisionRectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * setter of collision rectangle.
     *
     * @param rectangle the collision rectangle.
     */
    public void setCollisionRectangle(Rectangle rectangle) {
        this.collisionRectangle = rectangle;
    }

    /**
     * if the ball hits the side of the paddle act like the hit in block.
     * if the ball hits the upper place of the paddle change the velocity,
     * according to the fun paddle instructions.
     *
     * @param collisionPoint  the collision point between the point and the shape.
     * @param currentVelocity the current velocity of the ball.
     * @return the updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getX() == this.collisionRectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.collisionRectangle.getUpperLeft().getX()
                + collisionRectangle.getWidth()) {
            //changes the velocity of the ball.
            currentVelocity.setDx(currentVelocity.getDx() * -1);
        }
        //checks if we collide with the top or bottom of the rectangle.
        if (collisionPoint.getY() == this.collisionRectangle.getUpperLeft().getY()
                || collisionPoint.getY()
                == this.collisionRectangle.getUpperLeft().getY() + this.collisionRectangle.getHeight()) {
            //changes the velocity of the ball.
            currentVelocity = changeVelocity(collisionPoint, currentVelocity);
        }
        //returns the updated velocity of the object.
        return currentVelocity;
    }

    /**
     * @param collisionPoint  the collision point between the object and the rectangle.
     * @param currentVelocity the current velocity of the object.
     * @return the updated velocity.
     */
    public Velocity changeVelocity(Point collisionPoint, Velocity currentVelocity) {
        //divide into 5 sections.
        double section = collisionRectangle.getWidth() / 5;
        //get the section of the collision.
        double pointInSection = (collisionPoint.getX() - collisionRectangle.getUpperLeft().getX()) % section;
        switch ((int) pointInSection) {
            case 0:
                //region 1.
                return Velocity.fromAngleAndSpeed(ANGLE_SECTION_ONE, currentVelocity.getSpeed());
            case 1:
                //region 2.
                return Velocity.fromAngleAndSpeed(ANGLE_SECTION_TWO, currentVelocity.getSpeed());
            case 3:
                //region 4.
                return Velocity.fromAngleAndSpeed(MAX_ANGLE - ANGLE_SECTION_TWO, currentVelocity.getSpeed());
            case 4:
                //region 5
                return Velocity.fromAngleAndSpeed(MAX_ANGLE - ANGLE_SECTION_ONE, currentVelocity.getSpeed());
            default:
                //region 3.
                currentVelocity.setDy(currentVelocity.getDy() * -1);
                return currentVelocity;
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g a game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}