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
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class StoreController extends PlayerTurnSceneController {

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

    MuleType currMule = MuleType.NONE;
    HBox hbox = new HBox();
    TextArea muleTypeText = new TextArea();


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        loadPlayerData();
        loadMulePics();
        registerOnClick();
    }

    @FXML
    private void loadMulePics() {
        ToggleGroup muleTypes = new ToggleGroup();
        for(MuleType m: MuleType.values()) {
            if(m != MuleType.NONE) {
                String path = m.getPath();
                String imagePath = StoreController.class.getResource(path).toExternalForm();
                ToggleButton mule = new ToggleButton("PICK ME!");
                mule.setToggleGroup(muleTypes);
                mule.setPrefWidth(150);
                mule.setPrefHeight(150);
                mule.setStyle("-fx-background-image: url('" + imagePath + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-size: cover");
                mule.setMaxWidth(Double.MAX_VALUE);
                mule.setMaxHeight(Double.MAX_VALUE);
                mule.setId(m.name());
                hbox.getChildren().add(mule);
            }
        }
        anchorPane.getChildren().add(hbox);        
    }

    @FXML
    private void registerOnClick() {
        for (Node node: hbox.getChildren()) {
            if (node instanceof ToggleButton) {
                ToggleButton newNode = (ToggleButton)node;
                newNode.setOnAction (e -> {
                    onMuleClicked(newNode.getId());
                });
            }
        }
    }

    @FXML
    private void onMuleClicked(String muleType) { 
        currMule = MuleType.valueOf(muleType);
        muleTypeText = new TextArea("SELECTED " + muleType + " MULE!");
        muleTypeText.setEditable(false);
        muleTypeText.setPrefHeight(50);
        AnchorPane.setBottomAnchor(muleTypeText, 10.0);
        anchorPane.getChildren().add(muleTypeText);       
    }

    @FXML
    public void attemptExit() {
        System.out.println("Entering town");
        TurnManager.getInstance().loadScene(GameScene.TOWN); // replace PLACEHOLDER with whatever name you added to the GameScene for store (probably "STORE") and then uncomment this line.
    }

    public int buy() {
        System.out.println("buy mule");
        if (cost.getCost() >  playersMoney.getMoney()) {
            System.out.println("The player does not have enough money");
            return 0;
        } 
        int result =  playersMoney.getMoney() - cost.getCost();
        ////Added this line after
        //buyMule(currMule);
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
        Player currPlayer = TurnManager.getInstance().getCurrentPlayer();
        Mule mule = new Mule(currMule, currPlayer);
        currPlayer.setMule(mule);
        TurnManager.getInstance().loadScene(GameScene.WORLD_VIEW);
    }
}

