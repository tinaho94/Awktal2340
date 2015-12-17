package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;


public class TestGameStateFactory {

    private GameStateFactory fact;

    @Before
    public void setup() {
        fact = new GameStateFactory();
        fact.setNumPlayers(1);
    }

    @Test
    public void testAddEmptyPlayer() {
        try {
            fact.addPlayer(new Player("", null, Race.HUMAN));
            fail("addPlayer did not fail when player had empty string for name.");
        } catch (GameStateConfigException e) {
            // test passes.
            return;
        }
    }

    @Test
    public void testAddTooManyPlayers() {
        try {
            fact.addPlayer(new Player("first", null, Race.HUMAN));
            try {
                fact.addPlayer(new Player("second", null, Race.HUMAN));
                fail("did not throw a UncheckedGameStateConfigException"
                    + " when too many players were added");
            } catch (UncheckedGameStateConfigException e) {
                // test passes.
                return;
            }
        } catch (GameStateConfigException e) {
            fail("threw GameStateConfigException instead of"
                + " UncheckedGameStateConfigException: " + e.getMessage());
        }
    }

    @Test
    public void testAddDuplicateName() {
        fact.setNumPlayers(2);
        try {
            fact.addPlayer(new Player("abc", null, Race.HUMAN));
            try {
                fact.addPlayer(new Player("abc", null, Race.HUMAN));
                fail("did not throw a GameStateConfigException when a"
                    + " duplicate player (name) was added");
            } catch (GameStateConfigException e) {
                // test passes.
                return;
            }
        } catch (GameStateConfigException e) {
            fail("threw a game state config exception on adding the first player");
        }
    }

    @Test
    public void testAddDuplicateColor() {
        fact.setNumPlayers(2);
        try {
            fact.addPlayer(new Player("abc", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN));
            try {
                fact.addPlayer(new Player("def", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN));
                fail("did not throw a GameStateConfigException when a duplicate "
                    + "player (color) was added");
            } catch (GameStateConfigException e) {
                // test passes.
                return;
            }
        } catch (GameStateConfigException e) {
            fail("threw a game state config exception on adding the first player");
        }
    }

    @Test
    public void testAddNameTooLong() {
        try {
            // 15 is the limit.
            fact.addPlayer(new Player("0123456789ABCDEFG",
                new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN));
            fail("did not throw a GameStateConfigException when name was too long");
        } catch (GameStateConfigException e) {
            // test passes.
            return;
        }
    }

    @Test
    public void testValidAdds() {
        Player[] cases = {
            new Player("a", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN),
            new Player("b", new Color(0.0, 0.0, 0.0, 0.1), Race.HUMAN),
            new Player("c", new Color(0.0, 0.0, 0.0, 0.2), Race.BONZOID),
            new Player("d", new Color(0.0, 0.0, 0.1, 0.0), Race.FLAPPER),
            new Player("e", new Color(0.0, 0.1, 0.0, 0.0), Race.BUZZITE)
        };
        fact.setNumPlayers(cases.length);
        for (Player p: cases) {
            try {
                fact.addPlayer(p);
            } catch (GameStateConfigException e) {
                fail("could not add player: " + p + " got unexpected exception: " + e.getMessage());
            }
        }
    }

}