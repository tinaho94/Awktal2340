import 'Tile.dart';

class GameMap {
    List<List<Tile>> tiles;

    GameMap(List<List<Tile>> this.tiles);

    List<Tile> asList() {
        List<Tile> llist = [];
        for (List<Tile> l in tiles) {
            for (Tile t in l) {
                llist.add(t);
            }
        }
        return llist;
    }
}