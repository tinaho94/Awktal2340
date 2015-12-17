import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Player.dart';
import 'MapGenerator.dart';

class GameOverController extends Controller {

    GameOverController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Game over");
    }
}