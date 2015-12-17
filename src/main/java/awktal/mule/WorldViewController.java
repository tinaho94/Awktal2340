package awktal.mule;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
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


public class WorldViewController extends PlayerTurnSceneController implements Initializable {

    @FXML
    private GridPane gridpane;
    private Label messageLabel;
    private Map currMap;

    /**
    * These variables refer to the tiles that were clicked.
    */
    private int rowClicked;
    private int colClicked;
    private int currentPlayerIndex;
    private ArrayList<Player> players;
    private Player currPlayer;
    private boolean hasMule;
    private ArrayList<Tile> tiles;

    /**
     * Runs after the constructor in order to set everything up for the scene.
    **/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init start");
        currMap = gameState.getMap();
        loadPlayerData();
        FxMapRenderer.renderMap(gridpane, currMap);
        System.out.println("rendered map");
        registerOnClick();
        System.out.println("registered");
        if (gameState.getCurrentPlayer().getMule() == null) {
            System.out.println("No mule");
        } else {
            System.out.println("Player has mule "
                + gameState.getCurrentPlayer().getMule().getType());
        }
    }

    /**
     * Checks for Tile Clicks and changes border color.
     * Right now, I only changed to coral but later on
     * change to the current player.
    */
    private void registerOnClick() {
        currPlayer = gameState.getCurrentPlayer();
        tiles = currPlayer.getTiles();
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction(e -> {
                        rowClicked = gridpane.getRowIndex(newNode);
                        colClicked = gridpane.getColumnIndex(newNode);
                        onTileClicked(rowClicked, colClicked, newNode);
                        if (currPlayer.hasMule()) {
                            Tile tile = currMap.getTile(colClicked, rowClicked);
                            if (tiles.contains(tile) && !tile.hasMule()) {
                                installMule(tile);
                            } else if (tile.hasMule()) {
                                System.out.println("Tile already has mule!");
                                killMule();
                            } else {
                                killMule();
                            }
                        }
                    }
                );
            }
        }
    }

    private void killMule() {
        System.out.println("mule is killed");
        currPlayer.takeMule();
    }

    //initial install of mule
    private void installMule(Tile tile) {
        Mule mule = currPlayer.takeMule();
        tile.setMule(mule);
        String type = mule.getType().toString();
        String path = MuleType.valueOf(type).getPath();
        String imagePath = WorldViewController.class.getResource(path).toExternalForm();
        Image image = new Image(imagePath);
        ImageView muleImage = new ImageView(image);
        muleImage.setFitWidth(50);
        muleImage.setFitHeight(50);
        gridpane.add(muleImage,colClicked, rowClicked);
    }

    private void onTileClicked(int row, int col, Node tileView) {
        Tile tile = gameState.getMap().getTile(col, row);
        if (tile.getType().equals(TileType.BUILDING)) {
            SceneManager.loadScene(GameScene.TOWN);
        }
    }
}