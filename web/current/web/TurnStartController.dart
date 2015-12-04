import 'dart:html';

import 'InventoryRenderer.dart';
import 'GameState.dart';
import 'Controller.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'Tile.dart';
import 'MapRenderer.dart';
import 'Player.dart';
import 'TurnManager.dart';
import 'ResourceType.dart';

class TurnStartController extends Controller {

    static List<int> roundFoodRequirements = [3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5];

    TurnStartController(DivElement stage, GameState gameState) : super(stage, gameState) {
        print("Turn Start");
        print(gameState.currentPlayer);
        DivElement scoreDisplay = new DivElement();
        scoreDisplay.text = "Score: ${gameState.currentPlayer.score()}";
        stage.children.insert(0, scoreDisplay);
        stage.children.insert(0, InventoryRenderer.render(gameState.currentPlayer.inventory));
        DivElement playerName = new DivElement();
        playerName.text = "Player : ${gameState.currentPlayer.name}";
        playerName.style.padding = "5px";
        stage.children.insert(0, playerName);
        stage.children.add(MapRenderer.render(gameState.map));
        stage.querySelector("#done").onClick.listen((event) => done());
    }

    void done() {
        TurnManager.startTurnTimer(calcTurnTime(gameState.currentPlayer), []);
        SceneManager.loadScene(GameScene.WORLD_VIEW, gameState);
    }

    int calcTurnTime(Player player) {
        int foodRequirement = roundFoodRequirements[gameState.round - 1];
        int foodValue = player.inventory.resources[ResourceType.FOOD];
        int turnTime;
        if (foodValue >= foodRequirement) {
            turnTime = 50;
            //player.takeResource(Resource.FOOD, foodRequirement);
        } else if (foodValue > 0) {
            turnTime = 30;
            //player.takeResource(Resource.FOOD, foodValue);
        } else {
            turnTime = 5;
        }
        return turnTime;
    }
}