import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameLoopTest {

    private GameLoop gameLoop;
    @Mock
    GameEngine mockGameEngine;
    @Mock
    InputProcessor mockInputProcessor;
    @Mock
    ArrayList<Card> mockList;


    @BeforeAll
    void setUp(){
        gameLoop = new GameLoop(mockGameEngine, mockInputProcessor);
    }

    @Nested
    @DisplayName("Game loop phase 1")
    class TestPhase1{

        @BeforeAll
        void setUp2(){
            mockList = new ArrayList<>();
        }

        @Test
        void pickCardFromHandToPutOnTable() {
            when(mockInputProcessor.getInputInt()).thenReturn(1);

        }

        @Test
        void skip(){
            when(mockInputProcessor.getInputInt()).thenReturn(2);

        }

    }


}