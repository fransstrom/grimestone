import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Nested
    @DisplayName("Random pick first active player")
    class randomGenActivePlayer {
        Player player1;
        Player player2;

        GameEngine gameEngine;

        @BeforeEach
        void setUp() {
            player1 = new Player();
            player2 = new Player();
            player1.setHp(100);
            player2.setHp(200);
            gameEngine = new GameEngine(player1, player2);
        }


        @RepeatedTest(100)
        void getFirstActivePlayer() {
            System.out.println(gameEngine.getActivePlayer().getHp());
            assertNotNull(gameEngine.getActivePlayer());
        }
    }
}