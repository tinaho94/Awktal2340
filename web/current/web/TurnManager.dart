import 'GameState.dart';
import 'SceneManager.dart';
import 'GameScene.dart';

class TurnManager {
    static GameState gameState;

    static void setup(GameState s) {
        gameState = s;
    }

    static void endTurn() {
        if (gameState.currentPlayerIndex >= gameState.players.length) {
            gameState.currentPlayerIndex = 0;
            SceneManager.loadScene(GameScene.ROUND_START, gameState);
        } else {
            SceneManager.loadScene(GameScene.TURN_START, gameState);
        }
    }
}