package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the processRandomEvent method from StartTurnController.
 */

public class TestProcessRandomEvent {
    private GameState state;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Map map;
    private StartTurnController controller;

    /**
     * Sets up the junit test for processRandomEvent.
     */
    @Before
    public void setup() throws GameStateConfigException {
        player1 = new Player("Player 1", Color.BEIGE, Race.HUMAN);
        player2 = new Player("Player 2", Color.AQUA, Race.FLAPPER);
        player3 = new Player("Player 3", Color.CORAL, Race.UGAITE);
        player4 = new Player("Player 4", Color.DARKGREEN, Race.BONZOID);
        map = MapGenerator.generateMap(MapType.TRADITIONAL);
        GameStateFactory factory = GameStateFactory.getInstance();
        factory.setNumPlayers(4);
        factory.addPlayer(player1);
        factory.addPlayer(player2);
        factory.addPlayer(player3);
        factory.addPlayer(player4);
        factory.setMap(map);
        state = factory.createGameState();
        state.newRound();
        state.newRound();
        SceneController.setGameState(state);
        controller = new StartTurnController();

    }

    @Test
    public void testEventOne() {
        int rand = controller.processRandomEvent(1);
        assertEquals(controller.getRandomEvent().getMessage(),
            "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
    }

    @Test
    public void testEventTwo() {
        int rand = controller.processRandomEvent(2);
        assertEquals(controller.getRandomEvent().getMessage(),
            "A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
    }

    @Test
    public void testEventThree() {
        int rand = controller.processRandomEvent(3);
        assertTrue(controller.getRandomEvent().getInventory().equals(new Inventory(8, 0, 0, 0)));
    }

    @Test
    public void testEventFour() {
        int rand = controller.processRandomEvent(4);
        assertTrue(controller.getRandomEvent().getInventory().equals(new Inventory(2, 0, 0, 0)));
    }

    @Test
    public void testEventFive() {
        int rand = controller.processRandomEvent(5);
        assertTrue(controller.getRandomEvent().getInventory().equals(new Inventory(-4, 0, 0, 0)));
    }

    @Test
    public void testEventSix() {
        int rand = controller.processRandomEvent(6);
        assertEquals(controller.getRandomEvent().getMessage(),
            "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
    }

    @Test
    public void testEventSeven() {
        int rand = controller.processRandomEvent(7);
        assertTrue(controller.getRandomEvent().getInventory().equals(new Inventory(-6, 0, 0, 0)));
    }
}