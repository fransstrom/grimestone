import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        battleLogic.setDefendingPlayer(player);
        battleLogic.cardVsPlayer();
        assertTrue(player.getHp()<10);
    }

    @Test
    void cardVsCard() {
    }

}