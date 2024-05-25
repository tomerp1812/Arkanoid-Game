package Listeners;

import Others.Counter;
import differentSprites.Ball;
import differentSprites.Block;
import interfaces.HitListener;
import theGame.GameLevel;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private Ball[] balls;

    /**
     * constructor of blockRemover.
     *
     * @param game          a game object.
     * @param removedBlocks the counter of the amount of blocks remaining.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        setGame(game);
        setRemainingBlocks(removedBlocks);
    }

    /**
     * constructor of blockRemover.
     *
     * @param game          a game object.
     * @param removedBlocks the counter of the amount of blocks remaining.
     * @param balls         the array of all the balls in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks, Ball[] balls) {
        setGame(game);
        setRemainingBlocks(removedBlocks);
        setBalls(balls);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        for (Ball ball : balls) {
            ball.getGameEnvironment().removeCollidable(beingHit);
        }
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }

    /**
     * setter of game.
     *
     * @param game a game member.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * getter of game.
     *
     * @return the game.
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * setter of remainingBlocks.
     *
     * @param removedBlocks the amount of blocks remaining in the game.
     */
    public void setRemainingBlocks(Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
    }

    /**
     * getter of remainingBlocks.
     *
     * @return the amount of blocks remaining in the game.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * setter of balls.
     *
     * @param balls array of balls.
     */
    public void setBalls(Ball[] balls) {
        this.balls = balls;
    }

    /**
     * getter of balls.
     *
     * @return the array of balls.
     */
    public Ball[] getBalls() {
        return balls;
    }
}