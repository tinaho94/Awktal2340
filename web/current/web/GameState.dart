import 'Player.dart';
import 'GameMap.dart';

class GameState {
    List<Player> players = [];
    int num_players = 0;
    int round = 0;
    GameMap map;

    GameState(this.num_players, this.map);
}