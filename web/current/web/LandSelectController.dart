import 'dart:html';

import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Tile.dart';
import 'MapRenderer.dart';
import 'Player.dart';
import 'InventoryRenderer.dart';

class LandSelectController extends Controller {

    int currentPlayerIndex = 0;

    LandSelectController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Land select");
        print(gameState.currentPlayer);
        stage.children.add(MapRenderer.render(gameState.map));
        for (Element e in stage.querySelectorAll(".tile")) {
            e.onClick.listen((event) => onTileClicked(event, e));//print("clicked"));//
        }
    }

    Tile getMatchingTile(DivElement view) {
        int x = int.parse(view.id.split("-")[0]);
        int y = int.parse(view.id.split("-")[1]);
        return gameState.map.tiles[y][x];
    }

    void onTileClicked(MouseEvent event, DivElement target) {
        DivElement clicked = target;
        if (clicked.classes.contains("BUILDING")) {
            return;
        } else {
            Tile t = getMatchingTile(target);
            if (t.owner == null) {
                Player p = gameState.players[currentPlayerIndex];
                t.owner = p;
                target.style.borderColor = p.color;
                target.style.borderWidth = "2px";
                currentPlayerIndex++;
                if (currentPlayerIndex >= gameState.players.length) {
                    SceneManager.loadScene(GameScene.TURN_START, gameState);
                }
            }
        }
    }
}