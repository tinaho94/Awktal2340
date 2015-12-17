import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Player.dart';
import 'ProductionCalculator.dart';

class RoundStartController extends Controller {

    RoundStartController(DivElement stage, GameState gameState) : super(stage, gameState) {
        gameState.round++;
        gameState.currentPlayerIndex = 0;
        stage.querySelector("#round_number").text = "Round ${gameState.round}";
        sortPlayersByScore(gameState.players);
        if (gameState.round > 2) {
            stage.querySelector("#done").onClick.listen((event) => SceneManager.loadScene(GameScene.TURN_START, gameState));
        } else {
            stage.querySelector("#done").onClick.listen((event) => SceneManager.loadScene(GameScene.LAND_SELECT, gameState));
        }
        DivElement productionDisplay = ProductionCalculator.calculateAllProduction(gameState);
        stage.children.add(productionDisplay);
    }

    void sortPlayersByScore(List<Player> players) {
        players.sort((a, b) => a.score() - b.score());
    }
}