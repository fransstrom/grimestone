import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player1;

    @Mock
    ArrayList<Card> handMock;
    @Mock
    ArrayList<Card> deckMock;
    @Mock
    ArrayList<Card> graveyardMock;
    @Mock
    ArrayList<Card> tableMock;

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
    }

    @Test
    void drawInitialHand() {
    }

    @Test
    void generateDeck() {
    }
}