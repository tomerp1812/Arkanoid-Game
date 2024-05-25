package interfaces;

import Others.Velocity;
import differentSprites.Block;
import theGame.GameLevel;

import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public interface LevelInformation {
    /**
     * getter of Balls.
     *
     * @return number of the balls in the level.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball, puts in list and returns it.
     *
     * @return a list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * getter of paddleSpeed.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * getter of paddleWidth.
     *
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * getter of levelName,  the level name will be displayed at the top of the screen.
     *
     * @return returns a string with name of the level.
     */
    String levelName();

    /**
     * getter of BackGround.
     *
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * adds to game the sprite.
     *
     * @param gameLevel the game level.
     */
    void addToGame(GameLevel gameLevel);

    /**
     * getter of sprites.
     *
     * @return the sprite.
     */
    HitListener getHitListeners();

    /**
     * getter of blocks, The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return returns a list of blocks.
     */
    List<Block> blocks();

    /**
     * getter of number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks in the game.
     */
    int numberOfBlocksToRemove();
}