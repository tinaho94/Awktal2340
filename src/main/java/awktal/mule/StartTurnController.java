package awktal.mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class is run at the start of each player's turn and calculates
 * their random events.
 */

public class StartTurnController extends SceneController implements Initializable {

    @FXML
    private GridPane gridpane;
    @FXML
    private TextArea messageTextArea;

    private Map currMap;

    private RandomEvent randomEvent;

    private static final int RANDOM_EVENT_PROB = 100;

    private static final int[] m = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};

    /**
     * Initializes the scene.
    */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        FxMapRenderer.renderMap(gridpane, currMap);
        if (gameState.getRound() > 1) {
            int moneyAdded = processRandomEvent(-1);
            if (randomEvent != null) {
                displayRandomEvent(randomEvent, moneyAdded);
            }
        }

    }

    /**
     * Processes random events for the current player.
     * @param event A given random event (for testing purposes)
     * @return The money added during the random event
     */
    public int processRandomEvent(int event) {
        RandomEvent[] events = {RandomEvent.ONE, RandomEvent.TWO, RandomEvent.THREE,
            RandomEvent.FOUR, RandomEvent.FIVE, RandomEvent.SIX, RandomEvent.SEVEN};
        int currentM = m[gameState.getRound() - 1];
        Player currentPlayer = gameState.getCurrentPlayer();
        Random generator = new Random();
        int randomNum = generator.nextInt(100) + 1;
        if (randomNum <= RANDOM_EVENT_PROB) {
            if (event > 0) {
                randomNum = event - 1;
            } else {
                randomNum = generator.nextInt(7);
            }
            if (randomNum > 3 && gameState.getCurrentPlayerIndex() == 0) {
                processRandomEvent(-1);
            }
            randomEvent = events[randomNum];
            if (randomEvent == RandomEvent.SIX) {
                Inventory newResources = new Inventory();
                newResources.giveResource(Resource.FOOD,
                    (int) (currentPlayer.getResource(Resource.FOOD) * -0.5));

                currentPlayer.giveResources(newResources);
                return 0;
            } else {
                Inventory newResources = randomEvent.getInventory().scaleResource(
                    currentM, Resource.MONEY);
                currentPlayer.giveResources(newResources);
                int moneyAdded = Math.abs(newResources.getResource(Resource.MONEY));
                return moneyAdded;
            }
        } else {
            randomEvent = null;
            return 0;
        }
    }

    /**
     * Describes the random events that can happen.
     */
    public enum RandomEvent {
        ONE ("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.",
            new Inventory(0, 3, 2, 0)),
        TWO ("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.",
            new Inventory(0, 0, 0, 2)),
        THREE ("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $",
            new Inventory(8, 0, 0, 0)),
        FOUR ("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $",
            new Inventory(2, 0, 0, 0)),
        FIVE ("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $",
            new Inventory(-4, 0, 0, 0)),
        SIX ("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.",
            new Inventory(0, -1, 0, 0)),
        SEVEN ("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. TO CLEAN IT UP, IT COST YOU $",
            new Inventory(-6, 0, 0, 0));

        private String message;
        private Inventory inventory;

        private RandomEvent(String message, Inventory inventory) {
            this.message = message;
            this.inventory = inventory;
        }

        /**
         * Constructs a random event.
         * @return the inventory of the random event
         */
        public Inventory getInventory() {
            return inventory;
        }

        /**
         * Constructs a random event.
         * @return the message of the random event
         */
        public String getMessage() {
            return message;
        }
    }

    /**
     * Displays random events for the current player.
     * @param randomEvent A given random event
     * @param moneyAdded The money added during a given random event
     */
    private void displayRandomEvent(RandomEvent randomEvent, int moneyAdded) {
        String message = randomEvent.getMessage();
        if (randomEvent == RandomEvent.THREE || randomEvent == RandomEvent.FOUR
            || randomEvent == RandomEvent.FIVE || randomEvent == RandomEvent.SEVEN) {
            message = message + moneyAdded + ".";
        }
        messageTextArea.setText(message);
    }

    /**
     * On clicking the continue button will go to World View.
     */
    public void continueOn() {
        SceneManager.loadScene(GameScene.WORLD_VIEW);
        TurnManager.getInstance().startCurrentPlayerClock();
    }

    /**
     * Returns the random event for the current player.
     * @return The random event for the current player.
     */
    public RandomEvent getRandomEvent() {
        return randomEvent;
    }
}