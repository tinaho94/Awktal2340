package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;


public class TestCalculatePlayerTurnTime {

    private GameState gameState;

    /**
     * Sets up before each test.
    */
    @Before
    public void setup() {
        try {
            GameStateFactory fact = new GameStateFactory();
            fact.setMap(MapGenerator.generateMap(MapType.TRADITIONAL));
            fact.setNumPlayers(3);
            fact.addPlayer(new Player("Henry", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN));
            fact.addPlayer(new Player("Alex", new Color(0.0, 0.1, 0.0, 0.0), Race.HUMAN));
            fact.addPlayer(new Player("Chris", new Color(0.0, 0.0, 0.1, 0.0), Race.HUMAN));
            gameState = fact.createGameState();
            gameState.newRound();
            TurnManager.setGameState(gameState);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testPlayerWithNoFood() {
        Player currentPlayer = gameState.getCurrentPlayer();
        Inventory inventory = currentPlayer.getInventory();
        inventory.takeResource(Resource.FOOD, inventory.getResource(Resource.FOOD));
        TurnManager.getInstance().startCurrentPlayerClock();
        assertEquals(TurnManager.getInstance().getCurrentTurnTime(), 5);
    }

    @Test
    public void testPlayerWithLessThanRequirement() {
        Player currentPlayer = gameState.getCurrentPlayer();
        Inventory inventory = currentPlayer.getInventory();
        inventory.takeResource(Resource.FOOD, inventory.getResource(Resource.FOOD));
        inventory.giveResource(Resource.FOOD, 1);
        TurnManager.getInstance().startCurrentPlayerClock();
        assertEquals(TurnManager.getInstance().getCurrentTurnTime(), 30);
    }

    @Test
    public void testPlayerWithFoodRequirement() {
        Player currentPlayer = gameState.getCurrentPlayer();
        Inventory inventory = currentPlayer.getInventory();
        inventory.takeResource(Resource.FOOD, inventory.getResource(Resource.FOOD));
        inventory.giveResource(Resource.FOOD, 3);
        TurnManager.getInstance().startCurrentPlayerClock();
        assertEquals(TurnManager.getInstance().getCurrentTurnTime(), 50);
    }
}