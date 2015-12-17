package awktal.mule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;


public class FxMapRenderer {

    public static void renderMap(GridPane gridpane, Map map) {
        renderTiles(gridpane, map);
        renderMules(gridpane, map);
    }

    /**
     * Creates an ImageView from Tile's path to picture and places the
     * ImageView in Button then add to parent GridPane.
    */
    private static void renderTiles(GridPane gridpane, Map map) {
        for (Tile t: map) {
            renderTile(gridpane, t);
        }
    }

    /**
     * Renders a tile.
     * @param gridpane the grid pane that should hold the tile.
     * @param tile the tile to render.
    */
    public static void renderTile(GridPane gridpane, Tile tile) {
        String type = tile.getType().toString();
        String path = TileType.valueOf(type).getPath();
        Button button = new Button("");
        String imagePath = FxMapRenderer.class.getResource(path).toExternalForm();
        button.setStyle("-fx-background-image: url('" + imagePath + "'); "
            + "-fx-background-position: center center; "
            + "-fx-background-size: stretch");
        if (tile.isOwned()) {
            button.setStyle("-fx-border-color: " + colorToHexString(tile.getOwner().getColor())
                + ";"
                + "-fx-border-width: 5px;"
                + "-fx-background-image: url('" + imagePath + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size: stretch;");
        }
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setId(type);
        gridpane.add(button, tile.getX(), tile.getY(), 1, 1);
    }

    // for redraw of mule
    private static void renderMules(GridPane gridpane, Map map) {
        for (Tile t: map) {
            if (t.hasMule()) {
                Mule mule = t.getMule();
                String type = mule.getType().toString();
                String path = MuleType.valueOf(type).getPath();
                String imagePath = WorldViewController.class.getResource(path).toExternalForm();
                Image image = new Image(imagePath);
                ImageView muleImage = new ImageView(image);
                muleImage.setFitWidth(50);
                muleImage.setFitHeight(50);
                gridpane.add(muleImage,t.getX(), t.getY());
            }
        }
    }

    private static String colorToHexString(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255),
            (int)(color.getGreen() * 255), (int)(color.getBlue() * 255));
    }
}