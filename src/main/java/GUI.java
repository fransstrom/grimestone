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
        ArrayList<Card> table = gameEngine.getActivePlayer().getTable();
        int cardPlacement = 1;
        for (Card i : table) {
            if(i instanceof CreatureCard){
                System.out.println("Card "+cardPlacement+"HP: "+ ((CreatureCard) i).getHp());
            }
            else {
                System.out.println("Card "+cardPlacement+"HP: n/a");
            }
            cardPlacement++;
        }

    }


    public void printGameOverMenu() {

    }

    public void printStartMenu() {

    }

}
