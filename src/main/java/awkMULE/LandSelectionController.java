package awkMULE;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class LandSelectionController {

    private static Stage stage;

    public LandSelectionController() {
        System.out.println("I am constructed");
    }

    @FXML
    private void initialize() {
        System.out.println("Land select screen is shown.");
    }

    public static void setStage(Stage stage) {
        LandSelectionController.stage = stage;
    }
}