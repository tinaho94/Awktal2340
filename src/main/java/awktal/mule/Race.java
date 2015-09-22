package awktal.mule;

public enum Race {
    HUMAN,
    FLAPPER,
    BONZOID,
    UGAITE,
    BUZZITE;

    private int startingMoney;

    static {
        HUMAN.startingMoney = 600;
        FLAPPER.startingMoney = 1600;
        BONZOID.startingMoney = 1000;
        UGAITE.startingMoney = 1000;
        BUZZITE.startingMoney = 1000;
    }

    /**
     * Gets the starting money for a race.
     * @return the amount of money that a race should start the game with.
    */
    public int getStartingMoney() {
        return startingMoney;
    }
}