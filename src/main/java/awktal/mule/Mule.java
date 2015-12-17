package awktal.mule;

public class Mule {

    private MuleType type;

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
}