package interfaces;

/**
 * @param <C> a type of object.
 */
public interface Comparator<C> {
    /**
     * a compare function that compares two objects,
     * and returns true if they are equal.
     *
     * @param o1 object 1.
     * @param o2 object 2.
     * @return if two expressions equal returns true. otherwise false.
     */
    boolean compare(C o1, C o2);
}
