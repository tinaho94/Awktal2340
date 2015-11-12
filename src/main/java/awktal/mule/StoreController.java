package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StoreController extends PlayerTurnSceneController {


    Store store;
    Player currentPlayer;
    MuleType currMule = MuleType.NONE;
    HBox hbox = new HBox();
    TextArea muleTypeText = new TextArea();

    @FXML
    private VBox centerViewMuleTab;

    @FXML
    private Label foodPriceLabel;

    @FXML
    private Label foodStockLabel;

    @FXML
    private Label energyPriceLabel;

    @FXML
    private Label energyStockLabel;

    @FXML
    private Label orePriceLabel;

    @FXML
    private Label oreStockLabel;

    @FXML
    private Label muleStockLabel;

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
        for (MuleType m: MuleType.values()) {
            if (m != MuleType.NONE) {
                String path = m.getPath();
                String imagePath = StoreController.class.getResource(path).toExternalForm();
                ToggleButton mule = new ToggleButton("PICK ME!");
                mule.setToggleGroup(muleTypes);
                mule.setPrefWidth(150);
                mule.setPrefHeight(150);
                mule.setStyle("-fx-background-image: url('" + imagePath + "'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-size: stretch");
                mule.setMaxWidth(Double.MAX_VALUE);
                mule.setMaxHeight(Double.MAX_VALUE);
                mule.setId(m.name());
                hbox.getChildren().add(mule);
            }
        }
        centerViewMuleTab.getChildren().add(hbox);
        muleTypeText = new TextArea("No mule selected");

        muleTypeText.setEditable(false);
        muleTypeText.setPrefHeight(50);
        centerViewMuleTab.getChildren().add(muleTypeText);
    }

    @FXML
    private void registerOnClick() {
        for (Node node: hbox.getChildren()) {
            if (node instanceof ToggleButton) {
                ToggleButton newNode = (ToggleButton)node;
                newNode.setOnAction(e -> {
                        onMuleClicked(newNode.getId());
                    }
                );
            }
        }
    }

    @FXML
    private void onMuleClicked(String muleType) {
        currMule = MuleType.valueOf(muleType);
        muleTypeText.setText("SELECTED " + muleType + " MULE!\nCOST: "
            + store.getMuleCost(currMule) + " space-bucks");
    }

    @FXML
    public void attemptExit() {
        System.out.println("Entering town");
        SceneManager.loadScene(GameScene.TOWN);
    }


    private void buyResource(Resource resource) {
        try {
            store.buyResource(resource, 1, currentPlayer);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        loadStoreData();
    }

    private void sellResource(Resource resource) {
        try {
            store.sellResource(resource, 1, currentPlayer);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        loadStoreData();
    }

    public void buyFood() {
        buyResource(Resource.FOOD);
    }


    public void sellFood() {
        sellResource(Resource.FOOD);
    }

    public void buyEnergy() {
        buyResource(Resource.ENERGY);
    }


    public void sellEnergy() {
        sellResource(Resource.ENERGY);
    }

    public void buyOre() {
        buyResource(Resource.ORE);
    }


    public void sellOre() {
        sellResource(Resource.ORE);
    }

    private void loadStoreData() {
        loadPlayerData();
        muleStockLabel.setText(String.valueOf(store.getNumMules()));
        foodPriceLabel.setText(String.valueOf(store.getResourceCost(Resource.FOOD)));
        foodStockLabel.setText(String.valueOf(store.getStock(Resource.FOOD)));
        energyPriceLabel.setText(String.valueOf(store.getResourceCost(Resource.ENERGY)));
        energyStockLabel.setText(String.valueOf(store.getStock(Resource.ENERGY)));
        orePriceLabel.setText(String.valueOf(store.getResourceCost(Resource.ORE)));
        oreStockLabel.setText(String.valueOf(store.getStock(Resource.ORE)));
    }

    /**
     * Attempts to buy a mule for the player.
    */
    public void buyMule() {
        try {
            store.buyMule(currentPlayer, currMule);
            SceneManager.loadScene(GameScene.TOWN);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

