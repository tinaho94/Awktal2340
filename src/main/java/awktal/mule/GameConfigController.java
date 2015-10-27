package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

/**
 * GameConfigController controls the game configuration screen.
*/
public class GameConfigController extends SceneController {

    @FXML
    private Slider numPlayers;

    @FXML
    private ToggleGroup mapType;

    /**
     * Constructor for a GameConfigController.
    */
    public GameConfigController() {
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
        String mapTypeStr = ((ToggleButton)mapType.getSelectedToggle()).getId();
        Map map = MapGenerator.generateMap(MapType.valueOf(mapTypeStr));
        gameState.setMap(map);
        SceneManager.loadScene(GameScene.PLAYER_CONFIG);
    }

    /**
     * Loads the game from a file named "save.json".
    */
    @FXML
    public void loadFromFile() {
        try {
            SceneController.setGameState(GameState.fromSavedGame("save.json"));
            System.out.println("Loaded game");
            SceneManager.loadScene(GameScene.START_ROUND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Continue on with player input;
        }
    }
}