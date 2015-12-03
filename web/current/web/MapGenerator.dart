import 'GameMap.dart';
import 'Tile.dart';
import 'TileType.dart';

class MapGenerator {
    static List<List<int>> defaultMap = [
        [1,1,2,1,0,1,4,1,1],
        [1,2,1,1,0,1,1,1,4],
        [4,1,1,1,5,1,1,1,2],
        [1,2,1,1,0,1,3,1,1],
        [1,1,3,1,0,1,1,1,3]
    ];

    static GameMap generateMap() {
        return generateDefaultMap();
    }

    static GameMap generateDefaultMap() {
        print("generating map");
        List<TileType> types = TileType.values;
        List<List<Tile>> datMap = [];
        for (int y = 0; y < defaultMap.length; y++) {
            datMap.add(new List<Tile>(defaultMap[y].length));
            for (int x = 0; x < defaultMap[0].length; x++) {
                datMap[y][x] = new Tile(x, y, types[defaultMap[y][x]]);
            }
        }
        return new GameMap(datMap);
    }
}