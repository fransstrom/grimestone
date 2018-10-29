import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    private Player player1;

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
        player1.setGraveyard(graveyardMock);
        player1.setDeck(deckMock);
        player1.setHand(handMock);
        player1.setTable(tableMock);
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