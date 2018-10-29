public class GUI {

    private GameEngine gameEngine;

    public GUI(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void render() {
    }

    public void printPlayerHP() {
        System.out.println("Player 1 hp: " + gameEngine.getPlayer1().getHp());
        System.out.println("Player 2 hp: " + gameEngine.getPlayer2().getHp());
    }

    public void printCardsInHand() {

    }

    public void printRemainingCards() {

    }

    public void printCardsOnTable() {

    }

    public void printGameOverMenu() {

    }

    public void printStartMenu() {

    }

}
