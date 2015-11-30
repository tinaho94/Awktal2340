package awktal.mule;

/**
 * A Earthquake event kills all mules that are on mountains.
*/
public class EarthquakeEvent extends RoundRandomEvent {
    /**
     * Creates a new earthquake event.
    */
    public EarthquakeEvent() {
        this.name = "Earthquake";
        this.description = "An earthquake kills all mules that are on mountains.";
    }

    /**
     * Executes the event.
     * @param gameState the gameState that the event modifies.
    */
    public void execute(GameState gameState) {
        for (Tile t : gameState.getMap()) {
            if (t.getType() == TileType.MOUNTAIN1 || t.getType() == TileType.MOUNTAIN2
                || t.getType() == TileType.MOUNTAIN3) {
                t.setMule(null);
            }
        }
        System.out.println("A earthquake happened");
    }
}