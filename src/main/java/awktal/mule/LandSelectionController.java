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
import javafx.geometry.Pos;
import java.util.HashMap;

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

    private HashMap<String, Tile> map = new HashMap<String, Tile>();//the hashmap to conntain the tileObjects

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
            Button button = new Button("");
            String imagePath = LandSelectionController.class.getResource(path).toExternalForm();
            button.setStyle("-fx-background-image: url('" + imagePath + "'); " +
            "-fx-background-position: center center; " +
            "-fx-background-size: cover");
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
        }
    }

    /**
     * Checks for Tile Clicks and changes border color
     * Right now, I only changed to coral but later on
     * change to the current player
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
                    String path = TileType.valueOf(typeClicked.toString()).getPath();
                    String imagePath = LandSelectionController.class.getResource(path).toExternalForm();
                    newNode.setStyle("-fx-background-image: url('" + imagePath + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-size: cover; -fx-border-color: coral; -fx-border-size: 50px");
                    System.out.println("row: " + rowClicked + ", col: " +
                        colClicked + ", type: " + typeClicked.toString());
                    //check if theres a duplicate. row, col, type
            
     //--------------------------
                    String rowColType = rowClicked + colClicked + typeClicked.toString();
                    Tile value = map.get(rowColType);
                    Tile tile = null;
                    if (value != null) {//checking if the tile object exists...meaning some player owns it.
                        //already a tile there
                        tile = value;
                    } else { //it does not exist in the hashMap...meaning the tile is not owned
                        tile = new Tile(rowClicked, colClicked, typeClicked);
                        map.put(rowColType, tile);
                    }
                    //if rowclicked, colclicked, typeclicked - - the tile already exists
                    // meaning that you would NOT make a new tile
                    boolean isOwned = tile.isOwned();
                    if(isOwned) { //checking if it is already owned, 
                        System.out.println("Sorry the land is already owned.");
                    } else {
                        tile.setOwner(gameState.getCurrentPlayer());
                        System.out.println(tile.getOwner() + " has just bought land :)");
                        //end turn, and set button off.
                    }
                });
            }
        }
              
    }


/*
    The method below does...
   1) checks if player has enough $ to buy land
   2) Checks if the land is owned by another player via a boolean
   3) Deductd the land price from the players money
*/

    // public static int deductMoney(int playerMoney, int landCost, boolean owned) { //landCost...should I just pass in the enrtire land and access the price in the method?
    //     if (!owned) {
    //         if (landCost >= playerMoney) {
    //             return playerMoney - landCost;
    //         } else {
    //             System.out.println("Player does not have enough $$$$.");
    //             return 0;
    //         }
        
    //     } else {
    //         System.out.println("The land is owned by another player.");
    //         return 0;
        
    //     }
    // }
}
