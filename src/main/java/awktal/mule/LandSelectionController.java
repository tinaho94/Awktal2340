package awktal.mule;

import javafx.fxml.*;
import javafx.scene.control.Button;
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

    private Map currMap;

    /**
    * These variables refer to the tiles that were clicked
    */
    private int rowClicked;
    private int colClicked;
    private TileType typeClicked;
    private int currentPlayerIndex;
    private ArrayList<Player> players;



    public LandSelectionController() {
        players = gameState.getPlayers();
        currentPlayerIndex = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();        
        createImageViews();
        onClick();
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
            "-fx-background-size: cover");
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
        }
    }

    /**
     * Checks for Tile Clicks and changes border color
     * Right now, I only changed to coral but later on
     * change to the current player
    */
    @FXML
    private void onClick() {
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction (e -> {
                    rowClicked = gridpane.getRowIndex(newNode);
                    colClicked = gridpane.getColumnIndex(newNode);
                    tileClicked(rowClicked, colClicked, newNode);
                });
            }
        }
    }

    
    private String colorToHexString(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255));
    }



    /**
    * Checks if the tile is owned by another player
    * if no one owns it, then the player purchases it, and his $ is deducted
    * @param row of the tile
    * @param col of the tile
    * @param tileView is a node reference to the tile object
    */
    private void tileClicked(int row, int col, Node tileView) {
        Player currentPlayer = players.get(currentPlayerIndex);

        Tile tile = gameState.getMap().getTile(col, row);
        if(tile.isOwned()) {
            System.out.println("already owned");
            return;    
        } 
        if(gameState.getRound() >= 2) {
            if(currentPlayer.getInventory().getMoney() < 300){
                System.out.println("You aint got no $$$");
                currentPlayerIndex++;
                if(currentPlayerIndex >= players.size()) { 
                    SceneManager.loadScene(GameScene.TOWN);
                    return;
                } 
            } else {
                currentPlayer.getInventory().withdrawMoney(300);
            }
        }
        tile.setOwner(currentPlayer);
        currentPlayer.addTile(tile);
        TileType typeClicked = TileType.valueOf(tileView.getId());
        String path = TileType.valueOf(typeClicked.toString()).getPath();
        String imagePath = LandSelectionController.class.getResource(path).toExternalForm();

        tileView.setStyle("-fx-border-color: " + colorToHexString(currentPlayer.getColor()) + ";" +
        "-fx-border-size: 50px;" +
        "-fx-background-image: url('" + imagePath + "'); " +
        "-fx-background-position: center center; " +
        "-fx-background-size: cover;");

        currentPlayerIndex++;
        if(currentPlayerIndex >= players.size()) { 
            //go to world view
            SceneManager.loadScene(GameScene.TOWN);
        } 
    }
}
