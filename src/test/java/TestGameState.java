package awkMULE;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import awkMULE.GameState;
import awkMULE.GameStateConfigException;
import awkMULE.Player;

public class TestGameState {

    @Test(expected=UncheckedGameStateConfigException.class)
    public void testAddPlayerFail() {
        try {
            GameState state = GameState.getInstance();
            state.addPlayer(new Player("Henry", null, "Orc"));
        } catch (GameStateConfigException e) {
            return;
        }
        fail("GameStateConfigException not thrown");
    }
}