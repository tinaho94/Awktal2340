import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';

class RoundStartController extends Controller {

    RoundStartController(DivElement stage, GameState gameState) : super(stage, gameState) {
        gameState.round++;
        gameState.currentPlayerIndex = 0;
        stage.querySelector("#round_number").text = "Round ${gameState.round}";
        print("going to start round");
        stage.querySelector("#done").onClick.listen((event) => SceneManager.loadScene(GameScene.TURN_START, gameState));
    }
}