package awktal.mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


public class LandSelectionController extends SceneController implements Initializable {

    @FXML
    private GridPane gridpane;

    @FXML
    private Label playerLabel;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label roundLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button passButton;

    private Map currMap;

    private int numPasses;

    private int rowClicked;

    private int colClicked;

    private ArrayList<Player> players;

    Music play = new Music();
    static String openRange = "src/main/resources/awktal/mule/music/Train45Original.mp3";

    public LandSelectionController() {
        players = gameState.getPlayers();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        loadPlayerData();
        FxMapRenderer.renderMap(gridpane, currMap);
        registerOnClick();
        passButton.setOnAction(e -> {
                onPassClick();
            }
        );
        play.musicMp3(openRange);
    }

    /**
     * Checks for Tile Clicks and changes border color.
     * Right now, I only changed to coral but later on
     * change to the current player
    */
    @FXML
    private void registerOnClick() {
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button) node;
                newNode.setOnAction(e -> {
                        rowClicked = gridpane.getRowIndex(newNode);
                        colClicked = gridpane.getColumnIndex(newNode);
                        onTileClicked(rowClicked, colClicked, newNode);
                    }
                );
            }
        }

    }

    private void onTileClicked(int row, int col, Node tileView) {
        Player currentPlayer = gameState.getCurrentPlayer();

        Tile tile = gameState.getMap().getTile(col, row);
        if (tile.isOwned()) {
            System.out.println("already owned");
            return;
        }
        if (tile.getType().equals(TileType.BUILDING)) {
            System.out.println("cannot buy the town");
            return;
        }
        if (gameState.getRound() > 2) {
            if (currentPlayer.getResource(Resource.MONEY) < 300) {
                System.out.println("You aint got no $$$");
                return;
            } else {
                currentPlayer.takeResource(Resource.MONEY, 300);
            }
        }
        tile.setOwner(currentPlayer);
        currentPlayer.addTile(tile);
        FxMapRenderer.renderTile(gridpane, tile);
        gameState.endPlayerTurn();

        if (gameState.isRoundOver()) {
            gameState.resetRound();
            SceneManager.loadScene(GameScene.START_TURN);
        } else {
            loadPlayerData();
        }
    }

    private void loadPlayerData() {
        Player currentPlayer = gameState.getCurrentPlayer();
        playerLabel.setText(currentPlayer.getName());
        moneyLabel.setText(String.valueOf(currentPlayer.getResource(Resource.MONEY)));
        roundLabel.setText(String.valueOf(gameState.getRound()));
        scoreLabel.setText(String.valueOf(currentPlayer.getScore()));
    }

    /**
     * Handles if a player passes on land select.
    */
    @FXML
    public void onPassClick() {
        gameState.endPlayerTurn();
        numPasses++;
        if (numPasses == players.size()) {
            gameState.setPropertySelectionEnabled(false);
        }
        if (gameState.isRoundOver()) {
            gameState.resetRound();
            SceneManager.loadScene(GameScene.START_ROUND);
        }
        loadPlayerData();
    }
}