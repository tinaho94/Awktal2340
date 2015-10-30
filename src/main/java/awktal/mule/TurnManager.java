package awktal.mule;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class TurnManager {

    private static TurnManager instance;

    private static GameState gameState;

    private static final int[] roundFoodRequirements = {3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};

    private Timeline timeline;

    private PlayerTurnSceneController currentScene;

    private int currentTurnTime;

    /**
     * Gets the single instance of turn manager.
     * @return the single instance of the turn manager.
    */
    public static TurnManager getInstance() {
        if (instance == null) {
            instance = new TurnManager();
        }
        return instance;
    }

    public static void setGameState(GameState gameState) {
        TurnManager.getInstance();
        TurnManager.gameState = gameState;
    }

    private TurnManager() {
        this.timeline = new Timeline(
            new KeyFrame(
                Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        onClockTick();
                    }
                }
            ),
            new KeyFrame(
              Duration.seconds(1)
            )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the clock ticking for the current player.
     * Time is calculated based on food.
    */
    public void startCurrentPlayerClock() {
        Player player = gameState.getCurrentPlayer();
        currentTurnTime = calculatePlayerTurnTime(player);
        timeline.play();
    }

    private int calculatePlayerTurnTime(Player player) {
        int foodRequirement = roundFoodRequirements[gameState.getRound() - 1];
        int foodValue = player.getResource(Resource.FOOD);
        int turnTime;
        if (foodValue >= foodRequirement) {
            turnTime = 50;
            //player.takeResource(Resource.FOOD, foodRequirement);
        } else if (foodValue > 0) {
            turnTime = 30;
            //player.takeResource(Resource.FOOD, foodValue);
        } else {
            turnTime = 5;
        }
        return turnTime;
    }

    public void setCurrentScene(PlayerTurnSceneController currentScene) {
        this.currentScene = currentScene;
    }

    private void onClockTick() {
        currentTurnTime--;
        if (currentTurnTime == 0) {
            endPlayerTurn();
        } else {
            if (currentScene != null) {
                currentScene.updateTurnTimer(currentTurnTime);
            }
        }
    }

    /**
     * Ends a player's turn and sets up the next screen.
     * The next screen can be to start another round, another player's turn, or to end the game.
    */
    public void endPlayerTurn() {
        timeline.stop();
        gameState.endPlayerTurn();
        if (gameState.isRoundOver()) {
            SceneManager.loadScene(GameScene.START_ROUND);
        } else {
            SceneManager.loadScene(GameScene.START_TURN);
        }
    }

    public Player getCurrentPlayer() {
        return gameState.getCurrentPlayer();
    }

    public int getCurrentTurnTime() {
        return currentTurnTime;
    }
}