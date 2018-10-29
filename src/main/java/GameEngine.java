public class GameEngine {
    private Player player1;
    private Player player2;

    private boolean gameOver;

    public GameEngine() {
        this.gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    public void gameOver() {

    }

    public void playerChoice() {

    }

    public void setUpNewGame() {
        player1 = new Player();
        player2 = new Player();
        player1.drawInitialHand();
        player2.drawInitialHand();

    }

}
