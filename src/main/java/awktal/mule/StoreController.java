package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class StoreController extends PlayerTurnSceneController {


    Store store;
    Player currentPlayer;
    MuleType currMule = MuleType.NONE;
    HBox hbox = new HBox();
    TextArea muleTypeText = new TextArea();


    @FXML
    private Label food_price_label;

    @FXML
    private Label food_stock_label;

    @FXML
    private Label energy_price_label;

    @FXML
    private Label energy_stock_label;

    @FXML
    private Label ore_price_label;

    @FXML
    private Label ore_stock_label;

    @FXML
    private Label mule_stock_label;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        loadPlayerData();
        store = gameState.getStore();
        currentPlayer = gameState.getCurrentPlayer();
        loadMulePics();
        registerOnClick();
        loadStoreData();
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
        muleTypeText = new TextArea("SELECTED " + muleType + " MULE!\nCOST: " + store.getMuleCost(currMule) + " space-bucks");
        muleTypeText.setEditable(false);
        muleTypeText.setPrefHeight(50);
        AnchorPane.setBottomAnchor(muleTypeText, 10.0);
        anchorPane.getChildren().add(muleTypeText);

    }

    @FXML
    public void attemptExit() {
        System.out.println("Entering town");
        SceneManager.loadScene(GameScene.TOWN); // replace PLACEHOLDER with whatever name you added to the GameScene for store (probably "STORE") and then uncomment this line.
    }


    private void buyResource(Resource r) {
        try {
            store.buyResource(r, 1, currentPlayer);
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        loadStoreData();
    }

    private void sellResource(Resource r) {
        try {
            store.sellResource(r, 1, currentPlayer);
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        loadStoreData();
    }

    public void buyFood() {//check if they have enough $$ to buy food
        buyResource(Resource.FOOD);
    }


    public void sellFood() {
        sellResource(Resource.FOOD);
    }

    public void buyEnergy() {//check if they have enough $$ to buy food
        buyResource(Resource.ENERGY);
    }


    public void sellEnergy() {
        sellResource(Resource.ENERGY);
    }

    public void buyOre() {//check if they have enough $$ to buy food
        buyResource(Resource.ORE);
    }


    public void sellOre() {
        sellResource(Resource.ORE);
    }

    private void loadStoreData() {
        loadPlayerData();
        mule_stock_label.setText(String.valueOf(store.getNumMules()));
        food_price_label.setText(String.valueOf(store.getResourceCost(Resource.FOOD)));
        food_stock_label.setText(String.valueOf(store.getStock(Resource.FOOD)));
        energy_price_label.setText(String.valueOf(store.getResourceCost(Resource.ENERGY)));
        energy_stock_label.setText(String.valueOf(store.getStock(Resource.ENERGY)));
        ore_price_label.setText(String.valueOf(store.getResourceCost(Resource.ORE)));
        ore_stock_label.setText(String.valueOf(store.getStock(Resource.ORE)));
    }

    public void buyMule() {
        try {
            store.buyMule(currentPlayer, currMule);
            SceneManager.loadScene(GameScene.TOWN);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

