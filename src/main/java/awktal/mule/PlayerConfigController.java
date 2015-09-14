package awktal.mule;

import awktal.mule.Race;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

/**
 * PlayerConfigController controls the player configuration screen.
 * This will be automatically created when the fxml is loaded.
*/
public class PlayerConfigController {

    @FXML
    private TextField nameInput;

    @FXML
    private ColorPicker colorInput;

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
    }

    /**
     * Handles when the user selects that they are done configuring the player.
     * This is registered as a handler in the FXML.
    */
    public void selectionFinished() {
        GameState state = GameState.getInstance();
        try {
            validatePlayerConfig();
            if (state.getMaxPlayers() == state.getNumPlayers()) {
                SceneManager.loadScene(GameScene.LAND_SELECTION);
                return;
            } else {
                SceneManager.loadScene(GameScene.PLAYER_CONFIG);
                return;
            }
        } catch(Exception e) {
            // TODO(hvpeteet): replace this with creating an error message and setting a class = error.
            System.out.println(e.toString());
            nameInput.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Validated the current configuration options.
     * @throws GameStateConfigException if the configuration is invalid.
     * TODO(hvpeteet): make this return useful error messages.
    */
    private void validatePlayerConfig() throws GameStateConfigException {
        GameState.getInstance().addPlayer(new Player(nameInput.getCharacters().toString(), colorInput.getValue(), Race.HUMAN));
        System.out.println(GameState.getInstance().getPlayers());
    }
}