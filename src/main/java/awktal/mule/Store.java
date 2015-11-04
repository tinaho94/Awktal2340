package awktal.mule;

import java.util.ArrayList;
import java.util.HashMap;

/**
* Maintains the store content in the game. 
* Example: A player enters the store and attempts to purchase a mule. 
* The mule is purchased, and the resources of the player 
* and the store are updated. 
*/
public class Store {

    private Inventory inventory;
    private HashMap<Resource, Integer> costs;
    private ArrayList<Mule> mules;
    private HashMap<MuleType, Integer> outfitCosts;

    private static final int MULE_COST = 100;
    private static final int DEFAULT_FOOD_COST = 30;
    private static final int DEFAULT_ENERGY_COST = 25;
    private static final int DEFAULT_ORE_COST = 50;
    private static final int DEFAULT_NUM_MULES = 25;

    private static final int DEFAULT_STORE_MONEY = 100000;
    private static final int DEFAULT_STORE_FOOD = 16;
    private static final int DEFAULT_STORE_ENERGY = 16;
    private static final int DEFAULT_STORE_ORE = 0;

    private static final int DEFAULT_FOOD_OUTFIT_COST = 25;
    private static final int DEFAULT_ENERGY_OUTFIT_COST = 50;
    private static final int DEFAULT_ORE_OUTFIT_COST = 75;

    /**
     * Creates a store.
     * @param money the starting amount of money.
     * @param food the starting amount of food;
     * @param energy the starting amount of energy;
     * @param ore the starting amount of ore;
     * @param numMules the starting amount of mules;
    */
    public Store(int money, int food, int energy, int ore, int numMules) {
        this.inventory = new Inventory(money, food, energy, ore);
        this.costs = getDefaultCosts();
        this.mules = new ArrayList<Mule>();
        this.outfitCosts = getDefaultOutfitCosts();
        for (int i = 0; i < numMules; i++) {
            this.mules.add(new Mule());
        }
    }

    public Store() {
        this(DEFAULT_STORE_MONEY, DEFAULT_STORE_FOOD, DEFAULT_STORE_ENERGY, DEFAULT_STORE_ORE,
            DEFAULT_NUM_MULES);
    }

    private HashMap<Resource, Integer> getDefaultCosts() {
        HashMap<Resource, Integer> costs = new HashMap<>();
        costs.put(Resource.FOOD, DEFAULT_FOOD_COST);
        costs.put(Resource.ENERGY, DEFAULT_ENERGY_COST);
        costs.put(Resource.ORE, DEFAULT_ORE_COST);
        costs.put(Resource.MONEY, 1);
        return costs;
    }

    /**
     * Gets the costs for outfitting a mule.
     * @return a map of the possible outfits to their respective costs in money.
    */
    public HashMap<MuleType, Integer> getDefaultOutfitCosts() {
        HashMap<MuleType, Integer> costs = new HashMap<>();
        costs.put(MuleType.NONE, 0);
        costs.put(MuleType.FOOD, DEFAULT_FOOD_OUTFIT_COST);
        costs.put(MuleType.ENERGY, DEFAULT_ENERGY_OUTFIT_COST);
        costs.put(MuleType.ORE, DEFAULT_ORE_OUTFIT_COST);
        return costs;
    }

    public int getResourceCost(Resource resource) {
        return this.costs.get(resource);
    }

    public int getMuleCost(MuleType type) {
        return MULE_COST + outfitCosts.get(type);
    }

    public int getNumMules() {
        return mules.size();
    }

    /**
     * Attempt to buy a mule for the player.
     * Preconditions:
     * <ul>
     *   <li> player has enough resources. </li>
     *   <li> store has enough mules. </li>
     *   <li> player does not already have a mule. </li>
     * </ul>
     * Throws a runtime exception if there are no mules left or the player has insufficient funds.
     * @param player the player that is trying to buy the mule.
     * @param type the type of mule that the player wants to buy.
     * @throws RuntimeException if the player or store does not have all required preconditions.
    */
    public void buyMule(Player player, MuleType type) {
        int cost = getMuleCost(type);
        if (player.getResource(Resource.MONEY) < cost) {
            throw new RuntimeException("insufficient funds");
        }
        if (mules.size() == 0) {
            throw new RuntimeException("No more mules");
        }
        player.takeResource(Resource.MONEY, cost);
        Mule mule = mules.remove(mules.size() - 1);
        mule.outfit(type);
        if (player.hasMule()) {
            System.out.println("Current mule was lost");
        }
        player.giveMule(mule);
    }

    public void giveMule(Mule mule) {
        mules.add(mule);
    }

    public int getStock(Resource resource) {
        return inventory.getResource(resource);
    }

    /**
     * Attempt to buy a mule for the player.
     * Preconditions:
     * <ul>
     *   <li> player has enough money. </li>
     *   <li> store has enough of the resource. </li>
     * </ul>
     * @param resource the type of resource to buy.
     * @param player the player that is trying to buy resources.
     * @param quantity the amount of the resource to buy.
     * @throws RuntimeException if the player or store does not have all required preconditions.
    */
    public void buyResource(Resource resource, int quantity, Player player) {
        if (getStock(resource) < quantity) {
            throw new RuntimeException("Out of stock");
        }
        int total = costs.get(resource) * quantity;
        if (player.getResource(Resource.MONEY) < total) {
            throw new RuntimeException("Insufficient funds");
        }
        player.takeResource(Resource.MONEY, total);
        player.giveResource(resource, quantity);
        inventory.takeResource(resource, quantity);
        inventory.giveResource(Resource.MONEY, total);
    }

    /**
     * Attempt to buy a mule for the player.
     * Preconditions:
     * <ul>
     *   <li> player has enough of the resource. </li>
     * </ul>
     * @param resource the type of resource to sell.
     * @param player the player that is trying to sell resources.
     * @param quantity the amount of the resource to sell.
     * @throws RuntimeException if the player or store does not have all required preconditions.
    */
    public void sellResource(Resource resource, int quantity, Player player) {
        if (player.getResource(resource) < quantity) {
            throw new RuntimeException("Player has insufficient resources to sell " + quantity
                + " units");
        }
        player.takeResource(resource, quantity);
        player.giveResource(Resource.MONEY, getResourceCost(resource) * quantity);
        inventory.giveResource(resource, quantity);

    }
}