package awktal.mule;

import awktal.mule.GameScene;
import awktal.mule.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.Scene;

/**
 * GameConfigController controls the game configuration screen.
 * This will be automatically created when the fxml is loaded.
*/
public class GameConfigController extends SceneController{

    @FXML
    private Slider numPlayers;

    @FXML
    private ToggleGroup mapType;

    @FXML
    private Toggle random;

    @FXML
    private Toggle traditional;

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
    }

    /**
     * Handles when the user selects that they are done configuring the game.
     * This is registered as a handler in the FXML.
    */
    public void selectionFinished() {
        gameState.setMaxPlayers((int) numPlayers.getValue());
        Map map = null;
        if (mapType.getSelectedToggle() == random) {
            map = MapGenerator.generateMap(MapType.RANDOM);
        } else if (mapType.getSelectedToggle() == traditional) {
            map = MapGenerator.generateMap(MapType.TRADITIONAL);
        } 
        gameState.setMap(map);
        SceneManager.loadScene(GameScene.PLAYER_CONFIG);
    }
}