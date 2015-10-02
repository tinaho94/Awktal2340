package awktal.mule;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.Priority;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.util.ArrayList;



public abstract class PlayerTurnSceneController extends SceneController {

    @FXML
    protected Label playerLabel;

    @FXML
    protected Label scoreLabel;

    @FXML
    protected Label timeLabel;

    @FXML
    protected Label moneyLabel;

    @FXML
    protected Label foodLabel;

    protected void loadPlayerData() {
        Player currentPlayer = TurnManager.getInstance().getCurrentPlayer();
        playerLabel.setText(currentPlayer.getName());
        scoreLabel.setText(String.valueOf(currentPlayer.getScore()));
        timeLabel.setText(String.valueOf(TurnManager.getInstance().getCurrentTurnTime()));
        moneyLabel.setText(String.valueOf(currentPlayer.getInventory().getMoney()));
        foodLabel.setText(String.valueOf(currentPlayer.getInventory().getFood()));

    }

    public void updateTurnTimer(int currentTurnTime) {
        timeLabel.setText(String.valueOf(currentTurnTime));
    }

}