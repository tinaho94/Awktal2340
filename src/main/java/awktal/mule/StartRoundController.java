package awktal.mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;


public class StartRoundController extends SceneController implements Initializable {

    @FXML
    private GridPane gridpane;

    private Map currMap;

    /**
     * Initialize the scene.
    */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        createImageViews();
        gameState.newRound();
        for (Tile t : currMap) {
            Player owner = t.getOwner();
            if (owner != null && t.getMule() != null && owner.getResource(Resource.ENERGY) > 0) {
                Inventory inventory = t.calculateProduction();
                owner.takeResource(Resource.ENERGY, 1);
                System.out.println("taking energy from player " + owner.getName());
                String text = createIncomeLabel(inventory);
                Label label = new Label(text);
                owner.giveResources(inventory);
                gridpane.add(label, t.getX(), t.getY(), 1, 1);
            }
        }
        gameState.recalculatePlayerOrder();
    }

    private String createIncomeLabel(Inventory inventory) {
        StringBuffer message = new StringBuffer();
        for (Entry<Resource, Integer> entry : inventory.getResourcePairs()) {
            int quantity = entry.getValue();
            Resource resource = entry.getKey();
            if (quantity > 0) {
                message.append("+ ");
            } else if (quantity < 0) {
                message.append("- ");
            } else {
                continue;
            }
            message.append(quantity + " " + resource.name());
            message.append("\n");
        }
        message.append("- 1 Energy");
        return message.toString();
    }

    /**
     * Creates an ImageView from Tile's path to picture and places the
     * ImageView in Button then add to parent GridPane.
    */
    @FXML
    private void createImageViews() {
        for (Tile t: currMap) {
            String type = t.getType().toString();
            String path = TileType.valueOf(type).getPath();
            Button button = new Button("");
            String imagePath = StartRoundController.class.getResource(path).toExternalForm();
            button.setStyle("-fx-background-image: url('" + imagePath + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size: stretch");
            if (t.isOwned()) {
                button.setStyle("-fx-border-color: " + colorToHexString(t.getOwner().getColor())
                    + ";"
                    + "-fx-border-width: 5px;"
                    + "-fx-background-image: url('" + imagePath + "'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-size: stretch;");
            }
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
            if (t.hasMule()) {
                installMule(t, t.getMule());
            }
        }
    }

    // for redraw of mule
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

    private String colorToHexString(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255),
            (int)(color.getGreen() * 255), (int)(color.getBlue() * 255));
    }

    /**
     * Handles what happens when the round is acknowledged by the players.
     * (When they press the continue button)
    */
    @FXML
    public void continueOn() {
        if (!gameState.getPropertySelectionEnabled()) {
            System.out.println("Land selection has been disabled.");
            SceneManager.loadScene(GameScene.START_TURN);
        } else {
            SceneManager.loadScene(GameScene.LAND_SELECTION);
        }
    }
}