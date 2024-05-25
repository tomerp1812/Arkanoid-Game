package geometryPrimitive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    static final int RECTANGLE_SIDES = 4;

    /**
     * @param upperLeft the upper point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        setUpperLeft(upperLeft);
        setWidth(width);
        setHeight(height);
    }

    /**
     * the function creates and returns a list of points of intersections
     * between the line and the rectangle.
     *
     * @param line the line that intersects with the rectangle.
     * @return a list of points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line[] arrayLine = new Line[RECTANGLE_SIDES];
        //upper vertical line.
        arrayLine[0] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        //lower vertical line.
        arrayLine[1] = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        //left horizontal line.
        arrayLine[2] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        //right horizontal line.
        arrayLine[3] = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        //checks if the line intersects and adds to list if yes.
        for (Line checkLine : arrayLine) {
            if (checkLine.intersectionWith(line) != null) {
                list.add(checkLine.intersectionWith(line));
            }
        }
        return list;
    }

    /**
     * getter of width.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * setter of width.
     *
     * @param width the width of the rectangle.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * getter of height.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * setter of height.
     *
     * @param height the height of the rectangle.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * getter of rectangle.
     *
     * @return upperLeft point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * setter of upperLeft.
     *
     * @param upperLeft the upper point of the rectangle.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
}
