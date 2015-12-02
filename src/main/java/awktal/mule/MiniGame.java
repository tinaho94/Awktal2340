package awktal.mule;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
//import java.util.*;

public class MiniGame extends SceneController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView ship1;
    @FXML
    private ImageView ship2;
    @FXML
    private ImageView ship3;
    @FXML
    private ImageView earth;
    @FXML
    private ImageView playerShip;
    //@FXML
    //private I lowerPane;
    @FXML
    private ImageView space;

    // Current column that the player is in.
    private double playerCol;
    private double playerRow;
    private Button player;
    private static final int PLAYER_ROW  = 4;
    private static final int PLAYER_COL  = 4;
    private static final int LEFT_BOUNDRY = 1;
    private static final int RIGHT_BOUNDRY = 1;
    private static final int UP_BOUNDRY = 1;
    private static final int DOWN_BOUNDRY = 1;

    //enemies
    private static final int ENEMY_NUM = 3;
    private ImageView[] enemyShips = {null, null, null};// = {ship1, ship2, ship3};
    
    //earth position
    private static final int EARTH_ROW  = 0;
    private static final int EARTH_COL = 0;

    @FXML
    private void initialize() {
        if (ship1 == null){
            System.out.println("ship1 is null");
        } 
        enemyShips[0] = ship1;
        enemyShips[1] = ship2;
        enemyShips[2] = ship3;
        intitializeEnemy();
        initializePlayer();
        initializeEarth();
        setEnemyLocation();
        initKeyListeners();
    }

    private void initializePlayer() {
        playerShip.setX(5);
        playerShip.setY(5);
    }

    private void initializeEarth() {
        earth.setX(EARTH_ROW);
        earth.setY(EARTH_COL);
    }

    private void intitializeEnemy() {
        for (int i = 0; i < ENEMY_NUM; i++) {
            enemyShips[i].setX(i + 10);
            enemyShips[i].setY(i + 10);            
        }
    }

    private void movePlayerRight() {
        if (playerCol == RIGHT_BOUNDRY) {
            return;
        }
        playerCol += 1;
        playerShip.setY(playerCol);
    }

    private void movePlayerLeft() {
        if (playerCol == LEFT_BOUNDRY) {
            return;
        }
        playerCol -= 1;
        playerShip.setY(playerCol);
    }

    private void movePlayerUp() {
        if (playerRow == UP_BOUNDRY) {
            return;
        }
        playerRow -= 1;
        playerShip.setX(playerRow);
    }

    private void movePlayerDown() {
        if (playerRow == DOWN_BOUNDRY) {
            return;
        }
        playerRow -= 1;
        playerShip.setX(playerRow);
    }

    private void initKeyListeners() {
        mainPane.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case UP:
                        movePlayerUp();
                        attemptEntrance();
                        intercepted();
                        break;
                    case DOWN:
                        movePlayerDown();
                        attemptEntrance();
                        intercepted();
                        break;
                    case RIGHT:
                        movePlayerRight();
                        attemptEntrance();
                        intercepted();
                        break;
                    case LEFT:
                        movePlayerLeft();
                        attemptEntrance();
                        intercepted();
                        break;
                    default: break;
                }
            }
        );
    }

    private void attemptEntrance() {
        if (playerCol == EARTH_COL && playerRow == EARTH_ROW) {
            System.out.println("You have made it to earth! :)");
        }
    }

    private void intercepted() {
        //get collision
        for (int i = 0; i < ENEMY_NUM; i++) {
            if (playerRow == enemyShips[i].getX() && playerCol == enemyShips[i].getY()) {
                System.out.println("You have been cought. :o");
            }
        }
    }

    private void setEnemyLocation() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 17; col++) {
                for (int i = 0; i < ENEMY_NUM; i++) {
                    enemyShips[i].setY(row);
                    enemyShips[i].setX(col);
                } 
            }
        }
    }

    public void onReturnButtonClick() {
        SceneManager.loadScene(GameScene.WORLD_VIEW);
    }

}