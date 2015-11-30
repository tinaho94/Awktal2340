package awktal.mule;

/**
 * Corrosive plants kill all mules that are on plains.
*/
public class CorrosivePlantsEvent extends RoundRandomEvent {
    /**
     * Creates a new corrosive plants event.
    */
    public CorrosivePlantsEvent() {
        this.name = "Corrosive Plants";
        this.description = "Corrosive plants kill all mules that are on plains.";
    }

    /**
     * Executes the event.
     * @param gameState the gameState that the event modifies.
    */
    public void execute(GameState gameState) {
        for (Tile t : gameState.getMap()) {
            if (t.getType() == TileType.PLAIN) {
                t.setMule(null);
            }
        }
    }
}