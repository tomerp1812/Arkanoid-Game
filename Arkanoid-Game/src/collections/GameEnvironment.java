package collections;

import collisionDetection.CollisionInfo;
import geometryPrimitive.Line;
import geometryPrimitive.Point;
import interfaces.Collidable;
import interfaces.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class GameEnvironment implements Comparator<Collidable> {
    private List<Collidable> collideList;

    /**
     * constructor of game Environment.
     */
    public GameEnvironment() {
        this.collideList = new ArrayList<>();
    }

    /**
     * getter of collide list.
     *
     * @return the collide list.
     */
    public List<Collidable> getCollideList() {
        return collideList;
    }

    /**
     * adds a colloidal object, if this is the first object make new list.
     *
     * @param c a colloidal object.
     */
    public void addCollidable(Collidable c) {
        if (collideList == null) {
            collideList = new ArrayList<>();
        }
        collideList.add(c);
    }

    /**
     * removes the block from the Collidable list.
     *
     * @param c the block that should be removed.
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> tmp = new ArrayList<>(collideList);
        for (Collidable col : tmp) {
            if (compare(col, c)) {
                collideList.remove(col);
                break;
            }
        }
    }

    /**
     * the function checks if the object that moves will colide with other object.
     * if not it returns null. else returns the information about the closest collision that is going
     * to occur.
     *
     * @param trajectory the trajectory of the ball.
     * @return collision point and the object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> colList = new ArrayList<>(collideList);
        Point closestCollisionPoint = null;
        Point temp;
        int collisionNum = 0;
        for (int i = 0; i < colList.size(); i++) {
            // checks if there is a collision point.
            if (trajectory.closestIntersectionToStartOfLine(colList.get(i).getCollisionRectangle()) != null) {
                //checks for first point that is going to be collided with.
                if (closestCollisionPoint == null) {
                    closestCollisionPoint = trajectory.closestIntersectionToStartOfLine(colList.get(i)
                            .getCollisionRectangle());
                    //to know which object is collided.
                    collisionNum = i;
                } else {
                    //from 2nd point and on checks who is closer point to collide with the object.
                    temp = trajectory.closestPoint(closestCollisionPoint,
                            trajectory.closestIntersectionToStartOfLine(colList.get(i).getCollisionRectangle()));
                    if (temp != closestCollisionPoint) {
                        collisionNum = i;
                    }
                    closestCollisionPoint = temp;
                }
            }
        }
        if (closestCollisionPoint != null) {
            //returns the collision info which is the collision point and object.
            return new CollisionInfo(closestCollisionPoint, colList.get(collisionNum));
        }
        return null;
    }

    @Override
    public boolean compare(Collidable o1, Collidable o2) {
        return o1.equals(o2);
    }
}