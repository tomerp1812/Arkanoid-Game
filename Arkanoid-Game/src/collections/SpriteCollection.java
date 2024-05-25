package collections;

import biuoop.DrawSurface;
import interfaces.Comparator;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class SpriteCollection implements Comparator<Sprite> {
    private List<Sprite> sprites;
    private int counterSprites;

    /**
     * constructor of SpriteCollection.
     */
    public SpriteCollection() {
        counterSprites = 0;
    }

    /**
     * getter of CounterSprites.
     *
     * @return the counter of sprites.
     */
    public int getCounterSprites() {
        return counterSprites;
    }

    /**
     * getter of sprites.
     *
     * @return list of sprites.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * adds a sprite to the list.
     *
     * @param s a sprite to add.
     */
    public void addSprite(Sprite s) {
        //if the list is empty make one.
        if (sprites == null) {
            sprites = new ArrayList<>();
        }
        sprites.add(s);
        counterSprites++;
    }

    /**
     * the function removes a sprite from the sprites list.
     *
     * @param s a sprite that should be removed from the list.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> tmp = new ArrayList<>(sprites);
        for (Sprite sprite : tmp) {
            if (compare(sprite, s)) {
                //if we here we remove the sprite.
                sprites.remove(sprite);
                break;
            }
        }
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> s = new ArrayList<>(this.sprites);
        for (Sprite sprite : s) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> s = new ArrayList<>(this.sprites);
        for (Sprite sprite : s) {
            sprite.drawOn(d);
        }
    }

    @Override
    public boolean compare(Sprite o1, Sprite o2) {
        return o1.equals(o2);
    }
}