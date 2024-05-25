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
public class Temple extends BackGround {
    @Override
    public void drawOn(DrawSurface d) {
        Color gray = new Color(229, 224, 224);
        Color cyan = new Color(161, 201, 196);
        d.setColor(cyan);
        super.drawOn(d);

        //the basis of the temple.
        for (int i = 0; i < 4; i++) {
            int[] xTrapeze = {80 + i * 165, 90 + i * 165, 220 + i * 165, 230 + i * 165};
            int[] yTrapeze = {540, 525, 525, 540};
            Polygon trapeze = new Polygon(xTrapeze, yTrapeze, 4);
            d.setColor(Color.white);
            d.fillPolygon(trapeze);
            d.fillRectangle(90 + i * 165, 220, 130, 320);
            d.setColor(gray);
            for (int j = 0; j < 11; j++) {
                d.drawLine(90 + 12 * j + i * 165, 220, 90 + 12 * j + i * 165, 525);
            }

            //a lines that wrap the objects of the temple.
            d.setColor(Color.black);
            d.drawLine(80 + i * 165, 540, 90 + i * 165, 525);
            d.drawLine(220 + i * 165, 525, 230 + i * 165, 540);
            d.drawLine(90 + i * 165, 525, 220 + i * 165, 525);
            d.drawLine(90 + i * 165, 220, 90 + i * 165, 525);
            d.drawLine(220 + i * 165, 220, 220 + i * 165, 525);
            d.drawLine(90 + i * 165, 220, 220 + i * 165, 220);
        }

        //the roof of the temple.
        int[] xTriangle = {20, 390, 780};
        int[] yTriangle = {200, 40, 200};
        Polygon triangle = new Polygon(xTriangle, yTriangle, 3);
        d.setColor(Color.white);
        d.fillPolygon(triangle);
        d.setColor(Color.black);
        d.drawLine(20, 200, 390, 40);
        d.drawLine(20, 200, 780, 200);
        d.drawLine(390, 40, 780, 200);
        d.setColor(gray);

        //the inside triangle of the roof.
        int[] xInside = {110, 390, 690};
        int[] yInside = {180, 60, 180};
        Polygon inside = new Polygon(xInside, yInside, 3);
        d.fillPolygon(inside);
        d.setColor(Color.black);
        d.drawLine(110, 180, 390, 60);
        d.drawLine(110, 180, 690, 180);
        d.drawLine(390, 60, 690, 180);
        d.setColor(Color.white);
        d.fillRectangle(20, 580, 760, 20);
        d.fillRectangle(40, 560, 720, 20);
        d.fillRectangle(60, 540, 680, 20);
        d.setColor(Color.black);
        d.drawLine(20, 580, 780, 580);
        d.drawLine(40, 560, 760, 560);
        d.drawLine(60, 540, 740, 540);
        d.drawLine(40, 560, 40, 580);
        d.drawLine(760, 560, 760, 580);
        d.drawLine(60, 540, 60, 560);
        d.drawLine(740, 540, 740, 560);
    }
}
