import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;


import static org.junit.jupiter.api.Assertions.*;

class BattleLogicTest {

    @Spy
    Card card = new CreatureCard(10);

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
    }

    @Test
    void attack() {
    }

}