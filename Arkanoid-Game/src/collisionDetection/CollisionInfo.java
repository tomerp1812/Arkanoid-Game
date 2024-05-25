package collisionDetection;

import geometryPrimitive.Point;
import interfaces.Collidable;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * @param collisionPoint  the point at which the collision occurs.
     * @param collisionObject the object at which the collision occurs.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        setCollisionPoint(collisionPoint);
        setCollisionObject(collisionObject);
    }

    /**
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * setter of collision point.
     *
     * @param collisionPoint collision point.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * getter of collision object.
     *
     * @return collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * setter of collision object.
     *
     * @param collisionObject the object we collide with.
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
}