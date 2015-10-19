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

    /**
    * These variables refer to the tiles that were clicked
    */
    private int rowClicked;
    private int colClicked;
    private TileType typeClicked;
    private ArrayList<Player> players;



    public LandSelectionController() {
        players = gameState.getPlayers();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        loadPlayerData();
        createImageViews();
        registerOnClick();
        passButton.setOnAction (e -> {
            onPassClick();
        });
    }

    /**
     * Creates an ImageView from Tile's path to picture and places the
     * ImageView in Button then add to parent GridPane.
    */
    @FXML
    private void createImageViews() {
        for (Tile t: currMap) {
            String type = t.getType().toString();
            String path = TileType.valueOf(type).getPath();
            Button button = new Button("");
            String imagePath = LandSelectionController.class.getResource(path).toExternalForm();
            button.setStyle("-fx-background-image: url('" + imagePath + "'); " +
            "-fx-background-position: center center; " +
            "-fx-background-size: stretch");
            if (t.isOwned()) {
                button.setStyle("-fx-border-color: " + colorToHexString(t.getOwner().getColor()) + ";" +
                    "-fx-border-width: 5px;" +
                    "-fx-background-image: url('" + imagePath + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-size: stretch;");
            }
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
            if(t.hasMule()) {
                installMule(t, t.getMule());
            }
        }
    }

    // for redraw of mule
    private void installMule(Tile tile, Mule mule) {
        String type = mule.getType().toString();
        String path = MuleType.valueOf(type).getPath();
        String imagePath = WorldViewController.class.getResource(path).toExternalForm();
        Image image = new Image(imagePath);
        ImageView muleImage = new ImageView(image);
        muleImage.setFitWidth(50);
        muleImage.setFitHeight(50);
        gridpane.add(muleImage,tile.getX(), tile.getY());
    }


    /**
     * Checks for Tile Clicks and changes border color
     * Right now, I only changed to coral but later on
     * change to the current player
    */
    @FXML
    private void registerOnClick() {
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction (e -> {
                    rowClicked = gridpane.getRowIndex(newNode);
                    colClicked = gridpane.getColumnIndex(newNode);
                    onTileClicked(rowClicked, colClicked, newNode);
                });
            }
        }

    }

    private String colorToHexString(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255));
    }



    private void onTileClicked(int row, int col, Node tileView) {
        Player currentPlayer = gameState.getCurrentPlayer();

        Tile tile = gameState.getMap().getTile(col, row);
        if(tile.isOwned()) {
            System.out.println("already owned");
            return;
        }
        if (tile.getType().equals(TileType.BUILDING)) {
            System.out.println("cannot buy the town");
            return;
        }
        if(gameState.getRound() > 2) {
            if(currentPlayer.getResource(Resource.MONEY) < 300){
                System.out.println("You aint got no $$$");
                return;
            } else {
                currentPlayer.takeResource(Resource.MONEY, 300);
            }
        }
        tile.setOwner(currentPlayer);
        currentPlayer.addTile(tile);
        TileType typeClicked = TileType.valueOf(tileView.getId());
        String path = TileType.valueOf(typeClicked.toString()).getPath();
        String imagePath = LandSelectionController.class.getResource(path).toExternalForm();

        tileView.setStyle("-fx-border-color: " + colorToHexString(currentPlayer.getColor()) + ";" +
        "-fx-border-width: 5px;" +
        "-fx-background-image: url('" + imagePath + "'); " +
        "-fx-background-position: center center; " +
        "-fx-background-size: stretch;");

        gameState.endPlayerTurn();

        if(gameState.isRoundOver()) {
            gameState.resetRound();
            SceneManager.loadScene(GameScene.START_TURN);
            // TurnManager.getInstance().beginPlayerTurns();
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

    @FXML
    public void onPassClick() {
        gameState.endPlayerTurn();
        numPasses++;
        if (numPasses == players.size()) {
            gameState.setPropertySelectionEnabled(false);
        }
        if(gameState.isRoundOver()) {
            gameState.resetRound();
            SceneManager.loadScene(GameScene.START_ROUND);
        }
        loadPlayerData();
    }
}