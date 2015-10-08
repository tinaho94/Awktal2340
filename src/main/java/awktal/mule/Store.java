package awktal.mule;

public class Store {

	private Inventory inventory;
	private int cost;
	private int numMules;
	
	private final int FOOD_COST = 500;
	private final int FOOD_BOUGHT = 2;
    private final int FOOD_SOLD = 2;

	public Store(int cost, int numMules){
		this.cost = cost;
		this.numMules = numMules;
	}

	public Store() {
		this.inventory = new Inventory();
	}	
	
	public Store(int money, int food, int energy, int ore, int numMules) {
		this.inventory = new Inventory(money, food, energy, ore, numMules);
	}
	public int getCost() {
		return inventory.getCost();
	}

	public int getFoodCost(){
		return FOOD_COST;
	}
	public int getFoodBought(){
		return FOOD_BOUGHT;
	}
	public int getFoodSold(){
		return FOOD_SOLD;
	}
	public int getFood () {
		return inventory.getFood();
	}
	public int getNumMules() {
		return numMules;
	}

	public void setNumMules(int mules) {
		numMules = numMules - mules;
	}
}