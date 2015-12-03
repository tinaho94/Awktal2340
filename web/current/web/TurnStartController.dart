import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Tile.dart';
import 'MapRenderer.dart';
import 'Player.dart';

class TurnStartController extends Controller {

    TurnStartController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Turn Start");
        print(gameState.currentPlayer);
        stage.children.add(MapRenderer.render(gameState.map));
        stage.querySelector("#done").onClick.listen((event) => SceneManager.loadScene(GameScene.WORLD_VIEW, gameState));
    }
}