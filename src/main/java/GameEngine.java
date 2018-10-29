public class GameEngine {
    private Player player1;
    private Player player2;

    private boolean gameOver;

    public GameEngine() {
        player1 = new Player();
        player2 = new Player();
        this.gameOver = false;
    }

    public GameEngine(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
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
        player1.drawInitialHand();
        player2.drawInitialHand();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getActivePlayer() {
        if (player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }
}
