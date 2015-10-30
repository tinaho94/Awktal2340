package awktal.mule;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the state of the game.
*/
public class GameState {

    private int maxPlayers;

    private ArrayList<Player> players;

    private int currentPlayerIndex;

    private Map map;

    private int round;

    private boolean propertySelectionEnabled;

    private int maxRounds;

    private Store store;


    /**
     * The constructor for a GameState.
     * By default nothing is valid.
    */
    public GameState() {
        players = new ArrayList<>();
        maxPlayers = 0;
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
     * Sets the limit for the number of players.
     * This is for initializing the players.
     * TODO(henry): make a factory class for game state
     * to avoid bad construction patterns.
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
     * @throws GameStateConfigException if there is invalid input.
    */
    protected void addPlayer(Player player) throws GameStateConfigException {
        try {
            validateNewPlayer(player);
            players.add(player);
        } catch (GameStateConfigException e) {
            throw e;
        }
    }

    /**
     * Checks if adding a player is valid.
     * The players must:
     * <ul>
     *  <li> Not have the same name as any other players </li>
     *  <li> Not exceed the max number of players </li>
     *  <li> Not have the same color as another player </li>
     *  <li> The name must not be empty or be longer than 15 characters. </li>
     * </ul>
     * @param player the player to validate.
     * @throws GameStateConfigException if the player does not match the criteria listed.
    */
    private void validateNewPlayer(Player player) throws GameStateConfigException {
        if (player.getName().equals("")) {
            throw new GameStateConfigException("empty name");
        }
        for (Player p : players) {
            if (player.getName().equals(p.getName())) {
                throw new GameStateConfigException("duplicate name");
            }
            if (player.getColor().equals(p.getColor())) {
                throw new GameStateConfigException("duplicate color");
            }
        }
        if (player.getName().length() > 15) {
            throw new GameStateConfigException("name is too long");
        }
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

    /**
     * Saves the game to a file called "save.json".
    */
    public void saveGame() {
        Gson converter = new Gson();
        try {
            File file = new File("save.json");
            PrintWriter out = new PrintWriter(file);
            out.println(converter.toJson(this));
            out.close();
        } catch (Exception e) {
            System.out.println("you are boned");
        }
    }

    /**
     * Factory method for the game state.
     * Allows a game state to be made from a json file.
     * @param path the path to the json file.
     * @return The created GameState object.
     * @throws IOException if the file does not exist.
    */
    public static GameState fromSavedGame(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String json = new String(data, "UTF-8");

        Gson converter = new Gson();
        GameState state = converter.fromJson(json, GameState.class);

        fixLostReferences(state);
        return state;
    }

    /**
     * Helper method for loading from the saved game.
     * During jsonification some references must be lost to avoid circular references.
     * This method restores any missing references.
     * @param state the GameState that needs it's references fixed.
    */
    private static void fixLostReferences(GameState state) {
        TurnManager instance = TurnManager.getInstance();
        instance.setGameState(state);
        for (Player p: state.getPlayers()) {
            ArrayList<Tile> realTiles = new ArrayList<>();
            for (Tile t: p.getTiles()) {
                Tile realTile = state.map.getTile(t.getX(), t.getY());
                realTile.setOwner(p);
                realTiles.add(realTile);
            }
            p.setTiles(realTiles);
        }
    }
}