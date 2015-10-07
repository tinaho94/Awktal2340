package awktal.mule;

public class Store {

	private Inventory inventory;
	private int cost;
	private int numMules;
	
	// private int MULE = 5;
 //    private final int COST = 50;
 //    private final int BASE_BUY_BACK = 25;

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
	public int getNumMules() {
		return numMules;
	}

	public void setNumMules(int mules) {
		numMules = numMules - mules;
	}


}