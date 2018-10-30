import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEngineTest{

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
    void setUpNewGame(){
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

}