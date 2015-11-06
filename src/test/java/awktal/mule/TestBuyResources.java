package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;


public class TestBuyResources {

    private GameState gameState;

    @Before
    public void setup() {
        try {
            GameStateFactory fact = new GameStateFactory();
            fact.setMap(MapGenerator.generateMap(MapType.TRADITIONAL));
            fact.setNumPlayers(2);
            fact.addPlayer(new Player("Tina", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN));
            fact.addPlayer(new Player("Raul", new Color(0.0, 0.1, 0.0, 0.0), Race.HUMAN));
            gameState = fact.createGameState();
            gameState.newRound();
            TurnManager.setGameState(gameState);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testPlayerWithNoMoney() {
        try {
            Player currentPlayer = gameState.getCurrentPlayer();
            currentPlayer.takeResource(Resource.MONEY, currentPlayer.getResource(Resource.MONEY));
            Store store = gameState.getStore();
            store.buyResource(Resource.ENERGY, 1, currentPlayer);
            fail("Should have thrown a RuntimeException, player did not have enough money.");
        } catch(RuntimeException e) {
            return;
        }
    }

    @Test
    public void testStoreNoMoreResources() {
        try {
            Player currentPlayer = gameState.getCurrentPlayer();
            Store store = gameState.getStore();
            currentPlayer.giveResource(Resource.MONEY, 1000);
            store.buyResource(Resource.FOOD, 16, currentPlayer);
            store.buyResource(Resource.FOOD, 1, currentPlayer);
            assertEquals(currentPlayer.getResource(Resource.MONEY), 520);
        } catch(RuntimeException e) {
            return;
        }
    }
}