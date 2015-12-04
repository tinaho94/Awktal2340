import 'dart:html';

import 'GameState.dart';
import 'GameScene.dart';
import 'SceneManager.dart';
import 'Player.dart';
import 'MapGenerator.dart';
import 'TurnManager.dart';
import 'ResourceType.dart';

GameState gameState = null;//new GameState(2, MapGenerator.generateMap());//null;

void main() {
    // gameState.players = [new Player("One", "red"), new Player("Two", "green")];
    // TurnManager.setup(gameState);
    // SceneManager.loadScene(GameScene.ROUND_START, gameState);
    SceneManager.loadScene(GameScene.GAME_CONFIG, gameState);
}