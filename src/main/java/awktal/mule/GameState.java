package awktal.mule;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the state of the game.
*/
public class GameState {

    private ArrayList<Player> players;

    private int currentPlayerIndex;

    private Map map;

    private int round;

    private boolean propertySelectionEnabled;

    private int maxRounds;

    private Store store;

    /**
     * Constructs a GameState from a list of players and a map.
     * @param players the players.
     * @param map the map.
    */
    public GameState(ArrayList<Player> players, Map map) {
        this.players = players;
        this.map = map;
        this.currentPlayerIndex = 0;
        this.round = 0;
        this.propertySelectionEnabled = true;
        this.maxRounds = 12;
        this.store = new Store();
    }
    /**
     * The constructor for a GameState.
     * By default nothing is valid.
    */
    public GameState() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        round = 0;
        propertySelectionEnabled = true;
        maxRounds = 12;
        store = new Store();
    }

    /**
     * Checks if the game is over.
     * @return if the game is over.
    */
    public boolean isGameOver() {
        return round >= maxRounds && currentPlayerIndex > players.size();
    }

    /**
     * Gets the store.
     * @return the store object for the town's store.
    */
    public Store getStore() {
        return this.store;
    }


    /**
     * Gets the players that are in the game.
     * @return a list of players that are currently playing in no guaranteed order.
    */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Checks if land grants are active.
     * @return if land grants are active.
    */
    public boolean getPropertySelectionEnabled() {
        return propertySelectionEnabled;
    }

    /**
     * Sets if land grants are active.
     * @param enabled if land grants should be enabled.
    */
    public void setPropertySelectionEnabled(Boolean enabled) {
        propertySelectionEnabled = enabled;
    }

    /**
     * Gets the number of players.
     * @return the number of players.
    */
    public int getNumPlayers() {
        return players.size();
    }

    /**
     * Gets the current player.
     * @return the current player (the player whose turn it is)
     * @throws NoNextPlayerException if the round is over (no next player).
    */
    public Player getCurrentPlayer() {
        if (currentPlayerIndex >= players.size()) {
            throw new NoNextPlayerException("Round is over, no next player.");
        }
        return players.get(currentPlayerIndex);
    }

    /**
     * Sets the map for the game state.
     * @param map the map that the game will use for play.
    */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Gets the map that the GameState is currently using for the game.
     * @return the map that the GameState is currently using.
    */
    public Map getMap() {
        return this.map;
    }

    /**
     * Progresses the current player to the next player.
    */
    public void endPlayerTurn() {
        Player player = getCurrentPlayer();
        if (player.hasMule()) {
            player.takeMule();
            System.out.println("Mule killed at end of turn");
        }
        currentPlayerIndex++;
    }

    /**
     * Allows you to check if a round is over.
     * Make sure you call this before you call getCurrentPlayer.
     * @return if all players have taken their turns.
    */
    public boolean isRoundOver() {
        return !(currentPlayerIndex < players.size());
    }


    /**
     * Alows the round count to be reset.
     * NOTE: this does not undo everything done in that round.
    */
    public void resetRound() {
        currentPlayerIndex = 0;
    }

    /**
     * Starts a new round for the game.
    */
    public void newRound() {
        currentPlayerIndex = 0;
        round++;
        if (round > 2) {
            propertySelectionEnabled = false;
        }
    }

    /**
     * Gets the current round.
     * @return the current round number.
    */
    public int getRound() {
        return round;
    }

    /**
     * Sorts the players by score (Lowest first).
    */
    public void recalculatePlayerOrder() {
        Collections.sort(players);
    }

    /**
     * Gets the current player index.
     * @return the turn number within the current round.
    */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}