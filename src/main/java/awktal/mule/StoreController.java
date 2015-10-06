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

// Store (Stephen)
// Enter and leave store: done
// Buy and sell resources at store: 
// Can buy mule and outfit

    //Inventory playersMoney = new Inventory(50,4,5,1);
    //use the connection of the GUI to parameters
    Inventory playersMoney = new Inventory(0,0,0,0,0);//.getMoney();
    Inventory baseCost = new Inventory(0,0,0,0,0);
    Inventory cost = new Inventory(0,0,0,0,0);
    //Player player = new Player();
    //GameState inventory = new Inventory(); 

    @FXML
    private void initialize() {
    }

    @FXML
    public void attemptExit() {
        System.out.println("Entering town");
        SceneManager.loadScene(GameScene.TOWN); // replace PLACEHOLDER with whatever name you added to the GameScene for store (probably "STORE") and then uncomment this line.
    }

    public int buy() {
        System.out.println("buy mule");
        if (cost.getCost() >  playersMoney.getMoney()) {
            System.out.println("The player does not have enough money");
            return 0;
        } 
        int result =  playersMoney.getMoney() - cost.getCost();
        return result;
    }

    public int sell() {
        System.out.println("sell mule");
        int result =  playersMoney.getMoney() + baseCost.getSellBack();
       return result;
    }

    /**
    * detects when the user slides the bar for the amnt to buy or sell
    */
    public void buyMule() {
        System.out.println("Buying mule");
    }
}

