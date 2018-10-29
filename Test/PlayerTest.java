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

    @BeforeEach
    void setUp() {
        player1=new Player();
    }

    @Test
    void isAlive() {
    }

    @Test
    void deckIsEmpty() {
    }

    @Test
    void tableIsEmpty() {
    }

    @Test
    void moveDeadCardToGraveyard() {
    }

    @Test
    void placeCardOnTable() {
    }

    @Test
    void drawCard() {
        for (int i = 0; i < 10; i++) {
            player1.getDeck().add(card);
        }
        assertEquals(10, player1.getDeck().size());

        player1.drawCard();

        assertEquals(1, player1.getHand().size());
        assertEquals(9, player1.getDeck().size());
    }

    @Test
    void drawInitialHand() {
    }

    @Test
    void generateDeck() {
    }
}