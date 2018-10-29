import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    private Player player1;

    @Mock
    private Card card;
    @Mock
    private CreatureCard creatureCard;

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
        assertTrue( player1.deckIsEmpty() );
        player1.getDeck().add( card );
        assertFalse( player1.deckIsEmpty() );
    }

    @Test
    void tableIsEmpty() {
        assertTrue( player1.tableIsEmpty() );
        player1.getTable().add( card );
        assertFalse( player1.tableIsEmpty() );
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

    @Nested
    @DisplayName("Place card on table tests")
    class placeCardOnTable {
        
        @Test
        void placeCardOnTableWhenHandIsNotEmpty() {
            player1.getHand().add(card);

            assertEquals(1, player1.getHand().size());
            assertTrue(player1.placeCardOnTable(0));
            assertEquals(1, player1.getTable().size());
            assertEquals(0, player1.getHand().size());
            assertFalse(player1.placeCardOnTable(0));
        }

        @Test
        void placeCardOnTableWhenIndexOutOfRange() {
            player1.getHand().add(card);

            assertEquals(1, player1.getHand().size());
            assertFalse(player1.placeCardOnTable(3));
            assertEquals(0, player1.getTable().size());
            assertEquals(1, player1.getHand().size());
            assertTrue(player1.placeCardOnTable(0));
            assertEquals(1, player1.getTable().size());
            assertEquals(0, player1.getHand().size());
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
    void drawCardWhenDeckIsNotEmpty() {
        for (int i = 0; i < 10; i++) {
            player1.getDeck().add( card );
        }
        assertEquals( 10, player1.getDeck().size() );

        player1.drawCard();

        assertEquals( 1, player1.getHand().size() );

        assertEquals( 9, player1.getDeck().size() );

        assertTrue( player1.drawCard() );
    }

    @Test
    void drawCardWhenDeckIsEmpty() {

        assertEquals(0, player1.getDeck().size());


        player1.drawCard();

        assertEquals( 0, player1.getDeck().size() );

        assertEquals( 0, player1.getHand().size() );

        assertFalse( player1.drawCard() );

    }

    @Test
    void drawInitialHand() {
        for (int i = 0; i < 10; i++) {
            player1.getDeck().add(card);
        }
        assertEquals(10, player1.getDeck().size());

        assertEquals(0, player1.getHand().size());

        player1.drawInitialHand();

        assertEquals(5, player1.getHand().size());
        
        assertEquals(5, player1.getDeck().size());
    }

    @Test
    void generateDeck() {
        assertTrue( player1.getDeck().isEmpty() );
        player1.generateDeck();
        assertFalse( player1.getDeck().isEmpty() );
        assertEquals( 10, player1.getDeck().size() );
    }
}