package differentSprites;


import Others.Counter;
import biuoop.DrawSurface;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import theGame.GameLevel;

import java.awt.Color;


/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle rectangle;
    private LevelInformation levelInformation;

    /**
     * constructor of ScoreIndicator.
     *
     * @param scoreCounter the score of the player.
     */
    public ScoreIndicator(Counter scoreCounter) {
        setScoreCounter(scoreCounter);
        setRectangle();
    }

    /**
     * constructor of ScoreIndicator.
     *
     * @param scoreCounter     the score of the player.
     * @param rect             the rectangle where we print the text.
     * @param levelInformation the level information.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle rect, LevelInformation levelInformation) {
        setScoreCounter(scoreCounter);
        setRectangle(rect);
        setLevelInformation(levelInformation);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        //fills the rectangle up in the gui with a color.
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
        d.setColor(Color.red);
        //draws the text at (350,17) coordinate, with the string being size of 20.
        d.drawText(350, 17, "Score: " + scoreCounter.getValue(), 20);
        d.drawText(500, 17, "Level Name: " + levelInformation.levelName(), 20);
    }

    @Override
    public void timePassed() {

    }

    /**
     * setter of Rectangle.
     *
     * @param rectangle the rectangle where we write the score.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * a default setter of rectangle.
     */
    public void setRectangle() {
        setRectangle(new Rectangle(new Point(0, 0), 800, 20));
    }

    /**
     * getter of rectangle.
     *
     * @return the rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * setter of ScoreCounter.
     *
     * @param scoreCounter the score of the player.
     */
    public void setScoreCounter(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * getter of ScoreCounter.
     *
     * @return the score.
     */
    public Counter getScoreCounter() {
        return scoreCounter;
    }

    /**
     * setter of level information.
     *
     * @param levelInformation the level information.
     */
    public void setLevelInformation(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * getter of level information.
     *
     * @return the level information.
     */
    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * adds scoreIndicator to the game.
     *
     * @param g a game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
