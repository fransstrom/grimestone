import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    }
}