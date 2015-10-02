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
		this.inventory = new Inventory(race.getStartingMoney(), 5, 5, 5);
		this.tiles = new ArrayList<Tile>();
	}

	public int compareTo(Player player) {
		return (this.getScore() - player.getScore());
	} 

	public int getScore() {
		int score = 0;
		score += tiles.size() * 500;
		score += inventory.getMoney();
		score += inventory.getFood() * 30;
		score += inventory.getEnergy() * 25;
		score += inventory.getOre() * 50;
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
	public Tile[] getTiles() {
		return this.tiles.toArray(new Tile[tiles.size()]);
	}
}