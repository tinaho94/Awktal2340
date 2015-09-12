package awkMULE;

import java.util.ArrayList;

public class GameState {

	private static GameState instance;
	private int numPlayers;

	private ArrayList<Player> players;

	private int currentPlayerIndex;

	public GameState(int numPlayers) {
		players = new ArrayList<>();
		this.numPlayers = numPlayers;
		currentPlayerIndex = 0;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public void addPlayer(Player player) throws GameStateConfigException {
		for (Player p: players) {
			if (player.getName().equals(p.getName())) {
				throw new GameStateConfigException("duplicate name");
			}
		}
		players.add(player);
	}

	public static GameState getInstance() {
		if (GameState.instance == null) {
			GameState.instance = new GameState(0);
		}
		return GameState.instance;
	}
}