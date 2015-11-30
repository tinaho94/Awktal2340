package awktal.mule;

/**
 * A null event that does nothing.
*/
public class NullRandomEvent extends RoundRandomEvent {
    /**
     * Creates a new null event.
    */
    public NullRandomEvent() {
        this.name = "Nothing";
        this.description = "Nothing happens, the world seems to stand still.";
    }

    /**
     * Executes the event.
     * @param gameState the gameState that the event modifies.
    */
    public void execute(GameState gameState) {
    }
}