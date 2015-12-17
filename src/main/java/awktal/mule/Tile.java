package awktal.mule;

public class Tile {

    private int xloc;
    private int yloc;
    private transient Player owner;
    private TileType type;
    private Mule mule;

    /**
     * Creates a new tile.
     * @param xloc the x location of the tile.
     * @param yloc the y location of the tile.
     * @param type the type of tile.
    */
    public Tile(int xloc, int yloc, TileType type) {
        this.xloc = xloc;
        this.yloc = yloc;
        this.type = type;
        mule = null;
    }

    public TileType getType() {
        return this.type;
    }

    public Player setOwner(Player owner) {
        return this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public boolean isOwned() {
        return owner != null;
    }

    public int getX() {
        return xloc;
    }

    public int getY() {
        return yloc;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
    }

    public Mule getMule() {
        return mule;
    }

    public boolean hasMule() {
        return mule != null;
    }

    /**
     * Calculates a tile's production.
     * A tile only produces things when a mule is installed.
     * @return an Inventory with the produced resources.
    */
    public Inventory calculateProduction() {
        if (mule == null) {
            return new Inventory();
        } else {
            return type.calculateProduction(mule.getType().getProductionTypes());
        }
    }
}