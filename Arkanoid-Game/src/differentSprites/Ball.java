package differentSprites;

import biuoop.DrawSurface;
import collections.GameEnvironment;
import collisionDetection.CollisionInfo;
import Others.Velocity;
import geometryPrimitive.Line;
import geometryPrimitive.Point;
import interfaces.Sprite;
import theGame.GameLevel;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Ball implements Sprite {
    static final double DEFAULT_VELOCITY = 1;
    private int top;
    private int bottom;
    private int left;
    private int right;
    private Velocity velocity;
    private double x;
    private double y;
    private int size;
    private java.awt.Color color;
    static final int TOP = 0;
    static final int LEFT = 0;
    static final int RIGHT = 200;
    static final int BOTTOM = 200;
    private GameEnvironment gameEnvironment;
    static final int ONE_STEP = 1;

    /**
     * the constructor of ball.
     *
     * @param center a circle.
     * @param r      the radius of the circle.
     * @param color  the color of the circle.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        setX(center.getX());
        setY(center.getY());
        setTop(TOP);
        setLeft(LEFT);
        setRight(RIGHT);
        setBottom(BOTTOM);
        setSize(r);
        setColor(color);
        setVelocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        setGameEnvironment(new GameEnvironment());

    }

    /**
     * a constructor that gets two coordinate instead of a point.
     *
     * @param x     the x coordinate of the circle.
     * @param y     the y coordinate of the circle.
     * @param r     the radius of the circle.
     * @param color the color of the circle.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        setX(x);
        setY(y);
        setTop(TOP);
        setLeft(LEFT);
        setRight(RIGHT);
        setBottom(BOTTOM);
        setSize(r);
        setColor(color);
        setVelocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        setGameEnvironment(new GameEnvironment());

    }

    /**
     * @return the game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * @param gameEnvironment the game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * getter top.
     *
     * @return int top.
     */
    public int getTop() {
        return this.top;
    }

    /**
     * @param top setter top.
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * @return int bottom.
     */
    public int getBottom() {
        return this.bottom;
    }

    /**
     * @param bottom setter of bottom.
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    /**
     * @return returns left as int.
     */
    public int getLeft() {
        return this.left;
    }

    /**
     * @param left setter of left.
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * @return int right.
     */
    public int getRight() {
        return this.right;
    }

    /**
     * @param right setter of right.
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return (int) x;
    }

    /**
     * @param x a coordinate x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return y coordinate.
     */
    public int getY() {
        return (int) y;
    }

    /**
     * @param y sets y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the size of the circle.
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size sets the size of the circle.
     */
    public void setSize(int size) {
        //if the size is smaller than or equal to 0 set a default size to 1.
        if (size <= 0) {
            this.size = 1;
        }
        //if the size of the ball is bigger than the frame make it max possible size.
        this.size = Math.min(size, (this.getBottom() - this.getTop()) / 2);
        this.size = Math.min(this.size, (this.getRight() - this.getLeft()) / 2);
    }

    /**
     * @return the color of the circle.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * @param color sets the color of the circle.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * the function draws the balls in the given parameters.
     *
     * @param surface draws the ball on the given surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * sets the velocity.
     *
     * @param v the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets the velocity with dx and dy.
     *
     * @param dx infinitesimal change in x.
     * @param dy infinitesimal change in y.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getter of velocity.
     *
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * checks if the next move of the ball will be collided with other object.
     * if yes it will change the velocity of the ball accordingly.
     *
     * @param point the middle point of the ball.
     * @return returns if there is a collision.
     */
    public boolean collidePoint(Point point) {
        //the expected movement of the ball.
        Line trajectory = new Line(point.getX(), point.getY(), point.getX() + this.getVelocity().getDx(),
                point.getY() + this.getVelocity().getDy());
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            //if we expect a collision update the velocity accordingly.
            setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.getVelocity()));
            manualMoveOfBall(collisionInfo);
            return true;
        }
        return false;
    }

    /**
     * the function makes a manual move one step of the ball.
     * the function will be called only if the ball is close to
     * a collision object.
     *
     * @param collisionInfo the info about the collision.
     */
    public void manualMoveOfBall(CollisionInfo collisionInfo) {
        /*
            we make a manual move one step to the ball in relation,
            to the collision point.
            */
        //collision with left side of block.
        if (collisionInfo.collisionPoint().getX()
                == collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getX()) {
            setX(collisionInfo.collisionPoint().getX() - ONE_STEP);
            //collision with the right side of the block.
        } else if (collisionInfo.collisionPoint().getX()
                == collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getX()
                + collisionInfo.collisionObject().getCollisionRectangle().getWidth()) {
            setX(collisionInfo.collisionPoint().getX() + ONE_STEP);
        }
        //collision with the top side of the block.
        if (collisionInfo.collisionPoint().getY()
                == collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY()) {
            setY(collisionInfo.collisionPoint().getY() - ONE_STEP);
            //collision with the bottom side of the block.
        } else if (collisionInfo.collisionPoint().getY()
                == collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY()
                + collisionInfo.collisionObject().getCollisionRectangle().getHeight()) {
            setY(collisionInfo.collisionPoint().getY() + ONE_STEP);
        }
    }


    /**
     * the function moves each frame one step. everytime gets the point of the ball,
     * then checks if the ball is close to the bounds of the screen.
     * the it applies the velocity of the ball, and sets the next point on the screen.
     */
    public void moveOneStep() {
        Point point = new Point(x, y);
        if (collidePoint(point)) {
            return;
        }
        //if the ball is far from a collision.
        point = this.getVelocity().applyToPoint(point);
        setX(point.getX());
        setY(point.getY());
    }

    /**
     * adds ball to the game.
     *
     * @param g a game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removes the ball from the game.
     *
     * @param g a game object.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
