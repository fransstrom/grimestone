import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class BattleLogicTest {

    @Mock
    Card card;

    @Mock
    Player player;


    private BattleLogic battleLogic;

    @BeforeEach
    void setUp() {
        battleLogic = new BattleLogic();
        player = mock(Player.class);
        card = mock(CreatureCard.class);
    }

    @Test
    void cardVsPlayer() {
        battleLogic.setAttackingCard(card);
        battleLogic.setDefendingPlayer(player);
        //when(battleLogic.getDefendingPlayer().getHp()).thenReturn(10);
        System.out.println(player.getHp());

    }

    @Test
    void cardVsCard() {
    }

    @Test
    void attack() {
    }

}