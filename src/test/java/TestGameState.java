package awktal.mule;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import awktal.mule.GameState;
import awktal.mule.GameStateConfigException;
import awktal.mule.Player;

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