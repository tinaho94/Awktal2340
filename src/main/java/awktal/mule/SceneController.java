package awktal.mule;

/**
 * A SceneController allows controllers to be bound to their GameState.
 * Any class that inherits from SceneController can reference the current game
 * state by using the variable gameState.
*/
public abstract class SceneController {
    protected static GameState gameState;

    /**
     * Sets the game state for all SceneController objects.
     * This should only be used at game creation (or if you can load a game).
     * @param gameState the game state that is to be used for all scenes.
    */
    protected static void setGameState(GameState gameState) {
        SceneController.gameState = gameState;
    }
}