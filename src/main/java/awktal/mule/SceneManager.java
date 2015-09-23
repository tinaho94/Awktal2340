package awktal.mule;

import awktal.mule.GameScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage stage;
    private static GameState state;

    /**
     * Initializes the scene manager with the stage that it will control.
     * @param stage the stage that the scene manager will show screens on.
     * @param state the game state to use.
    */
    public static void initSceneManager(Stage stage, GameState state) {
        SceneManager.stage = stage;
        SceneManager.state = state;
        SceneController.setGameState(SceneManager.state);
    }

    /**
     * Loads a new scene onto the stage.
     * @param gameScene the gameScene to be loaded.
    */
    public static void loadScene(GameScene gameScene) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(gameScene.getFXML()));
            Parent screen = loader.load();
            SceneController controller = (SceneController) loader.getController();
            Scene scene = new Scene(screen, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load scene " + gameScene.toString() + ". Check if file " + gameScene.getFXML() + "exists. \n" + " error: " + e.toString());
        }
    }
}