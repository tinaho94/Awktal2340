package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

public class TownController extends SceneController {

    @FXML
    Button return_button;
    @FXML
    AnchorPane dat_pain;

    @FXML
    private void initialize() {
        Image img = new Image(TownController.class.getResourceAsStream("map_pictures/pallet_town.jpg"));
        dat_pain.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }


    public void helloWorld() {
        System.out.println("yayyy is workinnnggg");
    }




}