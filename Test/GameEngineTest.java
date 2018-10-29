import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEngineTest{

    GameEngine gameEngine;
    @Mock
    Player player1;
    @Mock
    Player player2;

    @BeforeEach
    void SetUp(){
        gameEngine = new GameEngine(player1, player2);
    }

    @Test
    void setUpNewGame(){
        gameEngine.setUpNewGame();
        verify(player1, times(1)).drawInitialHand();
        verify(player2, times(1)).drawInitialHand();
    }

}