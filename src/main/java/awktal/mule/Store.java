package awktal.mule;

import java.util.HashMap;
import java.util.ArrayList;

public class Store {

	private Inventory inventory;
	private HashMap<Resource, Integer> costs;
	private ArrayList<Mule> mules;
	private HashMap<MuleType, Integer> outfitCosts;

	private final static int MULE_COST = 100;
	private final static int DEFAULT_FOOD_COST = 30;
	private final static int DEFAULT_ENERGY_COST = 25;
	private final static int DEFAULT_ORE_COST = 50;
	private final static int DEFAULT_NUM_MULES = 25;

	private final static int DEFAULT_STORE_MONEY = 100000;
	private final static int DEFAULT_STORE_FOOD = 16;
	private final static int DEFAULT_STORE_ENERGY = 16;
	private final static int DEFAULT_STORE_ORE = 0;

	private final static int DEFAULT_FOOD_OUTFIT_COST = 25;
	private final static int DEFAULT_ENERGY_OUTFIT_COST = 50;
	private final static int DEFAULT_ORE_OUTFIT_COST = 75;

	public Store(int money, int food, int energy, int ore, int numMules){
		this.inventory = new Inventory(money, food, energy, ore);
		this.costs = getDefaultCosts();
		this.mules = new ArrayList<Mule>();
		this.outfitCosts = getDefaultOutfitCosts();
		for (int i = 0; i < numMules; i++) {
			this.mules.add(new Mule());
		}
	}

	public Store() {
		this(DEFAULT_STORE_MONEY, DEFAULT_STORE_FOOD, DEFAULT_STORE_ENERGY, DEFAULT_STORE_ORE, DEFAULT_NUM_MULES);
	}

	private HashMap<Resource, Integer> getDefaultCosts() {
		HashMap<Resource, Integer> costs = new HashMap<>();
		costs.put(Resource.FOOD, DEFAULT_FOOD_COST);
		costs.put(Resource.ENERGY, DEFAULT_ENERGY_COST);
		costs.put(Resource.ORE, DEFAULT_ORE_COST);
		costs.put(Resource.MONEY, 1);
		return costs;
	}

	public HashMap<MuleType, Integer> getDefaultOutfitCosts() {
		HashMap<MuleType, Integer> costs = new HashMap<>();
		costs.put(MuleType.NONE, 0);
		costs.put(MuleType.FOOD, DEFAULT_FOOD_OUTFIT_COST);
		costs.put(MuleType.ENERGY, DEFAULT_ENERGY_OUTFIT_COST);
		costs.put(MuleType.ORE, DEFAULT_ORE_OUTFIT_COST);
		return costs;
	}

	public int getResourceCost(Resource r) {
		return this.costs.get(r);
	}

	public int getMuleCost(MuleType type) {
		return MULE_COST + outfitCosts.get(type);
	}

	public int getNumMules() {
		return mules.size();
	}

	public void buyMule(Player p, MuleType type) {
		int cost = getMuleCost(type);
		if (p.getResource(Resource.MONEY) < cost) {
			throw new RuntimeException("insufficient funds");
		}
		if (mules.size() == 0) {
			throw new RuntimeException("No more mules");
		}
		p.takeResource(Resource.MONEY, cost);
		Mule m = mules.remove(mules.size() - 1);
		m.outfit(type);
		if (p.hasMule()) {
			System.out.println("Current mule was lost");
		}
		p.giveMule(m);
	}

	public void giveMule(Mule mule) {
		mules.add(mule);
	}

	public int getStock(Resource r) {
		return inventory.getResource(r);
	}

	public void buyResource(Resource r, int quantity, Player p) {
		if (getStock(r) < quantity) {
			throw new RuntimeException("Out of stock");
		}
		int total_cost = costs.get(r) * quantity;
		if (p.getResource(Resource.MONEY) < total_cost) {
			throw new RuntimeException("Insufficient funds");
		}
		p.takeResource(Resource.MONEY, total_cost);
		p.giveResource(r, quantity);
		inventory.takeResource(r, quantity);
		inventory.giveResource(Resource.MONEY, total_cost);
	}

	public void sellResource(Resource r, int quantity, Player p) {
		if (p.getResource(r) < quantity) {
			throw new RuntimeException("Player has insufficient resources to sell " + quantity + " units");
		}
		p.takeResource(r, quantity);
		p.giveResource(Resource.MONEY, getResourceCost(r) * quantity);
		inventory.giveResource(r, quantity);

	}
}