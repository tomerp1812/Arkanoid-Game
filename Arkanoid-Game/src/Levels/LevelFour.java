package Levels;

import BackGroundDecorator.IceCream;
import Others.Velocity;
import differentSprites.Block;
import geometryPrimitive.Point;
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
public class LevelFour implements LevelInformation {
    private final List<Velocity> velocityList;
    private final List<Block> blockList;
    /**
     * constructor of level four.
     */
    public LevelFour() {
        this.velocityList = new ArrayList<>();
        this.blockList = new ArrayList<>();
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity velocity = new Velocity(-5 + 2 * i, -4);
            this.velocityList.add(velocity);
        }
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new IceCream();
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
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Block block = new Block(colors[i],
                        new geometryPrimitive.Rectangle(new Point(20 + 50.7 * j, 100 + 20 * i), 50.7, 20));
                this.blockList.add(block);
            }

        }
        return this.blockList;
    }

    /**
     * makes array of colors.
     *
     * @return array of colors.
     */
    public Color[] colors() {
        Color[] colors = new Color[7];
        colors[0] = Color.gray;
        colors[1] = Color.red;
        colors[2] = Color.yellow;
        colors[3] = Color.blue;
        colors[4] = Color.white;
        colors[5] = Color.pink;
        colors[6] = Color.lightGray;
        return colors;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
