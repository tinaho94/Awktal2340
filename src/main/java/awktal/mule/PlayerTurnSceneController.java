package awktal.mule;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


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

    @FXML
    protected Label energyLabel;

    @FXML
    protected Label oreLabel;

    @FXML
    protected Label roundLabel;

    public PlayerTurnSceneController() {
        TurnManager.getInstance().setCurrentScene(this);
    }

    protected void loadPlayerData() {
        Player currentPlayer = gameState.getCurrentPlayer();
        playerLabel.setText(currentPlayer.getName());
        scoreLabel.setText(String.valueOf(currentPlayer.getScore()));
        timeLabel.setText(String.valueOf(TurnManager.getInstance().getCurrentTurnTime()));
        roundLabel.setText(String.valueOf(gameState.getRound()));
        moneyLabel.setText(String.valueOf(currentPlayer.getResource(Resource.MONEY)));
        foodLabel.setText(String.valueOf(currentPlayer.getResource(Resource.FOOD)));
        energyLabel.setText(String.valueOf(currentPlayer.getResource(Resource.ENERGY)));
        oreLabel.setText(String.valueOf(currentPlayer.getResource(Resource.ORE)));
    }

    public void updateTurnTimer(int currentTurnTime) {
        timeLabel.setText(String.valueOf(currentTurnTime));
    }

}