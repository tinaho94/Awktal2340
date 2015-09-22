package awktal.mule;

public abstract class SceneController {
    protected static GameState gameState;

    protected void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}