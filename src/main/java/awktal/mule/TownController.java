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

public class TownController extends SceneController {

    @FXML
    Button return_button;
    @FXML
    AnchorPane main_pane;
    @FXML
    AnchorPane town_pane;

    @FXML
    private void initialize() {
        Image img = new Image(TownController.class.getResourceAsStream("town_pictures/town.png"));
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        // Image img = new Image(TownController.class.getResourceAsStream("town_pictures/pallet_town.jpg"));
        town_pane.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size)));
    }


    public void loadLandSelect() {
        gameState.newRound();
        SceneManager.loadScene(GameScene.LAND_SELECTION);
    }




}