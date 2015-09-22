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

public class LandSelectionController extends SceneController implements Initializable {
    
    @FXML
    private GridPane gridpane;

    private int row;
    private int col;
    private Map currMap;

    public LandSelectionController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //GameState currState = new GameState();
        //currMap = currState.getMap();
        currMap = MapGenerator.generateMap(MapType.TRADITIONAL);
        createImageViews();
        onClick();
    }

    @FXML
    private void createImageViews() {
        for (Tile t: currMap) {
            String type = t.getType().toString();
            String path = TileType.valueOf(type).getPath();
            Image img = new Image(LandSelectionController.class.getResourceAsStream(path));
            ImageView imgView = new ImageView(img);
            Button button = new Button("",imgView);
            GridPane.setHgrow(button, Priority.ALWAYS);
            GridPane.setVgrow(button, Priority.ALWAYS);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setId(type);
            gridpane.add(button, t.getX(), t.getY(), 1, 1);
            imgView.fitWidthProperty().bind(button.widthProperty());
            imgView.fitHeightProperty().bind(button.heightProperty());
        }
    }

    @FXML
    private void onClick() {
        for (Node node: gridpane.getChildren()) {
            if (node instanceof Button) {
                Button newNode = (Button)node;
                newNode.setOnAction (e -> {
                    try {
                        row = gridpane.getRowIndex(newNode);
                    } catch (Exception f) {
                        row = 0;
                    }
                    try {
                        col = gridpane.getColumnIndex(newNode);
                    } catch (Exception g) {
                        col = 0;
                    }
                    System.out.println("row: " + row + "and col: " + col);
                    //getTileType(newNode);
                    //System.out.println("type of tile: " + type);
                       //newNode.setStyle("-fx-background-color: transparent; -fx-border-color: #800080; -fx-border-width: 5px; ");
                });
            }

        }
    }
}