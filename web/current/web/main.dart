import 'dart:html';

import 'GameState.dart';
import 'GameScene.dart';
import 'SceneManager.dart';
import 'Player.dart';
import 'MapGenerator.dart';
import 'TurnManager.dart';

GameState gameState = new GameState(2, MapGenerator.generateMap());//null;

void main() {
    gameState.players = [new Player("One", "#abcdef"), new Player("Two", "#123456")];
    TurnManager.setup(gameState);
    SceneManager.loadScene(GameScene.ROUND_START, gameState);
    // SceneManager.loadScene(GameScene.GAME_CONFIG, gameState);
}