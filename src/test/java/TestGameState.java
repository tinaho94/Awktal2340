package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;


public class TestGameState {

    @Test
    public void testAddPlayerFail() {
        try {
            GameState state = new GameState();
            state.addPlayer(new Player("", null, Race.HUMAN));
            fail("addPlayer did not fail when player had empty string for name.");
        } catch (GameStateConfigException e) {
            // test passes.
            return;
        }
    }
}