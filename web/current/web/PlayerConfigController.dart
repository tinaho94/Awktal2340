import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Player.dart';

class PlayerConfigController extends Controller {

    PlayerConfigController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Created new PlayerConfigController");
        stage.querySelector("#done").onClick.listen((event) => attemptAddPlayer());
    }

    void attemptAddPlayer() {
        print("attempt");
        Player p = new Player(querySelector("#name").value);
        if (isValidPlayer(p)) {
            print("added player ${p.name}");
            gameState.players.add(p);
            querySelector("#name").style.borderColor = "";
            querySelector("#name").value = "";
        } else {
            querySelector("#name").style.borderColor = "Red";
        }
        if (gameState.num_players == gameState.players.length) {
            done();
        }
    }

    bool isValidPlayer(Player newPlayer) {
        for (Player p in gameState.players) {
            if (p.name == newPlayer.name) {
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
        // TODO(henry): link to the next scene (need to implement round start).
        // SceneManager.loadScene(GameScene.ROUND_START, gameState);
    }
}