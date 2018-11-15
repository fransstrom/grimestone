import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    private Player player1;

    @Mock
    private Card card;
    @Mock
    private CreatureCard creatureCard;
    @Mock
    private SpecialCreatureCard specialCreatureCard;
    @Mock
    private MagicCard magicCard;

    @BeforeEach
    void setUp() {
        player1 = new Player();
    }

    @Test
    void isAlive() {
        assertTrue(player1.isAlive());
        player1.setHp(0);
        assertFalse(player1.isAlive());
        player1.setHp(5);
        assertTrue(player1.isAlive());

    }

    @Test
    void deckIsEmpty() {
        player1.getDeck().clear();
        assertTrue(player1.deckIsEmpty());
        player1.getDeck().add(card);
        assertFalse(player1.deckIsEmpty());
    }

    @Test
    void tableIsEmpty() {
        assertTrue(player1.tableIsEmpty());
        player1.getTable().add(card);
        assertFalse(player1.tableIsEmpty());
    }

    @Test
    void moveDeadCardToGraveyard() {
        assertTrue(player1.getGraveyard().isEmpty());
        player1.getTable().add(creatureCard);
        Mockito.when(creatureCard.getHp()).thenReturn(5);
        player1.moveDeadCardToGraveyard();
        assertTrue(player1.getGraveyard().isEmpty());
        Mockito.when(creatureCard.getHp()).thenReturn(0);
        player1.moveDeadCardToGraveyard();
        assertFalse(player1.getGraveyard().isEmpty());
        assertEquals(player1.getGraveyard().get(0), creatureCard);
    }

    @Test
    void noCardsLeftInDeckTest() {
        assertFalse(player1.noCardsLeftInDeck());
        player1.getDeck().clear();
        assertTrue(player1.noCardsLeftInDeck());
        player1.getDeck().add(card);
        assertFalse(player1.noCardsLeftInDeck());
    }


    @Nested
    @DisplayName("Place card on table tests")
    class placeCardOnTable {

        @Test
        void placeCardOnTableWhenHandIsNotEmpty() {
            player1.getHand().add(card);

            assertEquals(1, player1.getHand().size());
            assertTrue(player1.placeCardOnTable(1));
            assertEquals(1, player1.getTable().size());
            assertEquals(0, player1.getHand().size());
            assertFalse(player1.placeCardOnTable(1));
        }

        @Test
        void placeCardOnTableWhenIndexOutOfRange() {
            player1.getHand().add(card);

            assertEquals(1, player1.getHand().size());
            assertFalse(player1.placeCardOnTable(3));
            assertEquals(0, player1.getTable().size());
            assertEquals(1, player1.getHand().size());
            assertTrue(player1.placeCardOnTable(1));
            assertEquals(1, player1.getTable().size());
            assertEquals(0, player1.getHand().size());
            player1.getHand().add(card);
            assertFalse(player1.placeCardOnTable(-1));
        }

        @Test
        void placeCardOnTableWhenHandIsEmpty() {
            assertEquals(0, player1.getHand().size());
            assertFalse(player1.placeCardOnTable(0));
            assertEquals(0, player1.getTable().size());
            assertEquals(0, player1.getHand().size());
        }
    }

    @Test
    void drawCardWhenHandIsFull(){
        for (int i = 0; i < 8; i++) {
            player1.getHand().add(creatureCard);
        }
        assertEquals(8, player1.getHand().size());
        assertEquals(0, player1.getGraveyard().size());
        player1.drawCard();
        assertEquals(8, player1.getHand().size());
        assertEquals(1, player1.getGraveyard().size());
    }

    @Test
    void drawCardWhenDeckIsNotEmpty() {
        assertEquals(20, player1.getDeck().size());
        player1.drawCard();
        assertEquals(1, player1.getHand().size());
        assertEquals(19, player1.getDeck().size());
        assertTrue(player1.drawCard());
    }

    @Test
    void drawCardWhenDeckIsEmpty() {
        player1.getDeck().clear();
        assertEquals(0, player1.getDeck().size());
        assertEquals(0, player1.getHand().size());
        player1.drawCard();
        assertEquals(0, player1.getDeck().size());
        assertEquals(0, player1.getHand().size());
    }

    @Test
    void drawInitialHand() {
        assertEquals(20, player1.getDeck().size());
        assertEquals(0, player1.getHand().size());
        player1.drawInitialHand();
        assertEquals(5, player1.getHand().size());
        assertEquals(15, player1.getDeck().size());
    }

    @Test
    void overHeal(){
        player1.setHp(15);
        player1.heal(6);
        assertEquals(20, player1.getHp());
    }
    @Test
    void heal(){
        player1.setHp(10);
        player1.heal(5);
        assertEquals(15, player1.getHp());
    }

    @Test
    void generateDeck() {

        player1.getDeck().clear();
        assertTrue(player1.getDeck().isEmpty());
        player1.generateDeck();
        assertFalse(player1.getDeck().isEmpty());
        assertEquals(20, player1.getDeck().size());

        long creatureCount = player1.getDeck().stream()
                .filter(card -> card instanceof CreatureCard && !(card instanceof SpecialCreatureCard))
                .count();
        assertEquals(10, creatureCount);

        long specialCreatureCount = player1.getDeck().stream()
                .filter(card -> card instanceof SpecialCreatureCard)
                .count();
        assertEquals(5, specialCreatureCount);

        long magicCount = player1.getDeck().stream()
                .filter(card -> card instanceof MagicCard)
                .count();
        assertEquals(5, magicCount);
    }

    @Test
    void pickCardFromTableWithCardOnTable() {
        player1.getTable().add(card);
        assertEquals(card, player1.pickCardFromTable(1));
    }

    @Test
    void pickCardFromTableWithEmptyTable() {
        assertEquals(0, player1.getTable().size());
        assertNull(player1.pickCardFromTable(0));
    }

    @Test
    void hasActiveCardsOnTable() {
        player1.getTable().add(creatureCard);
        when(creatureCard.isActive()).thenReturn(true);
        assertTrue(player1.hasActiveCardsOnTable());
        when(creatureCard.isActive()).thenReturn(false);
        assertFalse(player1.hasActiveCardsOnTable());
    }

    @Test
    void setCardsOnTableToActive() {
        CreatureCard creatureCard = spy(CreatureCard.class);
        creatureCard.setActivationCountdown(1);
        player1.getTable().add(creatureCard);
        assertFalse(creatureCard.isActive());
        player1.setCardsOnTableToActive();
        assertTrue(creatureCard.isActive());

        creatureCard.setActivationCountdown(2);
        creatureCard.setActive(false);
        player1.setCardsOnTableToActive();
        assertFalse(creatureCard.isActive());
    }

    @Test
    void checkManaWithNotEnoughPlayerMana() {
        player1.setMana(5);
        assertFalse(player1.checkMana(7));
    }

    @Test
    void checkManaWithEnoughPlayerMana() {
        player1.setMana(10);
        assertTrue(player1.checkMana(3));
    }

    @Test
    void checkManaWithEqualPlayerAndCardMana() {
        player1.setMana(5);
        assertTrue(player1.checkMana(5));
    }

    @Test
    void increaseManaWhenManaIsNotMax() {
        assertEquals(0, player1.getMaxMana());
        player1.increaseMaxMana();
        assertEquals(1, player1.getMaxMana());
        player1.increaseMaxMana();
        assertEquals(2, player1.getMaxMana());
    }

    @Test
    void increaseManaWhenManaIsMax() {
        player1.setMaxMana(10);
        assertEquals(10, player1.getMaxMana());
        player1.increaseMaxMana();
        assertEquals(10, player1.getMaxMana());
    }

    @Test
    void refillManaWithHalfEmptyMana() {
        player1.setMaxMana(10);
        player1.setMana(5);
        assertEquals(5, player1.getMana());
        player1.refillMana();
        assertEquals(10, player1.getMana());
    }

    @Test
    void refillManaWithFullMana() {
        player1.setMaxMana(10);
        player1.setMana(10);
        assertEquals(10, player1.getMana());
        player1.refillMana();
        assertEquals(10, player1.getMana());
    }

    @Nested
    @DisplayName("PlayCard")
    class playCard{

        @BeforeEach
        void setUp(){
            player1.getHand().add(creatureCard);
            player1.getHand().add(specialCreatureCard);
            player1.getHand().add(magicCard);
        }

        @Test
        void creatureCard(){
            assertEquals(3, player1.getHand().size());
            assertEquals("PLAYED_CREATURECARD", player1.playCard(1));
            assertEquals(2, player1.getHand().size());
        }

        @Test
        void specialCreatureCard(){
            assertEquals(3, player1.getHand().size());
            assertEquals("PLAYED_CREATURECARD", player1.playCard(2));
            assertEquals(2, player1.getHand().size());
        }

        @Test
        void magicCard(){
            when(magicCard.trigger()).thenReturn("HEAL_PLAYER_5");
            assertEquals("HEAL_PLAYER_5", player1.playCard(3));
            verify(magicCard, Mockito.times(1)).trigger();
            assertEquals(2, player1.getHand().size());
        }

        @Test
        void faultyChoice(){
            assertEquals("FAULTY_CHOICE", player1.playCard(7));
            assertNotEquals("FAULTY_CHOICE", player1.playCard(3));
            assertEquals("FAULTY_CHOICE", player1.playCard(-3));
        }


    }

}