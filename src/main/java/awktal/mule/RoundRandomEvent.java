package awktal.mule;

public abstract class RoundRandomEvent {
    protected String name;
    protected String description;

    public String getName()  {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(GameState gameState);
}