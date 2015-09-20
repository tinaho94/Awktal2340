package awktal.mule;

import java.util.Random;

public class MapGenerator {
	private static int[][] traditionalMapTiles = {
		{0,0,2,0,1,0,4,0,0},
		{0,2,0,0,1,0,0,0,4},
		{4,0,0,0,5,0,0,0,2},
		{0,3,0,0,1,0,3,0,0},
		{0,0,3,0,1,0,0,0,3}
	};	
	public static Map generateMap(MapType type) {
		Map map = null;
		if (type == MapType.RANDOM) {
			map = generateRandomMap();
		} else if (type == MapType.TRADITIONAL) {
			map = generateTraditionalMap();
		}
		return map;
	}

	private static Map generateRandomMap() {
		TileType[] tileTypes = TileType.class.getEnumConstants();
		Random rand = new Random(System.currentTimeMillis());
		Tile[][] tiles = new Tile[9][5];
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				tiles[x][y] = new Tile(x, y, tileTypes[rand.nextInt(tileTypes.length)]);
			}
		}
		return new Map(tiles);
	}

	private static Map generateTraditionalMap() {
		TileType[] tileTypes = TileType.class.getEnumConstants();
		Tile[][] tiles = new Tile[9][5];
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				tiles[x][y] = new Tile(x, y, tileTypes[traditionalMapTiles[y][x]]);
			}
		}
		return new Map(tiles);
	}
}