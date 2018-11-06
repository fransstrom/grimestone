import java.util.ArrayList;

public class BattleLogic {

   private Card attackingCard;
   private Card defendingCard;
   private Player defendingPlayer;

    public void cardVsPlayer() {
        int damage = RandomNumberGenerator.roll();
        defendingPlayer.setHp(defendingPlayer.getHp()-damage);
    }

    public void cardVsCard() {
        if (attackingCard instanceof CreatureCard && defendingCard instanceof CreatureCard) {
            int attackingCardDamage = RandomNumberGenerator.roll();
            int defendingCardDamage = RandomNumberGenerator.roll();
            int damageToBeDealt;
            while (attackingCardDamage == defendingCardDamage) {
                attackingCardDamage = RandomNumberGenerator.roll();
                defendingCardDamage = RandomNumberGenerator.roll();
            }
            damageToBeDealt = Math.abs(attackingCardDamage - defendingCardDamage);
            if (attackingCardDamage > defendingCardDamage) {
                ((CreatureCard) defendingCard).setHp(((CreatureCard) defendingCard).getHp()-damageToBeDealt);
                System.out.println("HIT! Attacking card looses " + damageToBeDealt + " HP!");
            } else {
                ((CreatureCard) attackingCard).setHp(((CreatureCard) attackingCard).getHp()-damageToBeDealt);
                System.out.println("MISS! Defending card looses " + damageToBeDealt + " HP!");
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

