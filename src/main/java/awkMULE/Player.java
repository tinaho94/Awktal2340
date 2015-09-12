package awkMULE;

import javafx.scene.paint.Color;

public class Player {
	private String name;
	private Color color;
	private String race;
	public Player(String name, Color color, String race) {
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
	public String getRace() {
		return this.race;
	}
	public String toString() {
		return "(" + name  + ", " + color + ", " + race + ")";
	}
}