package Levels;

import BackGroundDecorator.Temple;
import Others.Velocity;
import differentSprites.Block;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import interfaces.HitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;
import theGame.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class LevelTwo implements LevelInformation {
    private final List<Velocity> velocityList;
    private final List<Block> blockList;

    /**
     * constructor of level two.
     */
    public LevelTwo() {
        this.velocityList = new ArrayList<>();
        this.blockList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity velocity = new Velocity(-9 + 2 * i, -3);
            this.velocityList.add(velocity);
        }
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Temple();
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
    }

    @Override
    public HitListener getHitListeners() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = colors();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block block = new Block(colors[i], new Rectangle(new Point(20 + 50.7 * i, 200), 50.7, 20));
            this.blockList.add(block);
        }
        return this.blockList;
    }

    /**
     * makes array of colors.
     *
     * @return array of colors.
     */
    public Color[] colors() {
        Color[] colors = new Color[15];
        colors[0] = Color.red;
        colors[1] = Color.red;
        colors[2] = Color.orange;
        colors[3] = Color.orange;
        colors[4] = Color.yellow;
        colors[5] = Color.yellow;
        colors[6] = Color.green;
        colors[7] = Color.green;
        colors[8] = Color.green;
        colors[9] = Color.blue;
        colors[10] = Color.blue;
        colors[11] = Color.pink;
        colors[12] = Color.pink;
        colors[13] = Color.CYAN;
        colors[14] = Color.CYAN;
        return colors;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
