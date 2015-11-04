package awktal.mule;

import com.google.gson.Gson;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Factory class for the GameState object.
 * Accumulates information about the state of the game slowly
 * allowing GameState objects to be created after multiple screens have progressed.
*/
public class GameStateFactory {

    private static GameStateFactory instance;

    private int numPlayers;
    private ArrayList<Player> players;
    private Map map;

    /**
     * Gets the current instance of the GameStateFactory.
     * @return the current instance of hte game state factory.
    */
    public static GameStateFactory getInstance() {
        if (GameStateFactory.instance == null) {
            GameStateFactory.instance = new GameStateFactory();
        }
        return instance;
    }

    public GameStateFactory() {
        this.players = new ArrayList<Player>();
        this.map = null;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return this.map;
    }

    public void addPlayer(Player player) throws GameStateConfigException {
        validateNewPlayer(player);
        this.players.add(player);
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Creates a game state object from the current settings and resets the factory.
     * @return the created game state.
     * @throws UncheckedGameStateConfigException if there are invalid settings.
    */
    @SuppressFBWarnings(value = "ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
    public GameState createGameState() {
        if (players.size() != numPlayers) {
            throw new UncheckedGameStateConfigException("Invalid number of players");
        } else if (map == null) {
            throw new UncheckedGameStateConfigException("Map must be non null");
        } else {
            instance = new GameStateFactory();
            return new GameState(this.players, this.map);
        }
    }

    /**
     * Creates a GameState from a JSON string.
     * @param json the JSON representation as a string.
     * @return the created GameState.
    */
    public GameState fromJsonString(String json) {
        Gson converter = new Gson();
        GameState state = converter.fromJson(json, GameState.class);
        fixJsonLostReferences(state);
        return state;
    }

    private void fixJsonLostReferences(GameState state) {
        for (Player p: state.getPlayers()) {
            ArrayList<Tile> realTiles = new ArrayList<>();
            for (Tile t: p.getTiles()) {
                Tile realTile = state.getMap().getTile(t.getX(), t.getY());
                realTile.setOwner(p);
                realTiles.add(realTile);
            }
            p.setTiles(realTiles);
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
        if (numPlayers <= players.size()) {
            throw new UncheckedGameStateConfigException("too many players, restricted to "
                + numPlayers);
        }
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
}