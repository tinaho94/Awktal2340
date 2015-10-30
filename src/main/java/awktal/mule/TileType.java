package awktal.mule;

import java.util.HashMap;


public enum TileType {
    RIVER ("map_pictures/water.png", 4, 2, 0),
    PLAIN ("map_pictures/grass.png", 2, 3, 1),
    MOUNTAIN1 ("map_pictures/mount1.png", 1, 1, 2),
    MOUNTAIN2 ("map_pictures/mount2.png", 1, 1, 3),
    MOUNTAIN3 ("map_pictures/mount3.png", 1, 1, 4),
    BUILDING ("map_pictures/building.png", 0, 0, 0);

    private String path;
    private HashMap<Resource, Integer> yields;

    private TileType(String path, int food, int energy, int ore) {
        this.yields = new HashMap<Resource, Integer>();
        yields.put(Resource.FOOD, food);
        yields.put(Resource.ENERGY, energy);
        yields.put(Resource.ORE, ore);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    /**
     * Calculates the production of the tile if worked for the specific resource.
     * @param resource the type of resource to calculate production for.
     * @return an inventory with the produced resources.
    */
    public Inventory calculateProduction(Resource resource) {
        Inventory production = new Inventory();
        production.giveResource(resource, yields.get(resource));
        return production;
    }
}