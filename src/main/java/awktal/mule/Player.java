package awktal.mule;

import javafx.scene.paint.Color;
import awktal.mule.Race;

public class Player {
	private String name;
	private Color color;
	private Race race;
	private Inventory inventory;

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
		inventory.depositMoney(100);
		String[] h = inventory.getHistory();
		for (String p: h) {
			System.out.println(p);
		}
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
}