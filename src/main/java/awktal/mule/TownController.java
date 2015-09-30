package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class TownController extends SceneController {

    @FXML
    private Button return_button;
    @FXML
    private AnchorPane main_pane;
    @FXML
    private AnchorPane town_pane;
    @FXML
    private GridPane gridpane;

    // Current column that the player is in.
    private int currentCol = 0;

    private static final BuildingType[] BUILDING_RANGES = { BuildingType.NONE, BuildingType.PUB, BuildingType.PUB,
                                                            BuildingType.NONE, BuildingType.LAND, BuildingType.LAND,
                                                            BuildingType.NONE, BuildingType.AUCTION, BuildingType.AUCTION, BuildingType.NONE,
                                                            BuildingType.NONE, BuildingType.STORE, BuildingType.STORE,
                                                            BuildingType.NONE, BuildingType.ASSAY, BuildingType.ASSAY,
                                                            BuildingType.NONE};

    @FXML
    private void initialize() {
        Image img = new Image(TownController.class.getResourceAsStream("town_pictures/town.png"));
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        // Image img = new Image(TownController.class.getResourceAsStream("town_pictures/pallet_town.jpg"));
        // town_pane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size)));
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 17; col++) {
                String path = "town_pictures/town-" + row + "-" + col + ".jpeg";
                Button button = new Button("");
                System.out.println(path);
                String imagePath = TownController.class.getResource(path).toExternalForm();
                String style = "";
                style += "-fx-background-image: url('" + imagePath + "'); ";
                style += "-fx-background-position: center center; ";
                style += "-fx-background-size: stretch";
                button.setStyle(style);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                gridpane.add(button, col, row, 1, 1);
            }
        }
    }

    public void onUpKeyPressed() {
        if (BUILDING_RANGES[currentCol] == BuildingType.PUB) {
            System.out.println("entered pub");
        }
    }

    public void loadLandSelect() {
        gameState.newRound();
        if (!gameState.getPropertySelectionEnabled()) {
            System.out.println("Land selection has been disabled.");
            return;
        }
        SceneManager.loadScene(GameScene.LAND_SELECTION);
    }

}