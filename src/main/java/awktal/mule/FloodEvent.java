package awktal.mule;

/**
 * A Flood event kills all mules that are on mountains.
*/
public class FloodEvent extends RoundRandomEvent {
    /**
     * Creates a new flood event.
    */
    public FloodEvent() {
        this.name = "Flood";
        this.description = "An flood kills all mules that are on rivers.";
    }

    /**
     * Executes the event.
     * @param gameState the gameState that the event modifies.
    */
    public void execute(GameState gameState) {
        for (Tile t : gameState.getMap()) {
            if (t.getType() == TileType.RIVER) {
                t.setMule(null);
            }
        }
    }
}