public class GameEngine {

    private Player player1;

    private Player player2;

    private boolean gameOver;

    private BattleLogic battleLogic;


    public GameEngine(Player p1, Player p2, BattleLogic battleLogic) {
        player1 = p1;
        player2 = p2;
        this.battleLogic = battleLogic;
        this.gameOver = false;
    }

    public boolean isGameOver() {
        if (player1.getHp() < 1 || player1.noCardsLeft()) {
            System.out.println("Player1 lost");
            setGameOver(true);
        }
        if (player2.getHp() < 1 || player2.noCardsLeft()) {
            System.out.println("Player2 lost");
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

    public void attack() {
        if(battleLogic.getDefendingPlayer().getTable().isEmpty()){
            battleLogic.cardVsPlayer();
        }else {
            battleLogic.cardVsCard();
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }


    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public BattleLogic getBattleLogic() {
        return battleLogic;
    }

    public void setBattleLogic(BattleLogic battleLogic) {
        this.battleLogic = battleLogic;
    }

    public Player getActivePlayer() {
        randomGenerateFirstActivePlayer();
        if (player1.isActive()) {
            return player1;
        } else {
            return player2;
        }
    }

    private void switchActivePlayer(){
        player1.setActive(!player1.isActive());
        player2.setActive(!player2.isActive());
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

