package awktal.mule;

import java.util.ArrayList;

public class Inventory {
    private int money;
    private int food;
    private int energy;
    private int ore;
    private ArrayList<String> history;

    public Inventory(int money, int food, int energy, int ore) {
        this.money = money;
        this.food = food;
        this.energy = energy;
        this.ore = ore;
        this.history = new ArrayList<String>();
    }

    public int getMoney() {
        return money;
    }
    public int getFood() {
        return food;
    }
    public int getEnergy() {
        return energy;
    }
    public int getOre() {
        return ore;
    }

    public void depositMoney(int money) {
        this.money += money;
        history.add(String.format("deposited %d Money (Total: %d)", money, this.money));
    }
    public void depositFood(int food) {
        this.food += food;
    }
    public void depositEnergy(int energy) {
        this.energy += energy;
    }
    public void depositOre(int ore) {
        this.ore += ore;
    }

    public void withdrawMoney(int money) {
        this.money -= money;
    }
    public void withdrawFood(int food) {
        this.food -= food;
    }
    public void withdrawEnergy(int energy) {
        this.energy -= energy;
    }
    public void withdrawOre(int ore) {
        this.ore -= ore;
    }

    public String[] getHistory() {
        return history.toArray(new String[history.size()]);
    }
}