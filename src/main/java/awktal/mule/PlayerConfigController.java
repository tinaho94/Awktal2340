package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * PlayerConfigController controls the player configuration screen.
 * This will be automatically created when the fxml is loaded.
*/
public class PlayerConfigController extends SceneController {

    @FXML
    private ToggleGroup race;

    @FXML
    private TextField nameInput;

    @FXML
    private ColorPicker colorInput;

    @FXML
    private Pane pane;

    /**
     * Constructor for a PlayerConfigController.
    */
    public PlayerConfigController() {
    }

    /**
     * Will be called after the constructor when the fxml is loaded.
    */
    @FXML
    private void initialize() {
        pane.setStyle("-fx-background-color: #73CEA5");
    }

    /**
     * Handles when the user selects that they are done configuring the player.
     * This is registered as a handler in the FXML.
    */
    public void selectionFinished() {
        GameStateFactory fact = GameStateFactory.getInstance();
        System.out.println(fact.getMap() == null);
        try {
            validatePlayerConfig();
            if (fact.getNumPlayers() == fact.getPlayers().size()) {
                GameState state = fact.createGameState();
                System.out.println(fact.getMap() == null);
                System.out.println(state.getMap() == null);
                SceneController.setGameState(state);
                TurnManager instance = TurnManager.getInstance();
                instance.setGameState(state);
                SceneManager.loadScene(GameScene.START_ROUND);
                return;
            } else {
                SceneManager.loadScene(GameScene.PLAYER_CONFIG);
                return;
            }
        } catch (GameStateConfigException e) {
            // TODO(hvpeteet): replace this with creating a pretty error.
            System.out.println(e.toString());
            nameInput.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Validated the current configuration options.
     * @throws GameStateConfigException if the configuration is invalid.
    */
    private void validatePlayerConfig() throws GameStateConfigException {
        GameStateFactory fact = GameStateFactory.getInstance();
        String raceString = ((ToggleButton)race.getSelectedToggle()).getId();
        Race raceVal = Race.valueOf(raceString);
        fact.addPlayer(new Player(nameInput.getCharacters().toString(),
            colorInput.getValue(), raceVal));
        System.out.println(fact.getPlayers());
    }
}