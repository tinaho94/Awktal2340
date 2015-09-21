package awktal.mule;

import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.awt.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;

public class LandSelectionController extends SceneController {
    
    @FXML
    private GridPane gridpane;

    private int row;
    private int col;
    private TileType type;


    
    //private MapType map;

    public LandSelectionController() {
    }

    /*
     * Initializes the land selection stage to be
     * constantly checking for if a mouse event has
     * happened and store the row and column of the
     * tile
     * 
    */
    @FXML
    private void initialize() {
        for (Node node: gridpane.getChildren()) {//
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction (e -> {
                    try {
                        row = gridpane.getRowIndex(newNode);
                    } catch (Exception f) {
                        row = 0;
                    }
                    try {
                        col = gridpane.getColumnIndex(newNode);
                    } catch (Exception g) {
                        col = 0;
                    }
                    System.out.println("row: " + row + "and col: " + col);
                    getTileType(newNode);
                    System.out.println("type of tile: " + type);

                    //-----below checks if the tile is owned-----
                    System.out.println(gameState.getPlayers());
                    boolean isOwned = new Tile(row, col, type).isOwned();
                    if(isOwned) { //checking if it is already owned, 
                        System.out.println("Sorry the land is already owned.");
                    } else {
                      Tile newOwner = new Tile(row, col, type);
                      newOwner.setOwner(gameState.getCurrentPlayer());
                      System.out.println(newOwner + " has just bought land :)");
                    }
                    //newNode.setStyle("-fx-background-color: transparent; -fx-border-color: #800080; -fx-border-width: 5px; ");
                });
            }
            
        }

        /*field.setOnAction(e -> {
                //trees1.setStyle("-fx-background-color: pink;");
                System.out.println("Button Pressed");
                field.setDisable(true);
                //field.getStylesheets().add(this.getClass().getResource("../../resources/awkMULE/DisabledButton.css").toExt‌​ernalForm());
                //field.getStylesheets().add("DisabledButton.css");
                field.setStyle("-fx-background-color: transparent; -fx-border-color: #800080; -fx-border-width: 5px; ");
                //field.setStyle("-fx-base: #800080;");
            }
        );
        System.out.println("Land select screen is shown.");
        */
    }

    public void getTileType(Button node) {
        String typeString = node.getId();
        type = TileType.valueOf(typeString);
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