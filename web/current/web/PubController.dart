import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'MapRenderer.dart';
import 'TurnManager.dart';
import 'Player.dart';

class PubController extends Controller {

    PubController(DivElement stage, GameState gameState) : super(stage, gameState) {
        stage.querySelector("#gamble").onClick.listen((event) => gamble());
        stage.querySelector("#exit").onClick.listen((event) => SceneManager.loadScene(GameScene.TOWN_VIEW, gameState));
    }

    void gamble() {
        gameState.currentPlayerIndex++;
        TurnManager.endTurn();
    }
}