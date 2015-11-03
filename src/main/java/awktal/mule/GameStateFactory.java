package awktal.mule;

import com.google.gson.Gson;

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
        if (instance == null) {
            instance = new GameStateFactory();
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

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Creates a game state object from the current settings and resets the factory.
     * @return the created game state.
     * @throws GameStateConfigException if there are invalid settings.
    */
    public GameState createGameState() throws GameStateConfigException {
        if (players.size() != numPlayers) {
            throw new GameStateConfigException("Invalid number of players");
        } else if (map != null) {
            throw new GameStateConfigException("Map must be non null");
        } else {
            ArrayList<Player> players = this.players;
            this.players = null;
            this.map = null;
            return new GameState(players, map);
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
}