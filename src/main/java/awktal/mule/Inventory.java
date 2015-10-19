package awktal.mule;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

/**
 * Keeps track of resources that belong to a single game object.
*/
public class Inventory {
    private HashMap<Resource, Integer> resources;

    public Inventory(){
        this(0,0,0,0);//
    }
    /**
     * Constructs a new Inventory.
     * @param money the starting money for the inventory.
     * @param food the starting food for the inventory.
     * @param energy the starting energy for the inventory.
     * @param ore the starting ore for the inventory.
    */
    public Inventory(int money, int food, int energy, int ore) {
        this.resources = new HashMap<>();
        resources.put(Resource.MONEY, money);
        resources.put(Resource.FOOD, food);
        resources.put(Resource.ENERGY, energy);
        resources.put(Resource.ORE, ore);
    }

    public int getResource(Resource r) {
        return this.resources.get(r);
    }

    public void takeResource(Resource r, int quantity) {
        this.resources.put(r, resources.get(r) - quantity);
    }

    public void giveResource(Resource r, int quantity) {
        this.resources.put(r, resources.get(r) + quantity);
    }

    public void giveResources(Inventory i) {
        for (Map.Entry<Resource, Integer> pair : i.resources.entrySet()) {
            this.giveResource(pair.getKey(), pair.getValue());
        }
    }

    public Set<Map.Entry<Resource, Integer>> getResourcePairs() {
        return resources.entrySet();
    }

    public Inventory scaleResource(double scale, Resource resource) {
        Inventory i = this.copy();
        int r = i.resources.get(resource);
        r *= scale;
        i.resources.put(resource, r);
        return i;
    }

    public Inventory copy() {
        return new Inventory(this.getResource(Resource.MONEY),
            this.getResource(Resource.FOOD), this.getResource(Resource.ENERGY),
            this.getResource(Resource.ORE));
    }
}