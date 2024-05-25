package Levels;

import BackGroundDecorator.PackManBackGround;
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
public class LevelOne implements LevelInformation {
    private final List<Velocity> velocityList;
    private final List<Block> blockList;
    /**
     * constructor of level one.
     */
    public LevelOne() {
        this.velocityList = new ArrayList<>();
        this.blockList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity = new Velocity(0, -3);
        this.velocityList.add(velocity);
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new PackManBackGround();
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
        Block block = new Block(Color.red, new Rectangle(new Point(375, 150), 50, 50));
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
