package awktal.mule;

import java.util.Random;

public class MapGenerator {
	private static int[][] traditionalMapTiles = {
		{1,1,2,1,0,1,4,1,1},
		{1,2,1,1,0,1,1,1,4},
		{4,1,1,1,5,1,1,1,2},
		{1,2,1,1,0,1,3,1,1},
		{1,1,3,1,0,1,1,1,3}
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
				tiles[x][y] = new Tile(x, y, tileTypes[rand.nextInt(tileTypes.length - 1)]);
			}
		}
		tiles[4][2] = new Tile(4, 2, TileType.BUILDING);
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