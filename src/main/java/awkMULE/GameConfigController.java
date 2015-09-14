package awkMULE;

import java.awt.Color;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Slider;

/**
 * GameConfigController controls the game configuration screen.
 * This will be automatically created when the fxml is loaded.
*/
public class GameConfigController {
    @FXML
    private Button done;

    @FXML
    private Slider numPlayers;

    private static Stage stage;

    /**
     * Constructor for a GameConfigController.
    */
    public GameConfigController() {
        System.out.println("I am constructed");
    }

    /**
     * Will be called after the constructor when the fxml is loaded.
    */
    @FXML
    private void initialize() {
        done.setOnAction(e -> {
                processGameConfig();
                startPlayerConfig();
            }
        );
        System.out.println("I am initialized");
    }

    private void startPlayerConfig() {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("player_config.fxml"));
            PlayerConfigController.setStage(stage);
            Scene scene = new Scene(screen, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setTitle("MULE");
            stage.setScene(scene);
        } catch(Exception e) {
            System.out.println("You have failed");
            // Throw some runtime exception.
        }
    }

    private void processGameConfig() {
        GameState.getInstance().setMaxPlayers((int) numPlayers.getValue());
    }

    /**
     * Sets the stage for the controller.
     * This must be called before the controller is given control of the stage.
     * @param stage the stage that the controller will take over (window).
    */
    protected static void setStage(Stage stage) {
        GameConfigController.stage = stage;
    }

    public void exampleClicked() {
        System.out.println("I was clicked");
    }
}