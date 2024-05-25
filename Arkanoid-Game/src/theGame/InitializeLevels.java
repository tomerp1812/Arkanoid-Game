package theGame;

import Levels.LevelFour;
import Levels.LevelOne;
import Levels.LevelThree;
import Levels.LevelTwo;
import interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Peisikov <\tomerp1812@gmail.com>
 * ID 209549542
 * @version ass6.
 * @since 2022/05/27.
 */
public class InitializeLevels {
    private final String[] args;
    private final LevelOne levelOne;
    private final LevelTwo levelTwo;
    private final LevelThree levelThree;
    private final LevelFour levelFour;

    /**
     * constructor of InitializeLevels.
     *
     * @param args the arguments of the main.
     */
    public InitializeLevels(String[] args) {
        this.args = args;
        levelOne = new LevelOne();
        levelTwo = new LevelTwo();
        levelThree = new LevelThree();
        levelFour = new LevelFour();
    }

    /**
     * the initializer of all the levels.
     *
     * @return a list of levels.
     */
    public List<LevelInformation> initialize() {
        List<LevelInformation> allLevels = new ArrayList<>();
        //if there are arguments that are levels we want to add them to the game.
        for (String arg : args) {
            if (arg.equals("1")) {
                allLevels.add(levelOne);
            }
            if (arg.equals("2")) {
                allLevels.add(levelTwo);
            }
            if (arg.equals("3")) {
                allLevels.add(levelThree);
            }
            if (arg.equals("4")) {
                allLevels.add(levelFour);
            }
        }
        //if no levels were added from the arguments we will add the default 4 levels.
        if (allLevels.size() == 0) {
            allLevels.add(levelOne);
            allLevels.add(levelTwo);
            allLevels.add(levelThree);
            allLevels.add(levelFour);
            return allLevels;
        }
        return allLevels;
    }
}
