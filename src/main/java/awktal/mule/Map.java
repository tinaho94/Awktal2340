package awktal.mule;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Iterator;

public class Map implements Iterable<Tile> {

	private Tile[][] tiles;

	@SuppressFBWarnings(value="EI2")
	public Map(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public Tile getTile(int x, int y) {
		return this.tiles[x][y];
	}

	public Iterator<Tile> iterator() {
		return new MapIterator(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < tiles[0].length; y++) {
			for (int x = 0; x < tiles.length; x++) {
				result.append(tiles[x][y].getType().ordinal() + ", ");
			}
			result.append("\n");
		}
		return result.toString();
	}
}