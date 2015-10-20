package awktal.mule;

import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Contains all the information pertaining to a player.
*/
public class Player implements Comparable<Player> {
	private String name;
	private Color color;
	private Race race;
	private Inventory inventory;
	private ArrayList<Tile> tiles;
	private Mule mule;

	/**
	 * The constructor for the player class.
	 * @param name the name of the player.
	 * @param color the color of the player.
	 * @param race the race of the player.
	*/
	public Player(String name, Color color, Race race) {
		this.name = name;
		this.color = color;
		this.race = race;
		this.inventory = new Inventory(race.getStartingMoney(), 8, 4, 0);
		this.tiles = new ArrayList<Tile>();
		this.mule = null;
	}

	public int compareTo(Player player) {
		return (this.getScore() - player.getScore());
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof Player)) {
			return false;
		} else if (this == other) {
			return true;
		} else {
			return this.name.equals(((Player) other).name);
		}
	}

	public int hashCode() {
		return this.name.hashCode();
	}

	public int getScore() {
		int score = 0;
		score += tiles.size() * 500;
		score += getResource(Resource.MONEY);
		score += getResource(Resource.FOOD) * 30;
		score += getResource(Resource.ENERGY) * 25;
		score += getResource(Resource.ORE) * 50;
		return score;
	}

	/**
	 * Gets the name of the player.
	 * @return the name of the player.
	*/
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the color of the player.
	 * @return the color of the player.
	*/
	public Color getColor() {
		return this.color;
	}

	/**
	 * Gets the race of the player.
	 * @return the race of the player.
	*/
	public Race getRace() {
		return this.race;
	}

	/**
	 * Gets a string representation of a player.
	 * @return a string representation of a player.
	*/
	public String toString() {
		return "(" + name  + ", " + color + ", " + race + ")";
	}

	/**
	 * Gets the players inventory.
	 * @return the players inventory.
	*/
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * Gives a tile to a player.
	 * @param tile the tile to give to the player.
	*/
	public void addTile(Tile tile) {
		this.tiles.add(tile);
	}

	/**
	 * Gets the tiles owned by the player.
	 * @return the tiles owned by the player.
	*/
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public Mule takeMule() {
		Mule m = this.mule;
		this.mule = null;
		return m;
	}

	public Mule getMule() {
		return this.mule;
	}

	public void giveMule(Mule mule) {
		this.mule = mule;
	}

	public boolean hasMule() {
		return this.mule != null;
	}

	public int getResource(Resource r) {
		return inventory.getResource(r);
	}

	public void giveResource(Resource r, int quantity) {
		inventory.giveResource(r, quantity);
	}

	public void giveResources(Inventory i) {
		this.inventory.giveResources(i);
	}

	public void takeResource(Resource r, int quantity) {
		inventory.takeResource(r, quantity);
	}
}