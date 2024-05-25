package geometryPrimitive;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Point {
    private double x;
    private double y;
    private static final double EPSILON = Math.pow(10, -10);

    /**
     * constructor of geometryPrimitive.Point.
     *
     * @param x the value of x.
     * @param y the value of y.
     */

    public Point(double x, double y) {
        setX(x);
        setY(y);
    }


    /**
     * we measure the distance between two points.
     *
     * @param other the point we measure the distance from.
     * @return returns the distance from the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }


    /**
     * we check if two points are equal.
     *
     * @param other we check if the other points is equal to our point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) <= EPSILON && (Math.abs(this.y - other.y) <= EPSILON));
    }


    /**
     * returns the value of x.
     *
     * @return double.
     */


    public double getX() {
        return this.x;
    }

    /**
     * sets the value of x.
     *
     * @param x sets the value of x.
     */
    public void setX(double x) {
        this.x = x;
    }


    /**
     * returns the value of y.
     *
     * @return double.
     */
    public double getY() {
        return this.y;
    }

    /**
     * sets the value of y.
     *
     * @param y sets the value of y.
     */
    public void setY(double y) {
        this.y = y;
    }
}
