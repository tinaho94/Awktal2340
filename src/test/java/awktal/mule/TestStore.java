package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

/**
* JUnit to test the Store class.
* @author: Stephen Zolnik
* 11/4/2015
*/
public class TestStore {

    private Store store;
    private Inventory inventory;
    private Player player;

    private static final int FOOD_MULE = 125;   // 100 + 25~outfit
    private static final int ENERGY_MULE = 150; // 100 + 50~outfit
    private static final int ORE_MULE = 175;    // 100 + 75~outfit

    private static final int DEFAULT_NUM_MULES = 25;

    public static final int TIMEOUT = 200;

    /**
    * Calculates the players money after the mule purchase
    * @param type is the mule type
    * @param palyersMoney is the money the player currently has
    * @return money that the player should now have
    */
    public int getPlayerMoney(int muleCost, int playersMoney) {
        int money = playersMoney - muleCost;
        return money;
    }


    @Before
    public void setUp() throws Exception {
        store = new Store();
        this.inventory = new Inventory();
        player = new Player("Steve", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN);
    }


    @Test (timeout = TIMEOUT)
    public void testPlayerMoney() throws Exception {
           try {
            store.buyMule(player, MuleType.FOOD);
            fail("Player should have had enough money but did not.");
        } catch (RuntimeException e) {
            //test passes
            return;
        }
    }

    @Test (timeout = TIMEOUT)
    public void testPlayerHasMule() throws Exception {
        try {
            assertEquals(player.hasMule(), false);
            store.buyMule(player, MuleType.ENERGY);
            assertEquals(player.hasMule(), true);
        } catch (RuntimeException e) {
            fail("There were not enough mules left.");
            return;
        }
    }


    @Test (timeout = TIMEOUT)
    public void testStoreInventory() throws Exception {
        try {
            //check when the player has or has not enough $$ 2 cases
            //test the bndry case when the store runs out of mules...1 mule left vs 0 left
            //add a set mule method to the store to set the # of mules...private... put it in the 
            //package protected...classes in the same package can call the method variable
            //making the method protected...encapculation idea.
            store.buyMule(player, MuleType.ORE);
            assertEquals(store.getNumMules(), 24);
            store.buyMule(player, MuleType.ENERGY);
            assertEquals(store.getNumMules(), 23);
            store.buyMule(player, MuleType.FOOD);
            assertEquals(store.getNumMules(), 22);

        } catch (RuntimeException e) {
            fail("The purchased mule was not removed from the store.");
            return;
        }
    }

}

/*
1) for each branch condition.... check all of the post conditins...(players $, whether or not the player has a mule, )
*/