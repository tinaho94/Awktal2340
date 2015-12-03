import 'dart:html';

import 'InventoryRenderer.dart';
import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Tile.dart';
import 'MapRenderer.dart';
import 'Player.dart';
import 'TurnManager.dart';

class TurnStartController extends Controller {

    TurnStartController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Turn Start");
        print(gameState.currentPlayer);
        stage.children.add(InventoryRenderer.render(gameState.currentPlayer.inventory));
        stage.children.add(MapRenderer.render(gameState.map));
        stage.querySelector("#done").onClick.listen((event) => done());
    }

    void done() {
        TurnManager.startTurnTimer(20, []);
        SceneManager.loadScene(GameScene.WORLD_VIEW, gameState);
    }
}