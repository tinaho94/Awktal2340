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
        for (Node node: gridpane.getChildren()) {
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
}