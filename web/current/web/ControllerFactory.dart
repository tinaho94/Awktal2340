import 'dart:html';
import 'dart:async';

import 'GameConfigController.dart';
import 'PlayerConfigController.dart';
import 'RoundStartController.dart';
import 'TurnStartController.dart';
import 'GameScene.dart';
import 'GameState.dart';
import 'Controller.dart';

class ControllerFactory {
    final static Map<GameScene, Function> constructors = {
        GameScene.GAME_CONFIG: (stage, gameState) => new GameConfigController(stage, gameState),
        GameScene.PLAYER_CONFIG: (stage, gameState) => new PlayerConfigController(stage, gameState),
        GameScene.ROUND_START: (stage, gameState) => new RoundStartController(stage, gameState),
        GameScene.TURN_START: (stage, gameState) => new TurnStartController(stage, gameState),
    };

    static Controller newController(GameScene scene, DivElement stage, GameState gameState) async {
        // Attempt to get cached scene's html by the value of the enum.
        // gets the value of the enum AKA GameScene.GAME_CONFIG --> "GAME_CONFIG"
        String scene_id = scene.toString().substring(scene.toString().indexOf('.')+1);

        // TODO: Caching for faster responses
        // ------------------------------------------
        // for (var child in stage.children) {
        //     child.style.display = "none";
        // }
        // DivElement old = stage.querySelector(scene_id);
        // if (old == null) {
            // await HttpRequest.getString("templates/$scene_id.html").then((resp) {
            //     var elem = new Element.html(resp);
            //     elem.id = scene_id;
            //     old = elem;
            //     stage.append(elem);
            // });
        // } else {
            // old.style.display = "";
        // }
        // ------------------------------------------

        stage.children.clear();
        var elem = null;
        await HttpRequest.getString("templates/$scene_id.html").then((resp) {
            elem = new Element.html(resp);
            elem.id = scene_id;
            stage.append(elem);
        });
        // Now we can create the controller and it will link itself to the html.
        Function constructor = constructors[scene];
        Controller controller = constructor(elem, gameState);
    }
}