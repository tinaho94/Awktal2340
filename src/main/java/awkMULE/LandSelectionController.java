package awkMULE;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.awt.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.Parent;

public class LandSelectionController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button field;

    private static Stage stage;

    public LandSelectionController() {
        System.out.println("I am constructed");
    }

    @FXML
    private void initialize() {
        field.setOnAction(e -> {
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
    }

    public static void setStage(Stage stage) {
        LandSelectionController.stage = stage;
    }
}