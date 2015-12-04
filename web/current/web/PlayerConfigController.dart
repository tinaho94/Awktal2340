import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'TurnManager.dart';
import 'GameScene.dart';
import 'Player.dart';
import 'Race.dart';

class PlayerConfigController extends Controller {

    Race race;

    PlayerConfigController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Created new PlayerConfigController");
        stage.querySelector("#done").onClick.listen((event) => attemptAddPlayer());
        setupRaceSelection();
    }

    void setupRaceSelection() {
        Element form = stage.querySelector("#race");
        for(Race r in Race.values) {

            LabelElement label = new LabelElement();
            label.text = "${r.toString().substring(r.toString().indexOf('.')+1).toLowerCase()}";
            label.htmlFor = r.toString();
            form.children.add(label);

            RadioButtonInputElement choice = new RadioButtonInputElement();
            choice.name = "race";
            choice.value = r.toString();
            choice.onClick.listen((event) => race = r);
            form.children.add(choice);
        }
    }

    void attemptAddPlayer() {
        InputElement nameField = querySelector("#name");
        InputElement colorField = querySelector("#color");
        Player p = new Player(nameField.value, colorField.value, race);
        if (isValidPlayer(p)) {
            print("added player $p");
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
        if (newPlayer.race == null) {
            return false;
        }
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