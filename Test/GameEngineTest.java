import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEngineTest {

    private GameEngine gameEngine;
    @Mock
    Player player1;
    @Mock
    Player player2;
    @Spy
    BattleLogic battleLogic = new BattleLogic();
    @Spy
    ArrayList<Card> mocklist;
    @Mock
    CreatureCard mockCreatureCard;
    @Mock
    InputProcessor inputProcessor;

    @BeforeEach
    void SetUp(){
        gameEngine = new GameEngine(player1, player2, battleLogic, inputProcessor);

    }

    @Test
    void setUpNewGame() {
        gameEngine.setUpNewGame();
        verify(player1, times(1)).drawInitialHand();
        verify(player2, times(1)).drawInitialHand();
    }
    
    @Test
    void attackWithCardsOnTable(){
        GameEngine gameEngineSpy = spy(gameEngine);
        when(player1.getTable()).thenReturn(mocklist);
        when(battleLogic.getDefendingPlayer()).thenReturn(player1);
        when(battleLogic.getDefendingPlayer().getTable().isEmpty()).thenReturn(true);
        doNothing().when(battleLogic).cardVsPlayer();
        gameEngineSpy.attack();
        verify(battleLogic, times(1)).cardVsPlayer();
        verify(battleLogic, times(0)).cardVsCard();
    }

    @Test
    void attackWithoutCardsOnTable(){
        GameEngine gameEngineSpy = spy(gameEngine);
        when(player1.getTable()).thenReturn(mocklist);
        when(battleLogic.getDefendingPlayer()).thenReturn(player1);
        when(battleLogic.getDefendingPlayer().getTable().isEmpty()).thenReturn(false);
        doNothing().when(battleLogic).cardVsCard();
        gameEngineSpy.attack();
        verify(battleLogic, times(0)).cardVsPlayer();
        verify(battleLogic, times(1)).cardVsCard();
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


        when(player1.noCardsLeft()).thenReturn(false);
        when(player2.noCardsLeft()).thenReturn(false);

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
        when(player1.noCardsLeft()).thenReturn(false);
        when(player2.noCardsLeft()).thenReturn(false);
        when(player1.getHp()).thenReturn(2);
        when(player2.getHp()).thenReturn(10);
        assertFalse(gameEngine.isGameOver());

        when(player1.noCardsLeft()).thenReturn(true);
        when(player2.noCardsLeft()).thenReturn(false);
        when(player1.getHp()).thenReturn(2);
        when(player2.getHp()).thenReturn(10);
        assertTrue(gameEngine.isGameOver());
    }

    @Test
    void isGameOver() {
    }



    @Test
    void BreakCard(){

    }


    @Nested
    @DisplayName("Random pick first active player")
    class randomGenActivePlayer {


        GameEngine gameEngine;

        @Mock
        BattleLogic battleLogic;

        @BeforeEach
        void setUp() {
            player1 = new Player();
            player2 = new Player();
            player1.setHp(100);
            player2.setHp(200);
            gameEngine = new GameEngine(player1, player2, battleLogic, inputProcessor);
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