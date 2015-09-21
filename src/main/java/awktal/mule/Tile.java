package awktal.mule;

public class Tile {
	private int x, y;
	private Player owner; 
	
	private TileType type;

	public Tile (int x, int y, TileType type) {
		this.type = type;
		//do nothing for now
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
}