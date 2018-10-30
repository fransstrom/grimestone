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
        System.out.println("You have "+gameEngine.getActivePlayer().getDeck().size() + " cards remaining in deck");
    }

    public void printCardsOnTable() {

    }

    public void printGameOverMenu() {

    }

    public void printStartMenu() {

    }

}
