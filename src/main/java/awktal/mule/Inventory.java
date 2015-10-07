package awktal.mule;

import java.util.ArrayList;

/**
 * Keeps track of resources that belong to a single game object.
*/
public class Inventory {
    private int money;
    private int food;
    private int energy;
    private int ore;
    private ArrayList<String> history;
    private ArrayList<Mule> mules;
    private int MULE = 5;
    private final int COST = 50;
    private final int BASE_BUY_BACK = 25;

    public Inventory(){
        this(0,0,0,0,0);//
    }
    /**
     * Constructs a new Inventory.
     * @param money the starting money for the inventory.
     * @param food the starting food for the inventory.
     * @param energy the starting energy for the inventory.
     * @param ore the starting ore for the inventory.
    */
    public Inventory(int money, int food, int energy, int ore, int numMules) {
        this.money = money;
        this.food = food;
        this.energy = energy;
        this.ore = ore;
        this.history = new ArrayList<String>();
        this.mules = new ArrayList<Mule>();
        for(int i = 0; i<numMules; i++){
            this.mules.add(new Mule());
        }
    }

    /**
     * Gets the amount of money in the inventory.
     * @return the amount of money in the inventory.
    */
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets the amount of food in the inventory.
     * @return the amount of food in the inventory.
    */
    public int getFood() {
        return food;
    }

    /**
     * Gets the amount of energy in the inventory.
     * @return the amount of energy in the inventory.
    */
    public int getEnergy() {
        return energy;
    }

    /**
     * Gets the amount of ore in the inventory.
     * @return the amount of ore in the inventory.
    */
    public int getOre() {
        return ore;
    }

    /**
     * Adds money to the inventory.
     * @param money the amount of money to add to the inventory.
    */
    public void depositMoney(int money) {
        this.money += money;
        history.add(String.format("deposited %d Money (Total: %d)", money, this.money));
    }

    /**
     * Adds food to the inventory.
     * @param food the amount of food to add to the inventory.
    */
    public void depositFood(int food) {
        this.food += food;
    }

    /**
     * Adds energy to the inventory.
     * @param energy the amount of energy to add to the inventory.
    */
    public void depositEnergy(int energy) {
        this.energy += energy;
    }

    /**
     * Adds ore to the inventory.
     * @param ore the amount of ore to add to the inventory.
    */
    public void depositOre(int ore) {
        this.ore += ore;
    }

    /**
     * Withdraws money from the inventory.
     * @param money the amount of money to withdraw from the inventory.
    */
    public void withdrawMoney(int money) {
        this.money -= money;
    }

    /**
     * Withdraws food from the inventory.
     * @param food the amount of food to withdraw from the inventory.
    */
    public void withdrawFood(int food) {
        this.food -= food;
    }

    /**
     * Withdraws energy from the inventory.
     * @param energy the amount of energy to withdraw from the inventory.
    */
    public void withdrawEnergy(int energy) {
        this.energy -= energy;
    }

    /**
     * Withdraws ore from the inventory.
     * @param ore the amount of ore to withdraw from the inventory.
    */
    public void withdrawOre(int ore) {
        this.ore -= ore;
    }

    /**
     * Returns a history of the transactions that have been done to this inventory.
     * @return a list of transactions that have been done to this inventory in chronological order.
    */
    public String[] getHistory() {
        return history.toArray(new String[history.size()]);
    }

//-----------------------------------------------------

    /**
    * gets the number of mules in the store
    * @return the number of mules in the store
    */
    public int getMule() {
        return MULE;
    }

    // public void setMule(int mule) {
    //     mules = mule; 
    // }
    /**
    * gets the cost of mules in the store
    * @return the cost of mules in the store
    */
    public int getCost() {
        return COST;
    }
    /**
    * gets the cost at which the store will buy players resources
    * @return the cost of mules in the store
    */
    public int getSellBack() {
        return BASE_BUY_BACK;
    }
}