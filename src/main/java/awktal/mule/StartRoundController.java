package awktal.mule;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.Priority;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.ImageCursor;
import java.util.Map.Entry;

public class StartRoundController extends SceneController implements Initializable {

    @FXML
    private GridPane gridpane;

    private Map currMap;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        createImageViews();
        for (Tile t : currMap) {
            Player owner = t.getOwner();
            if (owner != null && t.getMule() != null && owner.getResource(Resource.ENERGY) > 0) {
                Inventory i = t.calculateProduction();
                owner.takeResource(Resource.ENERGY, 1);
                System.out.println("taking energy from player " + owner.getName());

                String text = createIncomeLabel(i);
                Label label = new Label(text);
                owner.giveResources(i);
                //label.setStyle("-fx-text-fill: " + colorToHexString(t.getOwner().getColor()) + ";");
                gridpane.add(label, t.getX(), t.getY(), 1, 1);
            }
        }
    }

    private String createIncomeLabel(Inventory i) {
        String message = "";
        for (Entry<Resource, Integer> e : i.getResourcePairs()) {
            int q = e.getValue();
            Resource r = e.getKey();
            if (q > 0) {
                message += "+ ";
            } else if (q < 0) {
                message += "- ";
            } else {
                continue;
            }
            message += q + " " + r.name();
            message += "\n";
        }
        return message + "- 1 Energy";
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
        return String.format("#%02X%02X%02X", (int) (color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255));
    }

    @FXML
    public void continueOn() {
        if (!gameState.getPropertySelectionEnabled()) {
            System.out.println("Land selection has been disabled.");
            TurnManager.getInstance().beginPlayerTurns();
        } else {
            SceneManager.loadScene(GameScene.LAND_SELECTION);
        }
    }
}