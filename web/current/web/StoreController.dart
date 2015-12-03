import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'MapRenderer.dart';
import 'Player.dart';

class StoreController extends Controller {

    StoreController(DivElement stage, GameState gameState) : super(stage, gameState) {
        stage.querySelector("#exit").onClick.listen((event) => SceneManager.loadScene(GameScene.TOWN_VIEW, gameState));
    }
}