package awktal.mule;



/**
 * Keeps track of the store inventory.
*/
public class StoreInventory {
	private int mule;
	private int cost;

/**
* @param mule skjfvnsiev
* @param cost of each mule
*/
	public StoreInventory(int mule, int cost) {
		this.mule = mule;
		this.cost = cost;
	}

/**
* gets the number of mules in the store
* @return the number of mules in the store
*/
	public int getMule() {
		return mule;
	}
/**
* gets the cost of mules in the store
* @return the cost of mules in the store
*/
	public int getCost() {
		return cost;
	}


}

