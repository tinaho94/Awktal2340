import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'MapRenderer.dart';
import 'Player.dart';

class TownViewController extends Controller {

    TownViewController(DivElement stage, GameState gameState) : super(stage, gameState) {
        stage.querySelector("#world").onClick.listen((event) => SceneManager.loadScene(GameScene.WORLD_VIEW, gameState));
        stage.querySelector("#pub").onClick.listen((event) => SceneManager.loadScene(GameScene.PUB, gameState));
        stage.querySelector("#store").onClick.listen((event) => SceneManager.loadScene(GameScene.STORE, gameState));
    }
}