package awktal.mule;

import javafx.fxml.FXML;

/**
 * PubController controls the game configuration screen.
*/
public class PubController extends SceneController {

    /**
     * Constructor for a PubController.
    */
    public PubController() {
    }

    /**
     * Will be called after the constructor when the fxml is loaded.
    */
    @FXML
    private void initialize() {
    }

    @FXML
    public void gamble() {
        System.out.println("Gambling");
    }

    @FXML
    public void returnToTown() {
        SceneManager.loadScene(GameScene.TOWN);
    }

}