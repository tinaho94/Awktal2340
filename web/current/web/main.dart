import 'dart:html';

import 'GameState.dart';
import 'GameScene.dart';
import 'SceneManager.dart';
import 'Player.dart';

GameState gameState = null;

void main() {
    SceneManager.loadScene(GameScene.GAME_CONFIG, gameState);
}