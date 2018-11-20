import cards.Card;
import cards.CreatureCard;

public class BattleLogic {

   private Card attackingCard;
   private Card defendingCard;
   private Player defendingPlayer;

    public void cardVsPlayer() {
        defendingPlayer.setHp(defendingPlayer.getHp()-((CreatureCard)attackingCard).getAttack());
        ((CreatureCard) attackingCard).setActive(false);
        System.out.println("HIT! Defending player looses " + ((CreatureCard) attackingCard).getAttack() + " HP!");
    }

    public void cardVsCard() {
        if (attackingCard instanceof CreatureCard && defendingCard instanceof CreatureCard) {
            int attackingCardDamage = ((CreatureCard) attackingCard).getAttack();
            int defendingCardDefence = ((CreatureCard) defendingCard).getDefense();
            String superEffective="";

            if(((CreatureCard) attackingCard).isSuperEffective(((CreatureCard) defendingCard).getType())){
                attackingCardDamage +=2;
                superEffective="\033[0;93m The attack was SUPER EFFECTIVE!\033[0m";
            }

            int damageToBeDealt = attackingCardDamage - defendingCardDefence;
            if (damageToBeDealt>0) {
               ((CreatureCard) defendingCard).setHp(((CreatureCard) defendingCard).getHp()-damageToBeDealt);
                System.out.println("HIT! Defending card looses " + damageToBeDealt + " HP!"+ superEffective);
            } else {
                System.out.println("\033[0;93mMISS! Defending card was too strong!\033[0m" );
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

