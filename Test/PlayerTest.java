import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        player1 = new Player();
    }

    @Test
    void isAlive() {
        assertTrue( player1.isAlive() );
        player1.setHp( 0 );
        assertFalse( player1.isAlive() );
        player1.setHp( 5 );
        assertTrue( player1.isAlive() );
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
    }

    @Test
    void placeCardOnTable() {
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
        assertEquals( 0, player1.getDeck().size() );

        player1.drawCard();

        assertEquals( 0, player1.getDeck().size() );

        assertEquals( 0, player1.getHand().size() );

        assertFalse( player1.drawCard() );

    }

    @Test
    void drawInitialHand() {
    }

    @Test
    void generateDeck() {
        assertTrue( player1.getDeck().isEmpty() );
        player1.generateDeck();
        assertFalse( player1.getDeck().isEmpty() );
        assertEquals( 10, player1.getDeck().size() );
    }
}