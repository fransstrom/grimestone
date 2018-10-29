public class GameEngine {
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
        Player player1 = new Player();
        Player player2 = new Player();
        player1.drawInitialHand();
        player2.drawInitialHand();

    }

}
