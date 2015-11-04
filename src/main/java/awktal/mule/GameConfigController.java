package awktal.mule;



import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        GameStateFactory fact = GameStateFactory.getInstance();
        fact.setNumPlayers((int) numPlayers.getValue());
        String mapTypeStr = ((ToggleButton)mapType.getSelectedToggle()).getId();
        Map map = MapGenerator.generateMap(MapType.valueOf(mapTypeStr));
        fact.setMap(map);
        System.out.println(fact.getMap() == null);
        System.out.println(GameStateFactory.getInstance().getMap() == null);
        SceneManager.loadScene(GameScene.PLAYER_CONFIG);
    }

    /**
     * Loads the game from a file named "save.json".
    */
    @FXML
    public void loadFromFile() {
        try {
            File file = new File("save.json");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            try {
                fis.read(data);
            } catch (IOException e) {
                fis.close();
                // TODO: alert the user here.
                System.out.println("no file to load from.");
                return;
            }
            fis.close();
            String json = new String(data, "UTF-8");

            GameState state = GameStateFactory.getInstance().fromJsonString(json);
            SceneController.setGameState(state);

            TurnManager instance = TurnManager.getInstance();
            instance.setGameState(state);

            System.out.println("Loaded game");
            SceneManager.loadScene(GameScene.START_ROUND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Continue on with player input;
        }
    }
}