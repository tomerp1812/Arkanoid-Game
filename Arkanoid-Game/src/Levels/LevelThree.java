package Levels;

import BackGroundDecorator.Spikes;
import Others.Counter;
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
public class LevelThree implements LevelInformation {
    private final Spikes spikes;
    private final List<Velocity> velocityList;
    private final List<Block> blockList;

    /**
     * constructor of level three.
     */
    public LevelThree() {
        this.spikes = new Spikes(new Counter(10));
        this.velocityList = new ArrayList<>();
        this.blockList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 5;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity velocity = new Velocity(-5 + 2 * i, -6);
            this.velocityList.add(velocity);
        }
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.spikes;
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        this.spikes.addToGame(gameLevel);
    }

    @Override
    public HitListener getHitListeners() {
        return this.spikes;
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = colors();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Rectangle rectangle = new Rectangle(new Point(730 - j * 50, 150 + i * 20), 50, 20);
                Block block = new Block(colors[i], rectangle);
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
        Color[] colors = new Color[5];
        colors[0] = Color.gray;
        colors[1] = Color.green;
        colors[2] = Color.yellow;
        colors[3] = Color.blue;
        colors[4] = Color.white;
        return colors;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
