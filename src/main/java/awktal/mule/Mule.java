package awktal.mule;

public class Mule {

    private MuleType type;
    private Player owner;
    private Tile tile;

    public Mule() {
        this(MuleType.NONE);
    }

    public Mule(MuleType type) {
        this.type = type;
    }

    public void outfit(MuleType type) {
        this.type = type;
    }

    public MuleType getType() {
        return this.type;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}