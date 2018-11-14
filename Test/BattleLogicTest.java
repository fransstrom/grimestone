import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BattleLogicTest {

    @Spy
    CreatureCard card = new CreatureCard("Creature",2, CreatureCard.Type.NEUTRAL,1,2,1,3);

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
        battleLogic.setDefendingPlayer(player);
        battleLogic.cardVsPlayer();
        assertTrue(player.getHp()<20);
    }

    @Test
    void cardVsCard() {
        battleLogic.setAttackingCard(card2);
        battleLogic.setDefendingCard(card);
        assertEquals(3,card.getHp());
        cardVsCard();
        assertEquals(2,card.getHp());
    }

}