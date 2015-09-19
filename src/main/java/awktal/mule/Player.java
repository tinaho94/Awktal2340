package awktal.mule;

import javafx.scene.paint.Color;
import awktal.mule.Race;

public class Player {
	private String name;
	private Color color;
	private Race race;
	public Player(String name, Color color, Race race) {
		this.name = name;
		this.color = color;
		this.race = race;
	}
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
}