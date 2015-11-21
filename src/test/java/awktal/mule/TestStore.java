package awktal.mule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

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
    public static final int TIMEOUT = 200;


    @Before
    public void setUp() throws Exception {
        store = new Store();
        this.inventory = new Inventory();
        player = new Player("Steve", new Color(0.0, 0.0, 0.0, 0.0), Race.HUMAN);
    }

    @Test (timeout = TIMEOUT) // expected exception type...no need for the catch
    public void testPlayerWithoutMoney() {
        try {
            player.takeResource(Resource.MONEY, player.getResource(Resource.MONEY));
            store.buyMule(player, MuleType.FOOD);
            fail("Should have thrown a RuntimeException, player did not have enough money.");
        } catch (RuntimeException e) {
            return;
        }
    }

    @Test (timeout = TIMEOUT)
    public void testNoMoreMules() {
        try {
            player.giveResource(Resource.MONEY, 1000);
            for (int i = 0; i < 26; i++) {
                store.buyMule(player, MuleType.FOOD);
            }
            fail("Should have thrown a RuntimeException, store has no more resources");
        } catch (RuntimeException e) {
            return;
        }
    }

    @Test (timeout = TIMEOUT)
    public void testBuyMule() throws Exception {
        try {   
            store.buyMule(player, MuleType.FOOD);
            assertEquals(player.getResource(Resource.MONEY), 475);//use constance instead of #
            store.buyMule(player, MuleType.ORE);
            assertEquals(store.getNumMules(), 23);
            store.buyMule(player, MuleType.FOOD);
            assertTrue(player.hasMule());
        } catch (RuntimeException e) {
            fail("The purchased mule was not removed from the store.");
            return;
        }
    }

}

/*
1) for each branch condition.... check all of the post conditins...(players $, whether or not the player has a mule, )
check when the player has or has not enough $$ 2 cases
test the bndry case when the store runs out of mules...1 mule left vs 0 left
add a set mule method to the store to set the # of mules...private... put it in the 
package protected...classes in the same package can call the method variable
making the method protected...encapculation idea.
*/