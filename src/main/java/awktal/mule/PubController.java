package awktal.mule;

import javafx.fxml.FXML;
import java.util.Random;

/**
 * PubController controls the game configuration screen.
*/
public class PubController extends PlayerTurnSceneController {

    private final int[] roundBonus = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

    /**
     * Constructor for a PubController.
    */
    public PubController() {
    }

    /**
     * Will be called after the constructor when the fxml is loaded.
    */
    @FXML
    private void initialize() {
        loadPlayerData();
    }

    @FXML
    public void gamble() {
        int moneyEarned = calculateGamblingEarnings();
        Player currentPlayer = TurnManager.getInstance().getCurrentPlayer();
        currentPlayer.getInventory().depositMoney(moneyEarned);
        System.out.println(currentPlayer.getName() + " has earned " + moneyEarned + " spacebucks gambling!");
        TurnManager.getInstance().endPlayerTurn();
    }

    private int calculateGamblingEarnings() {
        Random rand = new Random();
        int remainingTime = TurnManager.getInstance().getCurrentTurnTime();
        int timeBonusMax;
        if (remainingTime >= 37) {
            timeBonusMax = 200;
        } else if (remainingTime >= 25) {
            timeBonusMax = 150;
        } else if (remainingTime >= 12) {
            timeBonusMax = 100;
        } else {
            timeBonusMax = 50;
        }
        int timeBonus = (int) (rand.nextDouble() * timeBonusMax);
        int totalBonus = timeBonus + roundBonus[gameState.getRound() - 1];
        return (totalBonus > 250) ? 250 : totalBonus;
    }

    @FXML
    public void returnToTown() {
        TurnManager.getInstance().loadScene(GameScene.TOWN);
    }

}