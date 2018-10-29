import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    private Player player1;

    @Mock
    private Card card;
    @Mock
    private CreatureCard creatureCard;

    @BeforeEach
    void setUp() {
        player1=new Player();
    }

    @Test
    void isAlive() {
        assertTrue( player1.isAlive() );
        player1.setHp( 0 );
        assertFalse( player1.isAlive());
        player1.setHp( 5 );
        assertTrue( player1.isAlive() );
    }

    @Test
    void deckIsEmpty() {
    }

    @Test
    void tableIsEmpty() {
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
    void placeCardOnTable() {
    }

    @Test
    void drawCard() {
    }

    @Test
    void drawInitialHand() {
    }

    @Test
    void generateDeck() {
    }
}