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
    @Mock
    ArrayList<Card> mocklist;


    @BeforeEach
    void SetUp(){
        gameEngine = new GameEngine(player1, player2, battleLogic);

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