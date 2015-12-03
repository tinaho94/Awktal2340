import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'TurnManager.dart';
import 'GameScene.dart';
import 'Player.dart';

class PlayerConfigController extends Controller {

    PlayerConfigController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Created new PlayerConfigController");
        stage.querySelector("#done").onClick.listen((event) => attemptAddPlayer());
    }

    void attemptAddPlayer() {
        InputElement nameField = querySelector("#name");
        InputElement colorField = querySelector("#color");
        Player p = new Player(nameField.value, colorField.value);
        if (isValidPlayer(p)) {
            print("added player ${p.name}");
            gameState.players.add(p);
            querySelector("#name").style.borderColor = "";
            nameField.value = "";
        } else {
            querySelector("#name").style.borderColor = "Red";
        }
        if (gameState.num_players == gameState.players.length) {
            done();
        }
    }

    bool isValidPlayer(Player newPlayer) {
        for (Player p in gameState.players) {
            if (p.name == newPlayer.name || p.color == newPlayer.color || newPlayer.name.trim() == "") {
                return false;
            }
        }
        return true;
    }

    void done() {
        print("players: ");
        for (Player p in gameState.players) {
            print(p.name);
        }
        TurnManager.setup(gameState);
        SceneManager.loadScene(GameScene.ROUND_START, gameState);
    }
}