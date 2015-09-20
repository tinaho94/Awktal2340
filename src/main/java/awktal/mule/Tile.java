package awktal.mule;

public class Tile {
	
	private TileType type;

	public Tile (int x, int y, TileType type) {
		this.type = type;
		//do nothing for now
	}

	public TileType getType() {
		return this.type;
	}

	
}