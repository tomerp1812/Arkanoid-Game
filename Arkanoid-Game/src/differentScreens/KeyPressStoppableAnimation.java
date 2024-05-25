package differentScreens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String pressedKey;
    private final Animation animation;
    private Boolean shouldStop;
    private boolean isAlreadyPressed;

    /**
     * constructor of KeyPressStoppableAnimation.
     *
     * @param sensor    the keyboard sensor object.
     * @param key       a string that holds the key that been pressed.
     * @param animation an animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.pressedKey = key;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(pressedKey) && !this.isAlreadyPressed) {
            this.shouldStop = true;
        }
        this.isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
