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
import javafx.scene.ImageCursor;


public class WorldViewController extends PlayerTurnSceneController implements Initializable {

    @FXML
    private GridPane gridpane;
    private Label messageLabel;
    private Map currMap;

    /**
    * These variables refer to the tiles that were clicked
    */
    private int rowClicked;
    private int colClicked;
    private TileType typeClicked;
    private int currentPlayerIndex;
    private ArrayList<Player> players;
    private Player currPlayer;
    private boolean hasMule;
    private ArrayList<Tile> tiles;



    public WorldViewController() {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        currMap = gameState.getMap();
        loadPlayerData();
        createImageViews();
        registerOnClick();
        if (gameState.getCurrentPlayer().getMule() == null) {
            System.out.println("No mule");
        } else {
            System.out.println("Player has mule " + gameState.getCurrentPlayer().getMule().getType());
        }
    }

    /**
     * Creates an ImageView from Tile's path to picture and places the
     * ImageView in Button then add to parent GridPane.
    */
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

    private String colorToHexString(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed()*255), (int)(color.getGreen()*255), (int)(color.getBlue()*255));
    }

    /**
     * Checks for Tile Clicks and changes border color
     * Right now, I only changed to coral but later on
     * change to the current player
    */
    private void registerOnClick() {
        currPlayer = TurnManager.getInstance().getCurrentPlayer();
        tiles = currPlayer.getTiles();
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction (e -> {
                    rowClicked = gridpane.getRowIndex(newNode);
                    colClicked = gridpane.getColumnIndex(newNode);
                    onTileClicked(rowClicked, colClicked, newNode);
                    if(currPlayer.hasMule()) {
                        Tile tile = currMap.getTile(colClicked, rowClicked);
                        if(tiles.contains(tile) && !tile.hasMule()) {
                            installMule(tile);
                        } else if(tile.hasMule()) {
                            System.out.println("Tile already has mule!");
                            killMule();
                        } else {
                            killMule();
                        }
                    }
                });
            }
        }
    }

    private void killMule() {
        System.out.println("mule is killed");
        currPlayer.takeMule();
    }

    //initial install of mule
    private void installMule(Tile tile) {
        Mule m = currPlayer.takeMule();
        tile.setMule(m);
        String type = m.getType().toString();
        String path = MuleType.valueOf(type).getPath();
        String imagePath = WorldViewController.class.getResource(path).toExternalForm();
        Image image = new Image(imagePath);
        ImageView muleImage = new ImageView(image);
        muleImage.setFitWidth(50);
        muleImage.setFitHeight(50);
        gridpane.add(muleImage,colClicked, rowClicked);
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

    private void onTileClicked(int row, int col, Node tileView) {
        Tile tile = gameState.getMap().getTile(col, row);
        if (tile.getType().equals(TileType.BUILDING)) {
            SceneManager.loadScene(GameScene.TOWN);
        }
    }



}