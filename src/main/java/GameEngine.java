public class GameEngine {

    private Player player1;

    private Player player2;

    private boolean gameOver;

    private BattleLogic battleLogic;

    /*public GameEngine() {
        player1 = new Player();
        player2 = new Player();
        this.gameOver = false;
        battleLogic = new BattleLogic();
    }*/

    public GameEngine(Player p1, Player p2, BattleLogic battleLogic) {
        player1 = p1;
        player2 = p2;
        this.battleLogic = battleLogic;
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
}
