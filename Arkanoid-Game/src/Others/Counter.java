package Others;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class Counter {
    private int value;

    /**
     * constructor of Counter.
     *
     * @param value current count.
     */
    public Counter(int value) {
        setValue(value);
    }

    /**
     * add number to current count.
     *
     * @param number the number we increase from the amount of blocks.
     */
    public void increase(int number) {
        setValue(getValue() + number);
    }

    /**
     * subtract number from current count.
     *
     * @param number the number that we decrease from the amount of blocks.
     */
    public void decrease(int number) {
        setValue(getValue() - number);
    }

    /**
     * getter of value.
     *
     * @return the amount of blocks remaining in the game.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * setter of value.
     *
     * @param value the amount of blocks remaining in the game.
     */
    public void setValue(int value) {
        this.value = value;
    }
}