import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Player.dart';

class RoundStartController extends Controller {

    RoundStartController(DivElement stage, GameState gameState) : super(stage, gameState) {
        gameState.round++;
        gameState.currentPlayerIndex = 0;
        stage.querySelector("#round_number").text = "Round ${gameState.round}";
        sortPlayersByScore(gameState.players);
        stage.querySelector("#done").onClick.listen((event) => SceneManager.loadScene(GameScene.TURN_START, gameState));
    }

    void sortPlayersByScore(List<Player> players) {
        players.sort((a, b) => a.score() - b.score());
    }
}