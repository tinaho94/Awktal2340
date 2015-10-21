package awktal.mule;

/**
 * Represents the possible scenes that the game can be in.
 * Allows for getFxml() to return the FXML file that matched the scene.
*/
public enum GameScene {
    GAME_CONFIG,
    PLAYER_CONFIG,
    LAND_SELECTION,
    TOWN,
    STORE,
    PUB,
    WORLD_VIEW,
    START_ROUND,
    START_TURN;

    private String fxml;

    static {
        GAME_CONFIG.fxml = "game_config.fxml";
        PLAYER_CONFIG.fxml = "player_config.fxml";
        LAND_SELECTION.fxml = "land_selection.fxml";
        TOWN.fxml = "town.fxml";
        STORE.fxml = "store.fxml";
        PUB.fxml = "pub.fxml";
        WORLD_VIEW.fxml = "world_view.fxml";
        START_ROUND.fxml = "start_round.fxml";
        START_TURN.fxml = "start_turn.fxml";
    }

    /**
     * Gets the path to the fxml file for this scene.
     * @return the path to the FXML file.
    */
    public String getFxml() {
        return fxml;
    }
}