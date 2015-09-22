package awktal.mule;

public class Tile {

	private int x, y;
	//x is col and y is row
	private TileType type;

	public Tile (int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public TileType getType() {
		return this.type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
}