package awktal.mule;

import awktal.mule.GameScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage stage;

    /**
     * Initializes the scene manager with the stage that it will control.
     * @param stage the stage that the scene manager will show screens on.
    */
    public static void initSceneManager(Stage stage) {
        SceneManager.stage = stage;
    }

    /**
     * Loads a new scene onto the stage.
     * @param gameScene the gameScene to be loaded.
    */
    public static void loadScene(GameScene gameScene) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent screen = loader.load(SceneManager.class.getResource(gameScene.getFXML()));
            Scene scene = new Scene(screen, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
        } catch(Exception e) {
            // TODO(hvpeteet): Make better exception.
            throw new RuntimeException("Cannot load file " + gameScene.getFXML() + " error: " + e.toString());
        }
    }
}