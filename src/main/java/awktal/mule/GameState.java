package awktal.mule;

import java.util.ArrayList;

/**
 * Represents the state of the game.
*/
public class GameState {

    private int maxPlayers;

    private ArrayList<Player> players;

    private int currentPlayerIndex;

    private Map map;

    /**
     * The constructor for a GameState.
     * This can only be called once as this class should be a singleton.
     * If called again this will throw an exception.
    */
    public GameState() {
        players = new ArrayList<>();
        maxPlayers = 0;
        currentPlayerIndex = 0;
    }

    /**
     * Gets the players that are in the game.
     * If getPlayers.size() is not equal to getNumPlayers() then the GameState has not been fully initialized.
     * @return a list of players that are currently playing in no guaranteed order.
    */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the number of players.
     * If this is not equal to getPlayers().size() then the GameState has not been initialized fully.
     * @return the number of players.
    */
    public int getNumPlayers() {
        return players.size();
    }

    /**
     * Gets the current player.
     * @return the current player (the player whose turn it is)
    */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Sets the limit for the number of players.
     * @param numPlayers the max number of players allowed.
    */
    protected void setMaxPlayers(int numPlayers) {
        maxPlayers = numPlayers;
    }

    /**
     * Gets the max number of players that are allowed to be playing.
     * Usually only set at game config.
     * @return the max number of players for this game.
    */
    protected int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Add a player to the game.
     * Requires that there are not numPlayers already in the game. (AKA just for initialization).
     * @param player the player to add to the game.
     * @throws GameStateConfigException if there is a duplicate name.
    */
    protected void addPlayer(Player player) throws GameStateConfigException {
        if (getNumPlayers() >= getMaxPlayers()) {
            throw new UncheckedGameStateConfigException("tried to add more than the allowed number of players");
        }
        for (Player p: players) {
            if (player.getName().equals(p.getName())) {
                throw new GameStateConfigException("duplicate name");
            }
        }
        players.add(player);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return this.map;
    }
}