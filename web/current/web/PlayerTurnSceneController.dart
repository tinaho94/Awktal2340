import 'dart:html';

import 'Controller.dart';
import 'GameState.dart';
import 'InventoryRenderer.dart';
import 'TurnManager.dart';

class PlayerTurnSceneController extends Controller {
    DivElement timerView;

    PlayerTurnSceneController(DivElement stage, GameState gameState) : super(stage, gameState) {
        timerView = new DivElement();
        timerView.style.padding = "5px";
        updateTimerView();
        TurnManager.updateFuncs.add(updateTimerView);
        stage.children.insert(0, timerView);
        stage.children.insert(0, InventoryRenderer.render(gameState.currentPlayer.inventory));
        DivElement playerName = new DivElement();
        playerName.text = "Player : ${gameState.currentPlayer.name}";
        playerName.style.padding = "5px";
        stage.children.insert(0, playerName);
    }

    void updateTimerView() {
        timerView.text = "Time: ${TurnManager.remainingTime} seconds";
    }
}