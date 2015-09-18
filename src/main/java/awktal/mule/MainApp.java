package awktal.mule;

import awktal.mule.GameConfigController;
import awktal.mule.GameScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MULE");
        stage.setWidth(600);
        stage.setHeight(600);

        SceneManager.initSceneManager(stage, new GameState());
        SceneManager.loadScene(GameScene.GAME_CONFIG);
        stage.show();
    }
}