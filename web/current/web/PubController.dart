import 'dart:html';
import 'dart:math';

import 'GameState.dart';
import 'PlayerTurnSceneController.dart';
import 'SceneManager.dart';
import 'GameScene.dart';
import 'MapRenderer.dart';
import 'TurnManager.dart';
import 'Player.dart';
import 'InventoryRenderer.dart';
import 'ResourceType.dart';

class PubController extends PlayerTurnSceneController {

    static final List<int> roundBonus = [50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200];

    PubController(DivElement stage, GameState gameState) : super(stage, gameState) {
        stage.querySelector("#gamble").onClick.listen((event) => gamble());
        stage.querySelector("#exit").onClick.listen((event) => SceneManager.loadScene(GameScene.TOWN_VIEW, gameState));
    }

    void gamble() {
        gameState.currentPlayer.inventory.resources[ResourceType.MONEY] += calculateGamblingEarnings();
        TurnManager.endTurn();
    }

    int calculateGamblingEarnings() {
        Random rand = new Random();
        int remainingTime = TurnManager.remainingTime;
        int timeBonusMax;
        if (remainingTime >= 37) {
            timeBonusMax = 200;
        } else if (remainingTime >= 25) {
            timeBonusMax = 150;
        } else if (remainingTime >= 12) {
            timeBonusMax = 100;
        } else {
            timeBonusMax = 50;
        }
        int timeBonus = rand.nextInt(timeBonusMax);
        int totalBonus = timeBonus + roundBonus[gameState.round - 1];
        return totalBonus > 250 ? 250 : totalBonus;
    }
}