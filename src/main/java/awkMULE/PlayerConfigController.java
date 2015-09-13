package awkMULE;

import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.ColorPicker;

/**
 * PlayerConfigController controls the player configuration screen.
 * This will be automatically created when the fxml is loaded.
*/
public class PlayerConfigController {
    @FXML
    private Button done;

    @FXML
    private TextField nameInput;

    @FXML
    private ColorPicker colorInput;

    private static Stage stage;

    /** 
     * Constructor for a PlayerConfigController.
    */
    public PlayerConfigController() {
        System.out.println("PlayerConfigController constructed");
    }

    /**
     * Will be called after the constructor when the fxml is loaded.
    */
    @FXML
    private void initialize() {
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameState state = GameState.getInstance();
                try {
                    processPlayerConfig();
                    if (state.getMaxPlayers() == state.getNumPlayers()) {
                        goToLandSelect();
                        return;
                    } else {
                        goToPlayerConfig();
                        return;
                    }
                } catch(Exception e) {
                    // Depending on exception type give different responses.
                    System.out.println(e.toString());
                    nameInput.setStyle("-fx-background-color: red;");
                }
            }
        });
    }

    private void goToLandSelect() {
        System.out.println("Going to land select");
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("land_selection.fxml"));
            LandSelectionController.setStage(stage);
            Scene scene = new Scene(screen, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setTitle("MULE");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    private void goToPlayerConfig() {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("player_config.fxml"));
            PlayerConfigController.setStage(stage);
            Scene scene = new Scene(screen, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setTitle("MULE");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    private void processPlayerConfig() throws Exception {
        GameState.getInstance().addPlayer(new Player(nameInput.getCharacters().toString(), colorInput.getValue(), "Human"));
        System.out.println(GameState.getInstance().getPlayers());
    }

    /**
     * Sets the stage for the controller.
     * This must be called before the controller is given control of the stage.
     * @param stage the stage that the controller will take over (window).
    */
    public static void setStage(Stage stage) {
        PlayerConfigController.stage = stage;
    }
}