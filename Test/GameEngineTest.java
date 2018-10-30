import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEngineTest {

    GameEngine gameEngine;
    @Mock
    Player player1;
    @Mock
    Player player2;


    @BeforeEach
    void SetUp() {
        gameEngine = new GameEngine(player1, player2);
    }

    @Test
    void setUpNewGame() {
        gameEngine.setUpNewGame();
        verify(player1, times(1)).drawInitialHand();
        verify(player2, times(1)).drawInitialHand();
    }

    @Test
    void getActivePlayer() {
        when(player1.isActive()).thenReturn(true);
        assertEquals(player1, gameEngine.getActivePlayer());
        when(player1.isActive()).thenReturn(false);
        assertEquals(player2, gameEngine.getActivePlayer());
    }

    @Test
    public void gameOverByHP() {
        ArrayList<Card> deckTest = new ArrayList<>();
        ArrayList<Card> emptyListOfCards = new ArrayList<>();
        deckTest.add(new CreatureCard());
        deckTest.add(new CreatureCard());
        deckTest.add(new CreatureCard());

        when(player1.getDeck()).thenReturn(deckTest);
        when(player2.getDeck()).thenReturn(deckTest);

        when(player1.getHp()).thenReturn(1);
        when(player2.getHp()).thenReturn(9);
        assertFalse(gameEngine.isGameOver());

        when(player1.getHp()).thenReturn(12);
        when(player2.getHp()).thenReturn(-1);
        assertTrue(gameEngine.isGameOver());

        when(player1.getHp()).thenReturn(-1);
        when(player2.getHp()).thenReturn(11);
        assertTrue(gameEngine.isGameOver());


    }

    @Test
    void gameOverByRunningOutOfCards() {
        ArrayList<Card> deckTest = new ArrayList<>();
        ArrayList<Card> emptyListOfCards = new ArrayList<>();
        deckTest.add(new CreatureCard());
        deckTest.add(new CreatureCard());
        deckTest.add(new CreatureCard());

        when(player1.getDeck()).thenReturn(deckTest);
        when(player2.getDeck()).thenReturn(deckTest);
        when(player1.getHp()).thenReturn(2);
        when(player2.getHp()).thenReturn(10);
        assertFalse(gameEngine.isGameOver());

        when(player1.getDeck()).thenReturn(emptyListOfCards);
        when(player2.getDeck()).thenReturn(deckTest);
        when(player1.getHp()).thenReturn(2);
        when(player2.getHp()).thenReturn(10);
        assertTrue(gameEngine.isGameOver());
    }


    @Nested
    @DisplayName("Random pick first active player")
    class randomGenActivePlayer {


        GameEngine gameEngine;

        @BeforeEach
        void setUp() {
            player1 = new Player();
            player2 = new Player();
            player1.setHp(100);
            player2.setHp(200);
            gameEngine = new GameEngine(player1, player2);
            assertFalse(player1.isActive());
            assertFalse(player2.isActive());
        }


        @RepeatedTest(100)
        void getFirstActivePlayer(RepetitionInfo repetitionInfo) {
            assertNotNull(gameEngine.getActivePlayer());
            System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition() + "\n" + gameEngine.getActivePlayer().getHp());
            assertEquals(100, repetitionInfo.getTotalRepetitions());
        }
    }
}