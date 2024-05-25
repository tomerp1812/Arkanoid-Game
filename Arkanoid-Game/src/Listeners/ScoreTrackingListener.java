package Listeners;

import Others.Counter;
import differentSprites.Ball;
import differentSprites.Block;
import interfaces.HitListener;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    static final int POINTS = 5;

    /**
     * constructor of scoreTrackingListener.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * getter of CurrentScore.
     *
     * @return the current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(POINTS);
    }
}