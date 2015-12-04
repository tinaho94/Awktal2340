import 'dart:async';

import 'GameState.dart';
import 'SceneManager.dart';
import 'GameScene.dart';

class TurnManager {
    static GameState gameState;
    static int remainingTime = 0;
    const static UPDATE_TIME = const Duration(seconds: 1);
    static Timer timer;
    static List<Function> updateFuncs;

    static void setup(GameState s) {
        gameState = s;
    }

    static void endTurn() {
        timer.cancel();
        timer = null;
        gameState.currentPlayerIndex++;
        if (gameState.currentPlayerIndex >= gameState.players.length) {
            gameState.currentPlayerIndex = 0;
            if (gameState.round > 11) {
                SceneManager.loadScene(GameScene.GAME_OVER, gameState);
                return;
            }
            SceneManager.loadScene(GameScene.ROUND_START, gameState);
        } else {
            SceneManager.loadScene(GameScene.TURN_START, gameState);
        }
    }

    static void startTurnTimer(int seconds, List<Function> callbacks) {
        remainingTime = seconds;
        updateFuncs = callbacks;
        timer = new Timer(UPDATE_TIME, updateRemainingTime);
    }

    static void pause() {
        timer.cancel();
    }

    static void unpause() {
        timer = new Timer(UPDATE_TIME, updateRemainingTime);
    }

    static void updateRemainingTime() {
        remainingTime--;
        print("Time : $remainingTime");
        for (Function f in updateFuncs) {
            f();
        }
        if (remainingTime == 0) {
            updateFuncs = [];
            TurnManager.endTurn();
        } else {
            timer = new Timer(UPDATE_TIME, updateRemainingTime);
        }
    }


}