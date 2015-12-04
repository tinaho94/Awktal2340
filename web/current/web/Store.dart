import 'Inventory.dart';
import 'ResourceType.dart';
import 'MuleType.dart';
import 'Mule.dart';
import 'Player.dart';

class Store {
    static final int MULE_COST = 100;
    static final int DEFAULT_FOOD_COST = 30;
    static final int DEFAULT_ENERGY_COST = 25;
    static final int DEFAULT_ORE_COST = 50;

    static final int DEFAULT_STORE_MONEY = 100000;
    static final int DEFAULT_STORE_FOOD = 16;
    static final int DEFAULT_STORE_ENERGY = 16;
    static final int DEFAULT_STORE_ORE = 0;

    static final int DEFAULT_FOOD_OUTFIT_COST = 25;
    static final int DEFAULT_ENERGY_OUTFIT_COST = 50;
    static final int DEFAULT_ORE_OUTFIT_COST = 75;

    static final int DEFAULT_NUM_MULES = 25;

    Inventory inventory;
    Map<ResourceType, int> costs;
    Map<MuleType, int> outfitCosts;
    List<Mule> mules;

    Store() {
        this.inventory = new Inventory(DEFAULT_STORE_FOOD, DEFAULT_STORE_ENERGY, DEFAULT_STORE_ORE, DEFAULT_STORE_MONEY);
        this.costs = getDefaultCosts();
        this.mules = [];
        this.outfitCosts = getDefaultOutfitCosts();
        for (int i = 0; i < DEFAULT_NUM_MULES; i++) {
            this.mules.add(new Mule());
        }
    }

    Map<ResourceType, int> getDefaultCosts() {
        Map<ResourceType, int> costs = {};
        costs[ResourceType.FOOD] = DEFAULT_FOOD_COST;
        costs[ResourceType.ENERGY] = DEFAULT_ENERGY_COST;
        costs[ResourceType.ORE] = DEFAULT_ORE_COST;
        costs[ResourceType.MONEY] = 1;
        return costs;
    }

    void buyResource(ResourceType resource, int quantity, Player player) {
        if (inventory.resources[resource] < quantity) {
            return;
        }
        int total = costs[resource] * quantity;
        if (player.inventory.resources[ResourceType.MONEY] < total) {
            return;
        }
        player.inventory.resources[ResourceType.MONEY] -= total;
        player.inventory.resources[resource] += quantity;
        inventory.resources[resource] -= quantity;
        inventory.resources[ResourceType.MONEY] += total;
    }

    void sellResource(ResourceType resource, int quantity, Player player) {
        if (player.inventory.resources[resource] < quantity) {
            return;
        }
        player.inventory.resources[resource] -= quantity;
        player.inventory.resources[ResourceType.MONEY] += costs[resource] * quantity;
        inventory.resources[resource] += quantity;
    }

    void buyMule(Player player, MuleType type) {
        int cost = outfitCosts[type] + MULE_COST;
        if (player.invetory[Resource.MONEY] < cost) {
            return;
        }
        if (mules.length == 0) {
            return;
        }
        player.inventory[Resource.MONEY] -= cost;
        Mule mule = mules.removeLast();
        mule.outfit(type);
        player.mule = mule;
    }

    Map<MuleType, int> getDefaultOutfitCosts() {
        Map<MuleType, int> costs = {};
        costs[MuleType.NONE] = 0;
        costs[MuleType.FOOD] = DEFAULT_FOOD_OUTFIT_COST;
        costs[MuleType.ENERGY] = DEFAULT_ENERGY_OUTFIT_COST;
        costs[MuleType.ORE] = DEFAULT_ORE_OUTFIT_COST;
        return costs;
    }
}