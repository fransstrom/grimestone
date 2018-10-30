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
        if (player1.getHp() < 1 || player1.getDeck().isEmpty() && player1.getHand().isEmpty() && player1.getTable().isEmpty()) {
           setGameOver(true);
        }
        if (player2.getHp() < 1 || player2.getDeck().isEmpty() && player2.getHand().isEmpty() && player2.getTable().isEmpty()) {
            setGameOver(true);
        }
        return gameOver;
    }


    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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
        randomGenerateFirstActivePlayer();
        if (player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }

    private void randomGenerateFirstActivePlayer() {
        if (!player1.isActive() && !player2.isActive()) {
            int lottery = RandomNumberGenerator.roll();
            if ((lottery % 2) == 0) {
                player1.setActive(true);
            } else if ((lottery % 2) != 0) {
                player2.setActive(true);
            }
        }
    }
}
