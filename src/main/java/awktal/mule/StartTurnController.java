package awktal.mule;

import javafx.fxml.*;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import com.google.gson.Gson;

public class StartTurnController extends SceneController implements Initializable {

    @FXML
    private Label messageLabel;
    @FXML
    private GridPane gridpane;

    private Map currMap;

    private final static int RANDOM_EVENT_PROB = 100;

    private static final int[] m = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};

    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        createImageViews();
        boolean processRandomEvents = false;
        Player currentPlayer = gameState.getCurrentPlayer();
        for (Player p : gameState.getPlayers()) {
            if (!(p.equals(currentPlayer))) {
                if (currentPlayer.compareTo(p) > 0) {
                    processRandomEvents = true;
                }
            }
        }
        if (processRandomEvents) {
            processRandomEvent();
        }
    }

    private void createImageViews() {
        for (Tile t: currMap) {
            String type = t.getType().toString();
            String path = TileType.valueOf(type).getPath();
            Button button = new Button("");
            String imagePath = StartTurnController.class.getResource(path).toExternalForm();
            button.setStyle("-fx-background-image: url('" + imagePath + "'); " +
            "-fx-background-position: center center; " +
            "-fx-background-size: stretch");
            if (t.isOwned()) {
                button.setStyle("-fx-border-color: " + colorToHexString(t.getOwner().getColor()) + ";" +
                    "-fx-border-width: 5px;" +
                    "-fx-background-image: url('" + imagePath + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-size: stretch;");
            }
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
            if(t.hasMule()) {
                    installMule(t, t.getMule());
            }
        }
    }

    private void processRandomEvent() {
        // depending on event, call scale on enum's inventory and then give it to player
        RandomEvent events[] = {RandomEvent.ONE, RandomEvent.TWO, RandomEvent.THREE, RandomEvent.FOUR,
            RandomEvent.FIVE, RandomEvent.SIX, RandomEvent.SEVEN};
        Random r = new Random();
        int currentM = m[gameState.getRound() - 1];
        Player currentPlayer = gameState.getCurrentPlayer();
        int randomNum = r.nextInt(100) + 1;
        clearMessageDisplay();
        if (randomNum <= RANDOM_EVENT_PROB) {
            randomNum = r.nextInt(7) + 1;
            RandomEvent randomEvent = events[randomNum];
            if (randomEvent == RandomEvent.SIX) {
                currentPlayer.giveResources(randomEvent.inventory.scaleResource(0.5, Resource.FOOD));
                displayRandomEvent(randomEvent, 0);
            } else {
                Inventory newResources = randomEvent.getInventory().scaleResource(currentM, Resource.MONEY);
                currentPlayer.giveResources(newResources);
                int moneyAdded = Math.abs(newResources.getResource(Resource.MONEY));
                displayRandomEvent(randomEvent, moneyAdded);
            }
        }
    }

    private void clearMessageDisplay() {
        messageLabel.setText("");
    }

    private enum RandomEvent {
        ONE ("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.", new Inventory(0, 3, 2, 0)),
        TWO ("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.", new Inventory(0, 0, 0, 2)),
        THREE ("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $", new Inventory(8, 0, 0, 0)),
        FOUR ("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $", new Inventory(2, 0, 0, 0)),
        FIVE ("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $", new Inventory(-4, 0, 0, 0)),
        SIX ("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.", new Inventory(0, -1, 0, 0)),
        SEVEN ("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. TO CLEAN IT UP, IT COST YOU $", new Inventory(-6, 0, 0, 0));

        private String message;
        private Inventory inventory;

        private RandomEvent(String message, Inventory inventory) {
            this.message = message;
            this.inventory = inventory;
        }

        private Inventory getInventory() {
            return inventory;
        }

        private String getMessage() {
            return message;
        }

    }

    private void displayRandomEvent(RandomEvent randomEvent, int moneyAdded) {
        String message = randomEvent.getMessage();
        if (randomEvent == RandomEvent.THREE || randomEvent == RandomEvent.FOUR || randomEvent == RandomEvent.FIVE || randomEvent == RandomEvent.SEVEN) {
            message = message + moneyAdded + ".";
        }
        messageLabel.setText(message);
    }

    public void continueOn() {
        TurnManager.getInstance().startCurrentPlayerClock();
        SceneManager.loadScene(GameScene.WORLD_VIEW);
    }

    private String colorToHexString(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255));
    }

    private void installMule(Tile tile, Mule mule) {
        String type = mule.getType().toString();
        String path = MuleType.valueOf(type).getPath();
        String imagePath = WorldViewController.class.getResource(path).toExternalForm();
        Image image = new Image(imagePath);
        ImageView muleImage = new ImageView(image);
        muleImage.setFitWidth(50);
        muleImage.setFitHeight(50);
        gridpane.add(muleImage,tile.getX(), tile.getY());
    }

}