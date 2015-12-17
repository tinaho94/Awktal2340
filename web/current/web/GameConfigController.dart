import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Player.dart';
import 'MapGenerator.dart';

class GameConfigController extends Controller {

    GameConfigController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Created new GameConfigController");
        stage.querySelector("#done").onClick.listen((event) => done());
    }

    void done() {
        setGameStateSettings();
        SceneManager.loadScene(GameScene.PLAYER_CONFIG, gameState);
    }

    void setGameStateSettings() {
        RangeInputElement player_input = this.stage.querySelector("#players");
        int num_players = int.parse(player_input.value);
        this.gameState = new GameState(num_players, MapGenerator.generateMap());
    }

}