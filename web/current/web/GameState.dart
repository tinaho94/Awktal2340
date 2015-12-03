import 'Player.dart';
import 'GameMap.dart';

class GameState {
    List<Player> players = [];
    int num_players = 0;
    int round = 0;
    GameMap map;
    int currentPlayerIndex = 0;

    GameState(this.num_players, this.map);

    Player get currentPlayer {
        if (currentPlayerIndex >= players.length) {
            print("player index out of bounds");
            return null;
        }
        return players[currentPlayerIndex];
    }
}