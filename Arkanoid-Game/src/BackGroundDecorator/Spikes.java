package BackGroundDecorator;

import Others.Counter;
import biuoop.DrawSurface;
import differentSprites.Ball;
import differentSprites.Block;
import theGame.GameLevel;

import java.awt.Color;
import java.awt.Polygon;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Spikes extends BackGround {
    private int height = 578;
    private int position = 0;
    private int sigma = 8;
    private Counter counter;

    /**
     * constructor of Spikes.
     *
     * @param counter to count the times between two positions of the spike.
     */
    public Spikes(Counter counter) {
        setCounter(counter);
    }

    /**
     * setter of counter.
     *
     * @param counter counter of times between position of spikes.
     */
    private void setCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        //draws the default background.
        super.drawOn(d);

        //draws the sign
        Color signColor1 = new Color(175, 165, 116);
        d.setColor(signColor1);
        //the sign points.
        int[] x1Sign = new int[]{225, 225, 205, 205, 215, 165, 115, 125, 125, 105, 105, 165};
        int[] y1Sign = new int[]{140, 185, 225, 395, 395, 485, 395, 395, 225, 185, 140, 175};
        Polygon signPolygon1 = new Polygon(x1Sign, y1Sign, 12);
        d.fillPolygon(signPolygon1);
        d.fillRectangle(0, 250, 220, 25);

        Color signColor2 = new Color(68, 56, 11);
        d.setColor(signColor2);
        //the sign points.
        int[] x2Sign = new int[]{220, 220, 200, 200, 210, 165, 120, 130, 130, 110, 110, 165};
        int[] y2Sign = new int[]{145, 180, 220, 400, 400, 480, 400, 400, 220, 180, 145, 180};
        Polygon signPolygon2 = new Polygon(x2Sign, y2Sign, 12);
        d.fillPolygon(signPolygon2);
        d.fillRectangle(0, 250, 220, 25);

        //writes hell in the sign.
        Color hellColor = new Color(80, 2, 2);
        d.setColor(hellColor);

        //the letter H.
        int[] hX = new int[]{140, 190, 190, 170, 170, 190, 190, 140, 140, 160, 160, 140};
        int[] hY = new int[]{200, 200, 210, 210, 230, 230, 240, 240, 230, 230, 210, 210};
        Polygon hPolygon = new Polygon(hX, hY, 12);
        d.fillPolygon(hPolygon);

        //the letter E.
        int[] eX = new int[]{140, 190, 190, 180, 180, 170, 170, 160, 160, 150, 150, 140};
        int[] eY = new int[]{250, 250, 290, 290, 260, 260, 290, 290, 260, 260, 290, 290};
        Polygon ePolygon = new Polygon(eX, eY, 12);
        d.fillPolygon(ePolygon);

        //the letter L.
        int[] l1X = new int[]{140, 190, 190, 155, 155, 140};
        int[] l1Y = new int[]{300, 300, 315, 315, 345, 345};
        Polygon l1Polygon = new Polygon(l1X, l1Y, 6);
        d.fillPolygon(l1Polygon);

        //the letter L.
        int[] l2X = new int[]{140, 190, 190, 155, 155, 140};
        int[] l2Y = new int[]{355, 355, 370, 370, 400, 400};
        Polygon l2Polygon = new Polygon(l2X, l2Y, 6);
        d.fillPolygon(l2Polygon);

        Color spikesColor = new Color(9, 47, 26, 255);
        d.setColor(spikesColor);
        int[] ySpikes;
        //draws the spikes.
        for (int i = 0; i < 17; i++) {
            if (i % 2 == 0) {
                //gets the y's of the lower spikes.
                ySpikes = new int[]{620, 620, height + sigma};
            } else {
                //gets the y's of the higher spikes.
                ySpikes = new int[]{620, 620, height - sigma};
            }
            int width = 15;
            //gets the x's of the spike.
            int[] xSpikes = {width + (i * 45), width + 40 + (i * 45), width + 20 + (i * 45)};
            Polygon spikesPolygon = new Polygon(xSpikes, ySpikes, 3);
            //draws the triangle (spike).
            d.fillPolygon(spikesPolygon);
        }
    }

    @Override
    public void timePassed() {
        //makes the spikes move up and down.
        if (counter.getValue() == 0) {
            sigma = 8;
            position = 0;
            counter.increase(1);
            return;
        }
        if (counter.getValue() == 15) {
            sigma = -8;
            position = 1;
            height = 578;
            counter.decrease(1);
            return;
        }
        if (position == 1) {
            counter.decrease(1);
            return;
        }
        counter.increase(1);
    }

    /**
     * adds decorate to the game.
     *
     * @param gameLevel a game object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //make all the spikes to jump when a ball is fallen.
        counter.setValue(60);
        position = 1;
        height = 550;
    }
}
