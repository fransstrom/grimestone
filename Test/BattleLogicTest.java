import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BattleLogicTest {

    @Spy
    CreatureCard card = new CreatureCard("Creature",2, CreatureCard.Type.FAIRY,1,2,2,3);

    @Spy
    CreatureCard card2 = new CreatureCard(20);

    @Mock
    IEffect effect;

    @Spy
    SpecialCreatureCard card3 = new SpecialCreatureCard("Dragon", 1, CreatureCard.Type.DRAGON, 1, 1,1,10, effect);

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


    @Nested
    @DisplayName("Card Vs Card")
    class CardVsCard {

        @Test
        void attackMoreThanDefence() {
        battleLogic.setAttackingCard(card);
        battleLogic.setDefendingCard(card2);
        assertEquals(20,card2.getHp());
        battleLogic.cardVsCard();
        assertEquals(19,card2.getHp());
        }

        @Test
        void attackLessThenDefence(){
            battleLogic.setAttackingCard(card2);
            battleLogic.setDefendingCard(card);

            assertEquals(3, card.getHp());
            battleLogic.cardVsCard();
            assertEquals(3, card.getHp());
        }

        @Test
        void attackSameAsDefence(){
            battleLogic.setAttackingCard(card2);
            battleLogic.setDefendingCard(card3);
            assertEquals(10, card3.getHp());
            battleLogic.cardVsCard();
            assertEquals(10, card3.getHp());
        }

        @Test
        void attackingCardIsSuperEffective(){
            battleLogic.setAttackingCard(card);
            battleLogic.setDefendingCard(card3);
            assertEquals(10, card3.getHp());
            battleLogic.cardVsCard();
            assertEquals(7, card3.getHp());
        }

    }


}