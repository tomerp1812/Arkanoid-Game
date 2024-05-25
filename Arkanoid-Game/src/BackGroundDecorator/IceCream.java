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
public class IceCream extends BackGround {
    @Override
    public void drawOn(DrawSurface d) {
        Color color = new Color(184, 182, 158);
        d.setColor(color);
        super.drawOn(d);
        //the pistachio ball.
        Color pistachio = new Color(73, 191, 29);
        d.setColor(pistachio);
        d.fillCircle(590, 315, 25);
        //vanilla ball.
        Color vanilla = new Color(239, 237, 221);
        d.setColor(vanilla);
        d.fillCircle(575, 340, 25);
        //chocolate ball.
        Color chocolate = new Color(50, 31, 4);
        d.setColor(chocolate);
        d.fillCircle(620, 340, 30);
        //the waffle.
        Color waffle = new Color(236, 166, 106);
        d.setColor(waffle);
        int[] x = new int[]{550, 650, 600};
        int[] y = new int[]{350, 350, 550};
        Polygon polygon = new Polygon(x, y, 3);
        d.fillPolygon(polygon);
        d.setColor(Color.black);
        //the lines on the waffle.
        for (int i = 0; i < 5; i++) {
            d.drawLine(555 + 10 * i, 370 + 40 * i, 641 - 9 * i, 386 + 36 * i);
        }
        for (int i = 0; i < 4; i++) {
            d.drawLine(570 + 20 * i, 350, 565 + 9 * i, 410 + 36 * i);
        }
        //the smiley face.
        d.setColor(Color.yellow);
        d.fillCircle(200, 380, 100);
        d.setColor(Color.black);
        d.fillOval(150, 330, 20, 50);
        d.fillOval(225, 330, 20, 50);
        d.fillOval(150, 420, 100, 40);
        d.setColor(Color.yellow);
        d.fillOval(150, 400, 100, 40);
    }
}
