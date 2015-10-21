package awktal.mule;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

/**
 * Keeps track of resources that belong to a single game object.
*/
public class Inventory {
    private HashMap<Resource, Integer> resources;

    public Inventory() {
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

    public int getResource(Resource resource) {
        return this.resources.get(resource);
    }

    public void takeResource(Resource resource, int quantity) {
        this.resources.put(resource, resources.get(resource) - quantity);
    }

    public void giveResource(Resource resource, int quantity) {
        this.resources.put(resource, resources.get(resource) + quantity);
    }

    /**
     * Gives the resources from one inventory to another.
     * <strong> NOTE: this does not take the resources form the other inventory. </strong>
     * @param inventory the inventory to add resources from.
    */
    public void giveResources(Inventory inventory) {
        for (Map.Entry<Resource, Integer> pair : inventory.resources.entrySet()) {
            this.giveResource(pair.getKey(), pair.getValue());
        }
    }

    public Set<Map.Entry<Resource, Integer>> getResourcePairs() {
        return resources.entrySet();
    }

    /**
     * Gets a copy of the inventory with one resource scaled by the certain factor.
     * @param scale the amount to scale the resource.
     * @param type the resource type that you want to scale.
    */
    public Inventory scaleResource(double scale, Resource type) {
        Inventory inventory = this.copy();
        int resource = inventory.resources.get(type);
        resource *= scale;
        inventory.resources.put(type, resource);
        return inventory;
    }

    /**
     * Gets a copy of this inventory.
     * @return a deep copy of this inventory.
    */
    public Inventory copy() {
        return new Inventory(this.getResource(Resource.MONEY),
            this.getResource(Resource.FOOD), this.getResource(Resource.ENERGY),
            this.getResource(Resource.ORE));
    }
}