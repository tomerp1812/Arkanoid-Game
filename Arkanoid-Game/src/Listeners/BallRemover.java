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
public class BallRemover implements HitListener {
    private Counter remainingBalls;
    private GameLevel game;

    /**
     * constructor of BallRemover.
     *
     * @param counter the amount of balls left in the game.
     * @param game    the game object.
     */
    public BallRemover(GameLevel game, Counter counter) {
        setRemainingBalls(counter);
        setGame(game);
    }

    /**
     * setter of remaining balls.
     *
     * @param remainingBalls the remaining balls in the game.
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    /**
     * getter of remaining balls.
     *
     * @return the remaining balls in the game.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * setter of game.
     *
     * @param game the game object.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * getter of game.
     *
     * @return the game object.
     */
    public GameLevel getGame() {
        return game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
