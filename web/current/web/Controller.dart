import 'dart:html';

import 'GameState.dart';

class Controller {
    GameState gameState;
    DivElement stage;

    Controller(DivElement this.stage, GameState this.gameState) {
        print("Created new Controller");
        stage.classes.toggle('stage');
    }
}