import 'dart:html';

import 'GameState.dart';
import 'GameScene.dart';
import 'SceneManager.dart';
import 'Player.dart';
import 'MapGenerator.dart';

GameState gameState = new GameState(0, MapGenerator.generateMap());//null;

void main() {
    SceneManager.loadScene(GameScene.TURN_START, gameState);
    // SceneManager.loadScene(GameScene.GAME_CONFIG, gameState);
}