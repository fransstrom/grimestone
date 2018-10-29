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
    }

    @Test
    void deckIsEmpty() {
    }

    @Test
    void tableIsEmpty() {
        assertTrue(player1.tableIsEmpty());
        player1.getTable().add(card);
        assertFalse(player1.tableIsEmpty());
    }

    @Test
    void moveDeadCardToGraveyard() {
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