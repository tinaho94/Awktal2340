package awkMULE;

import javafx.application.Application;
import awkMULE.GameConfigController;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
 
public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent screen = loader.load(getClass().getResource("game_config.fxml"));
        GameConfigController.setStage(stage);
        Scene scene = new Scene(screen);
        stage.setTitle("MULE");
        stage.setScene(scene);
        stage.show();
    }
}