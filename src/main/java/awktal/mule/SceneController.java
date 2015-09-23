package awktal.mule;

public abstract class SceneController {
    protected static GameState gameState;

    protected static void setGameState(GameState gameState) {
        SceneController.gameState = gameState;
    }
}