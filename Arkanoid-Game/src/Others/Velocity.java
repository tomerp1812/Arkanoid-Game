package Others;

import geometryPrimitive.Point;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
// Others.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    static final double MAX_ANGLE = 360;
    private double dx;
    private double dy;

    /**
     * the constructor of velocity.
     *
     * @param dx infinitesimal change in x.
     * @param dy infinitesimal change in y.
     */
    public Velocity(double dx, double dy) {
        setDx(dx);
        setDy(dy);
    }

    /**
     * the function converts the speed and angle to dx and dy.
     *
     * @param angle the angle the ball will move.
     * @param speed the speed of the ball.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //makes the angle between 0-360.
        angle %= MAX_ANGLE;
        angle = Math.toRadians(angle);
        //converts the speed and angle to dx.
        double dx = speed * Math.sin(angle);
        //converts the speed and angle to dy.
        double dy = speed * Math.cos(angle) * -1;
        return new Velocity(dx, dy);
    }

    /**
     * getter of speed.
     *
     * @return the speed of the object.
     */
    public double getSpeed() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * getter of dx.
     *
     * @return dx as double.
     */
    public double getDx() {
        return dx;
    }

    /**
     * setter of dx.
     *
     * @param dx sets the dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * getter of dy.
     *
     * @return dy as double.
     */
    public double getDy() {
        return dy;
    }

    /**
     * setter of dy.
     *
     * @param dy sets dy .
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * returns the change of the point.
     *
     * @param p the point that changes.
     * @return returns the point after the change.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}