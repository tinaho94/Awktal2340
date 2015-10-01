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


public class StoreController extends SceneController{


    //@FXML
    //JButton done;
    // @FXML
    // AnchorPane food_pane; 

    @FXML
    private void initialize() {
    }


    // public void loadLandSelect() {
    //     SceneManager.loadScene(GameScene.STORE);
    // }

    @FXML
    public void attemptExit() {
        System.out.println("Entering town");
        SceneManager.loadScene(GameScene.TOWN); // replace PLACEHOLDER with whatever name you added to the GameScene for store (probably "STORE") and then uncomment this line.
    }

    
    private int buy(int cost, int playersMoney) {
        if (cost > playersMoney) {
            System.out.println("The player does not have enough money");
            return 0;
        } 
        return playersMoney - cost;
    }


    private int sell(int baseCost, int playersMoney, int soldAmnt) {
       return playersMoney + baseCost * soldAmnt;
    }
}