import 'dart:html';

import 'GameState.dart';
import 'PlayerTurnSceneController.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Tile.dart';
import 'MapRenderer.dart';
import 'Player.dart';
import 'InventoryRenderer.dart';

class WorldViewController extends PlayerTurnSceneController {

    WorldViewController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Turn Start");
        print(gameState.currentPlayer);
        stage.children.add(MapRenderer.render(gameState.map));
        for (Element e in stage.querySelectorAll(".tile")) {
            e.onClick.listen((event) => onTileClicked(event, e));//print("clicked"));//
        }
    }

    Tile getMatchingTile(DivElement view) {
        int y = int.parse(view.id.split("-")[0]);
        int x = int.parse(view.id.split("-")[1]);
        return gameState.map.tiles[y][x];
    }

    void onTileClicked(MouseEvent event, DivElement target) {
        DivElement clicked = target;
        if (clicked.classes.contains("BUILDING")) {
            SceneManager.loadScene(GameScene.TOWN_VIEW, gameState);
        }
        // else if (gameState.currentPlayer.mule != null) {
        //     Tile t = getMatchingTile(clicked);
        //     if (t.owner == gameState.currentPlayer) {
        //         t.mule = gameState.currentPlayer.mule;
        //         gameState.currentPlayer.mule = null;
        //         t.mule.tile = t;
        //     }
        // } else {
        //     gameState.currentPlayer.mule = null;
        //     print("killed mule");
        // }
    }
}