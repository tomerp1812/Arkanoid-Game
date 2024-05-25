package BackGroundDecorator;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Polygon;


/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class PackManBackGround extends BackGround {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        super.drawOn(d);
        d.setColor(Color.yellow);
        //the head of the packMan.
        d.fillCircle(350, 175, 100);
        //the mouth of the packMan.
        int[] x = new int[]{350, 430, 500, 450};
        int[] y = new int[]{175, 80, 175, 275};
        Polygon polygon = new Polygon(x, y, 4);
        d.setColor(Color.black);
        d.fillPolygon(polygon);
        d.setColor(Color.white);
        d.fillCircle(330, 120, 25);
        d.setColor(Color.blue);
        d.fillCircle(330, 120, 15);

    }
}
