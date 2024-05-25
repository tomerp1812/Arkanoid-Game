package theGame;

import Listeners.BorderListener;
import differentScreens.KeyPressStoppableAnimation;
import differentScreens.CountdownAnimation;
import Others.Counter;
import Listeners.ScoreTrackingListener;
import differentScreens.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collections.GameEnvironment;
import collections.SpriteCollection;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import differentSprites.Ball;
import differentSprites.Block;
import differentSprites.Paddle;
import differentSprites.ScoreIndicator;
import geometryPrimitive.Point;
import geometryPrimitive.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;


/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class GameLevel implements Animation {
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private GUI gui;
    private LevelInformation levelInformation;
    private Counter scoreCounter;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ball[] balls;
    private final BlockRemover blockRemover;
    private final BallRemover ballRemover;
    private final ScoreTrackingListener scoreTrackingListener;
    private final ScoreIndicator scoreIndicator;
    private final Counter remainingBlocksCounter;
    private final Counter remainingBallsCounter;
    static final int WIN = 100;

    /**
     * constructor of game.
     *
     * @param levelInformation the level information parameter.
     * @param ks               the keyboard sensor object.
     * @param ar               the animation runner object.
     * @param gui              the guis object of the game.
     * @param score            the counter of the score of the game.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, GUI gui,
                     Counter score) {
        setLevelInformation(levelInformation);
        setKeyboard(ks);
        setRunner(ar);
        setGui(gui);
        setScoreCounter(score);

        this.sprites = new SpriteCollection();
        this.sprites.addSprite(levelInformation.getBackground());
        this.levelInformation.addToGame(this);

        remainingBlocksCounter = new Counter(0);
        remainingBallsCounter = new Counter(0);
        //sets the balls of the game
        setBalls(new Ball[levelInformation.numberOfBalls()]);
        blockRemover = new BlockRemover(this, remainingBlocksCounter, getBalls());
        ballRemover = new BallRemover(this, remainingBallsCounter);
        scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        //the background of the screen.
        Rectangle rectangle = new Rectangle(new Point(0, 0), 800, 20);
        scoreIndicator = new ScoreIndicator(scoreCounter, rectangle, levelInformation);
    }

    /**
     * setter of the score counter.
     *
     * @param scoreCounter the counter of the score of the game.
     */
    public void setScoreCounter(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * setter of gui.
     *
     * @param gui the gui object of the game.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * setter of runner.
     *
     * @param runner the animation runner.
     */
    public void setRunner(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * setter of keyboard sensor.
     *
     * @param keyboard returns the keyboard sensor object.
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * setter of level information.
     *
     * @param levelInformation the level we play.
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
     * getter of remaining balls.
     *
     * @return the amount of balls remaining in the game.
     */
    public Counter getRemainingBallsCounter() {
        return remainingBallsCounter;
    }

    /**
     * getter of remaining blocks.
     *
     * @return returns the amount of blocks remaining in the game.
     */
    public Counter getRemainingBlocksCounter() {
        return remainingBlocksCounter;
    }

    /**
     * setter of balls.
     * also gives them velocity and adds them to the game.
     *
     * @param balls the balls of the game.
     */
    private void setBalls(Ball[] balls) {
        Color color = new Color(198, 159, 123);
        /*
         * create new balls.
         */
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            //start the balls from the height of the paddle.
            balls[i] = new Ball(400, 570, 8, color);
            balls[i].setVelocity(levelInformation.initialBallVelocities().get(i));
            balls[i].addToGame(this);
        }
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

    /**
     * getter of game environment.
     *
     * @return game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * setter of game environment.
     *
     * @param gameEnvironment sets game environment.
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * getter of sprites.
     *
     * @return the sprites collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * setter of sprites.
     *
     * @param sprite sets the sprites.
     */
    public void setSprites(SpriteCollection sprite) {
        this.sprites = sprite;
    }


    /**
     * adds a colloidal object.
     *
     * @param c a colloidal object.
     */
    public void addCollidable(Collidable c) {
        //if the game environment is empty.
        if (environment == null) {
            environment = new GameEnvironment();
        }
        getEnvironment().addCollidable(c);
    }

    /**
     * removes a block from the collidable list.
     *
     * @param c a block which should be removed.
     */
    public void removeCollidable(Collidable c) {
        getEnvironment().removeCollidable(c);
    }

    /**
     * adds a sprite.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        if (sprites == null) {
            sprites = new SpriteCollection();
        }
        sprites.addSprite(s);
    }

    /**
     * removes a sprite from the sprite list.
     *
     * @param s a sprite that should be removed from the sprite list.
     */
    public void removeSprite(Sprite s) {
        getSprites().removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and diffSprites.Ball (and diffSprites.Paddle).
     * and add them to the game.
     */
    public void initialize() {
        //notifies the ballRemover that are 3 balls.
        remainingBallsCounter.setValue(levelInformation.numberOfBalls());
        //creates the borders of the game.
        createBorders();
        //creates the blocks of the game.
        createBlocks();
        //makes a new paddle.
        newPaddle();
        scoreIndicator.addToGame(this);
    }

    /**
     * creates the borders of the game.
     */
    public void createBorders() {
        //create blocks.
        Block[] borders = borders();
        BorderListener borderListener = new BorderListener();
        //the death border point, slightly below the screen.
        Point deathPointOfDeathBorder = new Point(20, 630);
        for (Block border : borders) {
            border.addToGame(this);
            for (Ball b : balls) {
                b.getGameEnvironment().addCollidable(border);
            }
            if (border.getCollisionRectangle().getUpperLeft().equals(deathPointOfDeathBorder)) {
                border.addHitListener(ballRemover);
                if (levelInformation.getHitListeners() != null) {
                    border.addHitListener(levelInformation.getHitListeners());
                }
            } else {
                border.addHitListener(borderListener);
            }
        }
    }

    /**
     * creates the blocks of the game.
     */
    public void createBlocks() {
        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = new Block(levelInformation.blocks().get(i).getColor(),
                    levelInformation.blocks().get(i).getCollisionRectangle());
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
            for (Ball b : balls) {
                b.getGameEnvironment().addCollidable(block);
            }
        }
        remainingBlocksCounter.setValue(levelInformation.numberOfBlocksToRemove());
    }

    /**
     * creates the new paddle of the game.
     */
    public void newPaddle() {
        //make the paddle almost perfectly in the middle of the screen.
        Rectangle paddleRectangle = new Rectangle(new Point(Math.abs(400 - (levelInformation.paddleWidth() / 2)), 570),
                levelInformation.paddleWidth(), 10);
        Paddle paddle = new Paddle(levelInformation.paddleSpeed(), gui, paddleRectangle, Color.orange);
        paddle.setCollisionRectangle(paddleRectangle);
        paddle.addToGame(this);
        for (Ball b : balls) {
            b.getGameEnvironment().addCollidable(paddle);
        }
    }

    /**
     * create 4 borders and return them as blocks.
     *
     * @return array of blocks. which are borders.
     */
    public Block[] borders() {
        Block[] borders = new Block[4];
        Rectangle[] rectangles = new Rectangle[4];
        Point top = new Point(0, 20);
        Point left = new Point(0, 40);
        Point right = new Point(780, 40);
        Point bottom = new Point(20, 630);
        rectangles[0] = new Rectangle(top, 800, 20);
        rectangles[1] = new Rectangle(left, 20, 610);
        rectangles[2] = new Rectangle(right, 20, 610);
        rectangles[3] = new Rectangle(bottom, 760, 20);
        for (int i = 0; i < borders.length; i++) {
            borders[i] = new Block(rectangles[i]);
        }
        return borders;
    }

    /**
     * the run function.
     */
    // Run the game -- start the animation loop.
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (blockRemover.getRemainingBlocks().getValue() == 0) {
            scoreTrackingListener.getCurrentScore().increase(WIN);
            this.running = false;
        }
        if (ballRemover.getRemainingBalls().getValue() == 0) {
            this.running = false;
        }
        //if p or caps lock P was pressed pause the game.
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}