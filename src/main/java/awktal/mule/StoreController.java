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


public class StoreController extends PlayerTurnSceneController {

// Store (Stephen)
// Enter and leave store: done
// Buy and sell resources at store: 
// Can buy mule and outfit

    Store store;
    Player currentPlayer;

    @FXML
    private void initialize() {
        loadPlayerData();
        store = gameState.getStore();
        currentPlayer = gameState.getCurrentPlayer();
    }

    @FXML
    public void attemptExit() {
        System.out.println("Entering town");
        TurnManager.getInstance().loadScene(GameScene.TOWN); // replace PLACEHOLDER with whatever name you added to the GameScene for store (probably "STORE") and then uncomment this line.
    }


    /**
    * detects when the user slides the bar for the amnt to buy or sell
    */
    public void buyMule() {
        System.out.println("Buying mule");
    }
}

