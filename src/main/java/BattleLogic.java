import java.util.ArrayList;

public class BattleLogic {

   private Card attackingCard;
   private Card defendingCard;
   private Player defendingPlayer;

    public BattleLogic() {

    }


    public void cardVsPlayer() {
        int damage = RandomNumberGenerator.roll();
        defendingPlayer.setHp(defendingPlayer.getHp()-damage);
    }

    public void cardVsCard() {

    }

    public void attack() {
        if(defendingPlayer.tableIsEmpty()){
            cardVsPlayer();
        }else {
            cardVsCard();
        }
    }

    public Card getAttackingCard() {
        return attackingCard;
    }

    public void setAttackingCard(Card attackingCard) {
        this.attackingCard = attackingCard;
    }

    public Card getDefendingCard() {
        return defendingCard;
    }

    public void setDefendingCard(Card defendingCard) {
        this.defendingCard = defendingCard;
    }

    public Player getDefendingPlayer() {
        return defendingPlayer;
    }

    public void setDefendingPlayer(Player defendingPlayer) {
        this.defendingPlayer = defendingPlayer;
    }
}

