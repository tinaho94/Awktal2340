package awktal.mule;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.Priority;
import javafx.scene.Node;

public class LandSelectionController extends SceneController implements Initializable {
    
    @FXML
    private GridPane gridpane;

    private Map currMap;

    /**
    * These variables refer to the tiles that were clicked
    */
    private int rowClicked;
    private int colClicked;
    private TileType typeClicked;


    public LandSelectionController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();        
        createImageViews();
        onClick();
    }

    /**
     * Creates an ImageView from Tile's path to picture and places the 
     * ImageView in Button then add to parent GridPane.
    */
    @FXML
    private void createImageViews() {
        for (Tile t: currMap) {
            String type = t.getType().toString();
            String path = TileType.valueOf(type).getPath();
            Image img = new Image(LandSelectionController.class.getResourceAsStream(path));
            ImageView imgView = new ImageView(img);
            Button button = new Button("",imgView);
            GridPane.setHgrow(button, Priority.ALWAYS);
            GridPane.setVgrow(button, Priority.ALWAYS);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
            //imgView.fitWidthProperty().bind(button.widthProperty());
            //imgView.fitHeightProperty().bind(button.heightProperty());
        }
    }

    /**
     * Checks for Tile Clicks
    */
    @FXML
    private void onClick() {
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction (e -> {
                    rowClicked = gridpane.getRowIndex(newNode);
                    colClicked = gridpane.getColumnIndex(newNode);
                    typeClicked = TileType.valueOf(newNode.getId());
                });

                    //-----below checks if the tile is owned-----
                //System.out.println(gameState.getPlayers());
                //boolean isOwned = new Tile(row, col, type).isOwned();
                //if(isOwned) { //checking if it is already owned, 
                /*
                    System.out.println("Sorry the land is already owned.");
                } else {
                    Tile newOwner = new Tile(row, col, type);
                    newOwner.setOwner(gameState.getCurrentPlayer());
                    System.out.println(newOwner + " has just bought land :)");
                }
                */
            }
        }
    }


/*
    The method below does...
   1) checks if player has enough $ to buy land
   2) Checks if the land is owned by another player via a boolean
   3) Deductd the land price from the players money
*/
    public static int deductMoney(int playerMoney, int landCost, boolean owned) { //landCost...should I just pass in the enrtire land and access the price in the method?
        if (!owned) {
            if (landCost >= playerMoney) {
                return playerMoney - landCost;
            } else {
                System.out.println("Player does not have enough $$$$.");
                return 0;
            }
        
        } else {
            System.out.println("The land is owned by another player.");
            return 0;
        
        }
    }
}