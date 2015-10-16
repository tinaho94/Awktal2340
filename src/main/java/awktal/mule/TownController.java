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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class TownController extends PlayerTurnSceneController {

    @FXML
    private Button return_button;
    @FXML
    private AnchorPane main_pane;
    @FXML
    private AnchorPane town_pane;
    @FXML
    private GridPane gridpane;

    // Current column that the player is in.
    private int currentCol;
    private Button player;
    private static final int PLAYER_ROW  = 4;
    private static final int LEFT_BOUNDRY = 1;
    private static final int RIGHT_BOUNDRY = 15;
    private static final BuildingType[] BUILDING_RANGES = { BuildingType.NONE, BuildingType.PUB, BuildingType.PUB,
                                                            BuildingType.NONE, BuildingType.LAND, BuildingType.LAND,
                                                            BuildingType.NONE, BuildingType.AUCTION, BuildingType.AUCTION, BuildingType.NONE,
                                                            BuildingType.NONE, BuildingType.STORE, BuildingType.STORE,
                                                            BuildingType.NONE, BuildingType.ASSAY, BuildingType.ASSAY,
                                                            BuildingType.NONE};

    @FXML
    private void initialize() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 17; col++) {
                String path = "town_pictures/town-" + row + "-" + col + ".jpeg";
                Button button = new Button("");
                // System.out.println(path);
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
        initializePlayer();
        setPlayerLocation(currentCol);
        initKeyListeners();
        loadPlayerData();
    }

    private void initializePlayer() {
        currentCol = LEFT_BOUNDRY;
        String imagePath = TownController.class.getResource("player_sprite.png").toExternalForm();
        String style = "-fx-background-image: url('" + imagePath + "'); ";
        style += "-fx-background-position: center center; ";
        style += "-fx-background-size: stretch; ";
        style += "-fx-background-color: transparent;";
        player = new Button("");
        player.setStyle(style);
    }

    private void movePlayerRight() {
        if (currentCol == RIGHT_BOUNDRY) {
            return;
        }
        currentCol += 1;
        setPlayerLocation(currentCol);
    }

    private void movePlayerLeft() {
        if (currentCol == LEFT_BOUNDRY) {
            return;
        }
        currentCol -= 1;
        setPlayerLocation(currentCol);
    }

    private void initKeyListeners() {
        main_pane.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    attemptEntrance();
                    break;
                case RIGHT:
                    movePlayerRight();
                    break;
                case LEFT:
                    movePlayerLeft();
                    break;
                default: break;
            }
        });
    }

    private void attemptEntrance() {
        switch (BUILDING_RANGES[currentCol]) {
            case PUB:
                // System.out.println("Entering pub");
                TurnManager.getInstance().loadScene(GameScene.PUB);
                break;
            case STORE:
                // System.out.println("Entering store");
                TurnManager.getInstance().loadScene(GameScene.STORE); // replace PLACEHOLDER with whatever name you added to the GameScene for store (probably "STORE") and then uncomment this line.
            default:
                break;
        }
    }

    private void setPlayerLocation(int col) {
        gridpane.getChildren().remove(player);
        gridpane.add(player, col, PLAYER_ROW, 1, 1);
    }

    public void onReturnButtonClick() {
        TurnManager.getInstance().loadScene(GameScene.WORLD_VIEW);
    }
}