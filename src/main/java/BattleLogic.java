import cards.Card;
import cards.CreatureCard;

public class BattleLogic {

   private Card attackingCard;
   private Card defendingCard;
   private Player defendingPlayer;

    public void cardVsPlayer() {
        defendingPlayer.setHp(defendingPlayer.getHp()-((CreatureCard)attackingCard).getAttack());
        ((CreatureCard) attackingCard).setActive(false);
    }

    public void cardVsCard() {
        if (attackingCard instanceof CreatureCard && defendingCard instanceof CreatureCard) {
            int attackingCardDamage = ((CreatureCard) attackingCard).getAttack();
            int defendingCardDefence = ((CreatureCard) defendingCard).getDefense();
            String superEffective="";

            if(((CreatureCard) attackingCard).isSuperEffective(((CreatureCard) defendingCard).getType())){
                attackingCardDamage +=2;
                superEffective=" The attack was SUPER EFFECTIVE!";
            }

            int damageToBeDealt = attackingCardDamage - defendingCardDefence;
            if (damageToBeDealt>0) {
               ((CreatureCard) defendingCard).setHp(((CreatureCard) defendingCard).getHp()-damageToBeDealt);
                System.out.println("HIT! Defending card looses " + damageToBeDealt + " HP!"+ superEffective);
            } else {
                System.out.println("MISS! Defending card was too strong!");
            }
            ((CreatureCard) attackingCard).setActive(false);
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

