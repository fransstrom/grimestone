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
            player.setHp(20);
            battleLogic.setAttackingCard(card);
            card.setActive(true);
            assertEquals(20, player.getHp());
            battleLogic.cardVsPlayer();
            assertEquals(18, player.getHp());
            assertFalse(card.isActive());
    }



    @Nested
    @DisplayName("Card Vs Card")
    class CardVsCard {

        @Test
        void attackMoreThanDefence() {
        battleLogic.setAttackingCard(card);
        card.setActive(true);
        battleLogic.setDefendingCard(card2);
        assertEquals(20,card2.getHp());
        battleLogic.cardVsCard();
        assertEquals(19,card2.getHp());
        assertFalse(card.isActive());
        }

        @Test
        void attackLessThanDefence(){
            battleLogic.setAttackingCard(card2);
            battleLogic.setDefendingCard(card);
            card2.setActive(true);
            assertEquals(3, card.getHp());
            battleLogic.cardVsCard();
            assertEquals(3, card.getHp());
            assertFalse(card2.isActive());
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