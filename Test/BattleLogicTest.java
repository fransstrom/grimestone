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
        battleLogic.cardVsCard();
        assertTrue(card.getHp()<10 || card2.getHp()<20);
    }

    @Test
    void attack() {
    }

}