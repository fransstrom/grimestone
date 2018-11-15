import java.util.ArrayList;

public class BattleLogic {

   private Card attackingCard;
   private Card defendingCard;
   private Player defendingPlayer;

    public void cardVsPlayer() {
        int damageToBeDealt= ((CreatureCard)attackingCard).getAttack();
        defendingPlayer.setHp((defendingPlayer.getHp()-damageToBeDealt));
    }

    public void cardVsCard() {
        if (attackingCard instanceof CreatureCard && defendingCard instanceof CreatureCard) {
            int attackingCardDamage = ((CreatureCard) attackingCard).getAttack();
            int defendingCardDefence = ((CreatureCard) defendingCard).getDefense();

            if(((CreatureCard) attackingCard).isSuperEffective(((CreatureCard) defendingCard).getType())){
                attackingCardDamage +=2;
            }

            int damageToBeDealt = attackingCardDamage - defendingCardDefence;
            if (damageToBeDealt>0) {
               ((CreatureCard) defendingCard).setHp(((CreatureCard) defendingCard).getHp()-damageToBeDealt);
                System.out.println("HIT! Defending card looses " + damageToBeDealt + " HP!");
            } else {
                System.out.println("MISS! Defending card was too strong!");
            }
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

