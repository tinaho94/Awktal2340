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


public class StoreController extends SceneController{

    @FXML
    AnchorPane main_pane;
    @FXML
    AnchorPane mule_pane;
    @FXML
    AnchorPane food_pane;
    // @FXML
    // AnchorPane mine_pane;
    // @FXML
    // AnchorPane energy_pane;
   

    @FXML
    private void initialize() {
        Image screen = new Image(StoreController.class.getResourceAsStream("store_pictures/store.png"));
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        main_pane.setBackground(new Background(new BackgroundImage(screen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, size)));
    }


    public void loadLandSelect() {
        SceneManager.loadScene(GameScene.STORE);
    }

}