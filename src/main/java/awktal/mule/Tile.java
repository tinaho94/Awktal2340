package awktal.mule;

public class Tile {

	private int x, y;
<<<<<<< HEAD
	//x is col and y is row
=======
	private Player owner; 	
>>>>>>> 3034f5c45a8c88ae45721652d6755a5ee9819e6f
	private TileType type;

	public Tile (int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public TileType getType() {
		return this.type;
	}

	public Player setOwner(Player owner){
		return this.owner = owner;
	} 
	public Player getOwner() {
		return this.owner;  
	}
	public boolean isOwned() {
		return (owner != null);
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}