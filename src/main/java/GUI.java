import java.util.ArrayList;

public class GUI {

    private GameEngine gameEngine;

    public GUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void render() {
    }

    public void printPlayerHP() {
        System.out.println("Player 1 HP: " + gameEngine.getPlayer1().getHp());
        System.out.println("Player 2 HP: " + gameEngine.getPlayer2().getHp());
    }

    public void printCardsInHand() {

    }

    public void printRemainingCards() {

    }

    public void printCardsOnTable() {
        Player activePlayer = gameEngine.getActivePlayer();
        ArrayList<Card> table = activePlayer.getTable();
        int cardPlacement = 1;
        for (Card i : table) {
            if(i instanceof CreatureCard){
                System.out.println(cardPlacement+" Card "+ ((CreatureCard) i).getHp());
            }
            else {
                System.out.println(cardPlacement+" Card ");
            }
            cardPlacement++;
        }

    }


    public void printGameOverMenu() {

    }

    public void printStartMenu() {

    }

}
