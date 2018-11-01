import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;


import static org.junit.jupiter.api.Assertions.*;

class BattleLogicTest {

    @Spy
    CreatureCard card = new CreatureCard(10);

    @Spy
    CreatureCard card2 = new CreatureCard(20);

    @Spy
    Player player = new Player();

    private BattleLogic battleLogic;

    @BeforeEach
    void setUp() {
        battleLogic = new BattleLogic();
    }

    @Test
    void cardVsPlayer() {
        player.setHp(10);
        battleLogic.cardVsPlayer(card, player);
        assertTrue(player.getHp()<10);
    }

    @Test
    void cardVsCard() {

        assertTrue(card.getHp()==10);
        assertTrue(card2.getHp()==20);
        battleLogic.setAttackingCard(card);
        battleLogic.setDefendingCard(card2);
        System.out.println("AC Before HIT " + ((CreatureCard)battleLogic.getAttackingCard()).getHp());
        System.out.println("DC Before HIT " + ((CreatureCard)battleLogic.getDefendingCard()).getHp());

        battleLogic.cardVsCard();
        System.out.println("AC After HIT " + ((CreatureCard)battleLogic.getAttackingCard()).getHp());
        System.out.println("DC After HIT " + ((CreatureCard)battleLogic.getDefendingCard()).getHp());

        assertTrue(card.getHp()<10 || card2.getHp()<20);

    }

    @Test
    void attack() {
    }

}