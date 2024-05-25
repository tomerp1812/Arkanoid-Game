package geometryPrimitive;

import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Line {


    private Point start;
    private Point end;

    /**
     * constructor of line with two points.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        setStart(start);
        setEnd(end);
    }

    /**
     * sets the start point of line.
     *
     * @param start the start point of line.
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * sets the end point of line.
     *
     * @param end the end point of line.
     */
    public void setEnd(Point end) {
        this.end = end;
    }


    /**
     * constructor of line with 4 coordinates.
     *
     * @param x1 the x coordinate of start.
     * @param y1 the y coordinate of start.
     * @param x2 the x coordinate of end.
     * @param y2 the y coordinate of end.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        setStart(start);
        setEnd(end);
    }

    /**
     * @return minimum between y's.
     */
    public double minX() {
        return Math.min(this.end.getX(), this.start.getX());
    }

    /**
     * @return maximum between x's.
     */
    public double maxX() {
        return Math.max(this.end.getX(), this.start.getX());
    }

    /**
     * @return minimum between y's.
     */
    public double minY() {
        return Math.min(this.end.getY(), this.start.getY());
    }

    /**
     * @return maximum between the y's.
     */
    public double maxY() {
        return Math.max(this.end.getY(), this.start.getY());
    }

    /**
     * measure the length of a line.
     *
     * @return double.
     */
    public double length() {
        return start.distance(end);
    }


    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }


    /**
     * @return the start point of the line.
     */
    public Point start() {
        return start;
    }


    /**
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * the function checks if two lines are intersecting and returns false or true .
     * we check if two lines are intersecting , first we check if the value of x
     * isn't changing in each of the lines which means that we can't find a normal inclining.
     * then if the value of x is changing we find the incline of the line, the y value of the line.
     * if the two lines have same x value it means they are parallels and we check if they are on
     * the same level. if they aren't parallel we find the x in which they intersect.
     * if this x is between the two coordinates of the two lines it means they are intersecting.
     *
     * @param other the line that we check if its intersecting with our line.
     * @return true or false.
     */
    public boolean isIntersecting(Line other) {
        Line ourLine = new Line(start(), end());
        /*
         * three first if's are for a situation where either our line or the other line
         * has infinite slope. in this case we can't use the normal method of finding incline.
         */
        if (start.getX() == end.getX() && other.start.getX() == other.end.getX()) {
            return start.getX() == other.start.getX()
                    && (checkTwoCoordinates(other.start.getY(), other.end.getY(), start.getY(), end.getY())
                    || checkTwoCoordinates(start.getY(), end.getY(), other.start.getY(), other.end.getY()));
        }

        if (start.getX() == end.getX()) {
            return ourLine.infiniteIncline(other) != null;
        }

        if (other.start.getX() == other.end.getX()) {
            return other.infiniteIncline(ourLine) != null;
        }

        /*
         * if we are here it means the incline of the lines aren't infinite so we find the incline and
         * the b (in the y=ax+b) of the two lines.
         */
        double inclineOther = inclineOfLine(other.start.getX(), other.start.getY(),
                other.end.getX(), other.end.getY());

        double coordinateOtherY = coordinateY(inclineOther, other.end.getX(), other.end.getY());

        double inclineLine = inclineOfLine(start.getX(), start.getY(),
                end.getX(), end.getY());

        double coordinateLineY = coordinateY(inclineLine, end.getX(), end.getY());

        //checks if the two lines are parallel
        if (inclineOther != inclineLine) {
            //finds the x in which the two equations are intersecting.
            double commonX = commonX(coordinateLineY, inclineLine, coordinateOtherY, inclineOther);
            //checks if the x value is on the lines.
            return checkCoordinateInLine(commonX, start.getX(), end.getX())
                    && checkCoordinateInLine(commonX, other.start.getX(), other.end.getX());
        } else {
            //if the b( in y=ax+b) isn't the same because the lines parallel they will never intersect.
            if (coordinateLineY != coordinateOtherY) {
                return false;
            }
            //checks if the lines in between or at least touch one of the coordinates of the other line.
            return checkTwoCoordinates(start.getX(), end.getX(), other.start.getX(), other.end.getX())
                    || checkCoordinateInLine(other.start.getX(), start.getX(), end.getX());
        }
    }

    /**
     * checks if two lines are crossing each other when one of them has infinite incline.
     *
     * @param other 2nd line.
     * @return true if two lines is crossing each other.
     */
    public Point infiniteIncline(Line other) {
        //the incline of the 2nd line.
        double inclineOtherLine = inclineOfLine(other.start.getX(), other.start.getY(),
                other.end.getX(), other.end.getY());
        //the b in the y=ax+b.
        double coordinateOtherLine = coordinateY(inclineOtherLine, other.end.getX(), other.end.getY());
        //y value ,for this x, we can use the start.getX because we know the x isn't changing for our line.
        double yPoint = yPoint(start.getX(), coordinateOtherLine, inclineOtherLine);
        //checks if y is on line1, and x is on line2.
        if (checkCoordinateInLine(yPoint, start.getY(), end.getY())
                && checkCoordinateInLine(start.getX(), other.start.getX(), other.end.getX())) {
            return new Point(start.getX(), yPoint);
        }
        return null;
    }

    /**
     * the function checks if the point,
     * in which two function are intersecting is between the two coordinates.
     *
     * @param checkPoint  the intersection x of a line.
     * @param coordinate1 the start point of a line.
     * @param coordinate2 the end point of a line.
     * @return boolean .
     */

    public boolean checkCoordinateInLine(double checkPoint, double coordinate1, double coordinate2) {
        return ((checkPoint <= coordinate1 && checkPoint >= coordinate2)
                || (checkPoint >= coordinate1 && checkPoint <= coordinate2));
    }

    /**
     * this function checks if one of two coordinates in line.
     *
     * @param x1   coordinate.
     * @param x2   coordinate.
     * @param head the start coordinate of a line.
     * @param tail the end coordinate of a line.
     * @return true or false
     */
    public boolean checkTwoCoordinates(double x1, double x2, double head, double tail) {
        /*
            checks if one of two coordinates is between the start and the end of our line.
         */
        return (x2 >= head && x2 <= tail)
                || (x2 >= tail && x2 <= head)
                || (x1 >= head && x1 <= tail)
                || (x1 >= tail && x1 <= head);
    }

    /**
     * the function returns the incline of a line.
     *
     * @param x1 coordinate of point.
     * @param y1 coordinate of point.
     * @param x2 coordinate of point.
     * @param y2 coordinate of point.
     * @return the incline of the line.
     */
    public double inclineOfLine(double x1, double y1, double x2, double y2) {
        return (y1 - y2) / (x1 - x2);
    }

    /**
     * the function returns the value of x when cutting with the y-axis.
     *
     * @param incline the incline of our line.
     * @param x1      coordinate.
     * @param y1      coordinate.
     * @return the cutting with y-axis as double.
     */
    public double coordinateX(double incline, double x1, double y1) {
        return x1 - (y1 / incline);
    }

    /**
     * the functions returns the b value of an equation.
     *
     * @param incline of the line.
     * @param x1      coordinate.
     * @param y1      coordinate.
     * @return the b value of the equation y=ax+b as double.
     */


    public double coordinateY(double incline, double x1, double y1) {
        return y1 - (incline * x1);
    }

    /**
     * the function returns the value of x of the intersection between the lines.
     *
     * @param intersectionA the b value of line A.
     * @param inclineA      the incline of line A.
     * @param intersectionB the b value of line B.
     * @param inclineB      the incline of line B.
     * @return double, the value of x in the intersection of the two lines.
     */
    public double commonX(double intersectionA, double inclineA, double intersectionB, double inclineB) {
        return (intersectionA - intersectionB) / (inclineB - inclineA);
    }

    /**
     * the function returns the value of y for a certain x.
     *
     * @param x       the value of x in ax+b=y.
     * @param b       the value of b in ax+b=y.
     * @param incline the value of a in ax+b=y.
     * @return the value of y for a certain x as double.
     */
    public double yPoint(double x, double b, double incline) {
        return incline * x + b;
    }

    /**
     * the function returns the value of x for a certain y.
     *
     * @param b       the b value in ax+b=y.
     * @param y       the y value in ax+b=y.
     * @param incline the a value.
     * @return double , the x value.
     */
    public double xPoint(double b, double y, double incline) {
        return (y - b) / incline;
    }

    /**
     * the function checks if a point is on the equation.
     *
     * @param x       coordinate.
     * @param y       coordinate.
     * @param incline the incline of an equation.
     * @param b       the value of b in y=ax+b.
     * @return true if the point is on the equation.
     */
    public boolean onLine(double x, double y, double incline, double b) {
        return (y - (incline * x) - b) == 0;
    }


    /**
     * the function checks if two lines are intersecting with each other and returns the point.
     * we first check if they intersect by using the isIntersecting function.
     * if the function returns false we return null. if it returns true it means that we have at least
     * one point that intersect. if they aren't parallel we know for sure it's exactly 1 point
     *
     * @param other line.
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Line ourLine = new Line(start(), end());
        /*
        if the isIntersecting function returns true, we check the if the lines intersecting.
         */
        if (isIntersecting(other)) {
            /*
            first three if's are for a condition in which one or two of the lines has infinite slope.
             */
            if (this.equals(other)) {
                return null;
            }
            if (start.getX() == end.getX() && other.start.getX() == other.end.getX()) {
                return intersectingParallelLines(other);
            }
            if (start.getX() == end.getX()) {
                return ourLine.infiniteIncline(other);
            }

            if (other.start.getX() == other.end.getX()) {
                return other.infiniteIncline(ourLine);
            }
            double inclineOther = inclineOfLine(other.start.getX(), other.start.getY(),
                    other.end.getX(), other.end.getY());

            double inclineLine = inclineOfLine(start.getX(), start.getY(),
                    end.getX(), end.getY());
            //if the lines are parallel .
            if (inclineOther == inclineLine) {
                return ourLine.intersectingParallelLines(other);
            }
            /*
             we know for sure the lines intersecting in exactly one point because, we checked
             it in the isIntersecting function. and they aren't parallel so there is only 1
             common point.
             */
            double coordinateOtherY = coordinateY(inclineOther, other.end.getX(), other.end.getY());
            double coordinateLineY = coordinateY(inclineLine, end.getX(), end.getY());
            double commonX = commonX(coordinateLineY, inclineLine, coordinateOtherY, inclineOther);
            double commonY = yPoint(commonX, coordinateLineY, inclineLine);
            return new Point(commonX, commonY);
        }
        return null;
    }

    /**
     * the function check the case of two parallel lines.
     * in this case the only way of having only 1 point of intersection is when
     * the two lines are touching either the start or the end of one another.
     *
     * @param other the other parallel line
     * @return geometryPrimitive.Point of intersection.
     */
    public Point intersectingParallelLines(Line other) {
        /*
        first 2 if's are the case in which one of the lines is a point.
         */
        if ((start.equals(end))
                && checkCoordinateInLine(start.getY(), other.start.getY(), other.end.getY())) {
            return start;
        }
        if ((other.start.equals(other.end))
                && checkCoordinateInLine(other.start.getY(), start.getY(), end.getY())) {
            return other.start;
        }
        /*
         this four if's are for a case in which one of the points
         is equal to the other point of the other line.
         in this case we want to check if this is the only point that touches the other line.
         */
        if (start.equals(other.start)
                //checks if the end.getX isn't between the two x's of the other line.
                && ((!(checkCoordinateInLine(end.getX(), other.start.getX(), other.end.getX()))
                //checks if the other.end x isn't between the two x's of our line.
                && !(checkCoordinateInLine(other.end.getX(), start.getX(), end.getX())))
                //checks if the end.getY isn't between the two Y's of the other line.
                || !(checkCoordinateInLine(end.getY(), other.start.getY(), other.end.getY()))
                //checks if the other.end y isn't between the two y's of our line.
                && !(checkCoordinateInLine(other.end.getY(), start.getY(), end.getY())))) {
            return start;
        }
        if (start.equals(other.end)
                && ((!(checkCoordinateInLine(end.getX(), other.start.getX(), other.end.getX()))
                && !(checkCoordinateInLine(other.start.getX(), start.getX(), end.getX())))
                || !(checkCoordinateInLine(end.getY(), other.start.getY(), other.end.getY()))
                && !(checkCoordinateInLine(other.start.getY(), start.getY(), end.getY())))) {
            return start;
        }
        if (end.equals(other.start)
                && ((!(checkCoordinateInLine(start.getX(), other.start.getX(), other.end.getX()))
                && !(checkCoordinateInLine(other.end.getX(), start.getX(), end.getX())))
                || !(checkCoordinateInLine(start.getY(), other.start.getY(), other.end.getY()))
                && !(checkCoordinateInLine(other.end.getY(), start.getY(), end.getY())))) {
            return end;
        }
        if (end.equals(other.end)
                && ((!(checkCoordinateInLine(start.getX(), other.start.getX(), other.end.getX()))
                && !(checkCoordinateInLine(other.start.getX(), start.getX(), end.getX())))
                || !(checkCoordinateInLine(start.getY(), other.start.getY(), other.end.getY()))
                && !(checkCoordinateInLine(other.start.getY(), start.getY(), end.getY())))) {
            return end;
        }
        return null;
    }

    /**
     * checks if two lines are exactly the same.
     *
     * @param other check if the other line is the same as our line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((start.equals(other.start)) && (end.equals(other.end)))
                || ((start.equals(other.end)) && (end.equals(other.start)));
    }

    /**
     * the function returns the point that is the closest to the start point.
     * note that a line can intersect with the rectangle up to two points.
     *
     * @param rect the rectangle.
     * @return point, the closest to start point in line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        //no close points.
        if (list.size() == 0) {
            return null;
        }
        //if only 1 point return it.
        if (list.size() == 1) {
            return list.get(0);
        }
        //if 2 points return the closer one.
        return this.closestPoint(list.get(0), list.get(1));
    }

    /**
     * checks which point is closer to a line.
     *
     * @param point1 first point.
     * @param point2 2nd point.
     * @return the closest point to a line.
     */
    public Point closestPoint(Point point1, Point point2) {
        //if the line has infinite incline check the y's.
        if (point1.getX() == point2.getX()) {
            if (Math.abs(point1.getY() - this.start().getY())
                    < Math.abs(point2.getY() - this.start().getY())) {
                return point1;
            } else {
                return point2;
            }
        }
        //checks if the first or 2nd point is closer to start.
        if (Math.abs(point1.getX() - this.start().getX())
                < Math.abs(point2.getX() - this.start().getX())) {
            return point1;
        } else {
            return point2;
        }

    }
}