import 'dart:html';

import 'GameScene.dart';
import 'GameState.dart';
import 'ControllerFactory.dart';

class SceneManager {

    static DivElement shadow_stage = null;

    static void loadScene(GameScene scene, GameState gameState) {
        if (shadow_stage == null) {
            DivElement stage = querySelector("#stage");
            shadow_stage = new DivElement();
            shadow_stage.id = "shadow_stage";
            stage.children.add(shadow_stage);
        }
        var controller = ControllerFactory.newController(scene, shadow_stage, gameState);
    }
}