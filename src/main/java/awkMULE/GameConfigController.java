package awkMULE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GameConfigController {
    @FXML
    private Button done;

    private static Stage stage;

    public GameConfigController() {
        System.out.println("I am constructed");
    }

    @FXML
    private void initialize() {
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                processGameConfig();
                startPlayerConfig();
            }
        });
        System.out.println("I am initialized");
    }

    private void startPlayerConfig() {
        try {
            Parent screen = FXMLLoader.load(getClass().getResource("player_config.fxml"));
            Scene scene = new Scene(screen);
            stage.setTitle("MULE");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            System.out.println("You have failed");
        }
    }

    private void processGameConfig() {
        System.out.println("I will save game state here");
    }

    public static void setStage(Stage stage) {
        GameConfigController.stage = stage;
    }
}