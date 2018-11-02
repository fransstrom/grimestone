import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    @BeforeEach
    void setUp(){
        gameLoop = new GameLoop(mockGameEngine);
        gameLoop.setInputProcessor(mockInputProcessor);
    }

    @Nested
    @DisplayName("Game loop phase 1")
    class TestPhase1{

        @BeforeEach
        void setUp2(){
            mockList = new ArrayList<>();
        }

        @Test
        void pickCardFromHandToPutOnTable() {


        }

        @Test
        void skip(){


        }

    }


}