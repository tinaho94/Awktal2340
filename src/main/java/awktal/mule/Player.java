package awktal.mule;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {
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
		this.inventory = new Inventory(race.getStartingMoney(), 0, 0, 0);
		this.tiles = new ArrayList<Tile>();
	}
	/**
	 * Gets the name of the player.
	 * @return the name of the player.
	*/
	public String getName() {
		return this.name;
	}
	public Color getColor() {
		return this.color;
	}
	public Race getRace() {
		return this.race;
	}
	public String toString() {
		return "(" + name  + ", " + color + ", " + race + ")";
	}
	public Inventory getInventory() {
		return this.inventory;
	}
	public void addTile(Tile tile) {
		this.tiles.add(tile);
	}
	public Tile[] getTiles() {
		return this.tiles.toArray(new Tile[tiles.size()]);
	}
}