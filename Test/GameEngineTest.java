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
    @Spy
    Player player1;
    @Mock
    Player player2;
    @Spy
    BattleLogic battleLogic;
    @Spy
    ArrayList<Card> mocklist;
    @Mock
    CreatureCard mockCreatureCard;

    @BeforeEach
    void SetUp(){
        gameEngine = new GameEngine(player1, player2, battleLogic);
    }

    @Test
    void startGame() {
        when(player1.getHp()).thenReturn(0);
        gameEngine.startGame();
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
        when(battleLogic.getDefendingPlayer().getTable().isEmpty()).thenReturn(true);
        gameEngineSpy.attack();
        verify(battleLogic, times(1)).cardVsPlayer();
        verify(battleLogic, times(0)).cardVsCard();
    }

    @Test
    void getActivePlayer() {
        when(player1.isActive()).thenReturn(true);
        assertEquals(player1, gameEngine.getActivePlayer());
        when(player1.isActive()).thenReturn(false);
        assertEquals(player2, gameEngine.getActivePlayer());
    }

    @Test
    void gameOverByHP() {


        when(player1.noCardsLeftInDeck()).thenReturn(false);
        when(player2.noCardsLeftInDeck()).thenReturn(false);

        when(player1.getHp()).thenReturn(1);
        when(player2.getHp()).thenReturn(9);
        assertFalse(gameEngine.isGameOver());

        when(player1.getHp()).thenReturn(12);
        when(player2.getHp()).thenReturn(-1);
        assertTrue(gameEngine.isGameOver());

        when(player1.getHp()).thenReturn(-1);
        assertTrue(gameEngine.isGameOver());


    }

    @Test
    void gameOverByRunningOutOfCards() {
        when(player1.noCardsLeftInDeck()).thenReturn(false);
        when(player2.noCardsLeftInDeck()).thenReturn(false);
        when(player1.getHp()).thenReturn(2);
        when(player2.getHp()).thenReturn(10);
        assertFalse(gameEngine.isGameOver());

        when(player1.noCardsLeftInDeck()).thenReturn(true);
        assertTrue(gameEngine.isGameOver());

        when(player1.noCardsLeftInDeck()).thenReturn(false);
        when(player2.noCardsLeftInDeck()).thenReturn(true);
        assertTrue(gameEngine.isGameOver());
    }

    @Test
    void getInactivePlayerWhenPlayerOneIsInactive(){
        when(player1.isActive()).thenReturn(false);
        assertEquals(player1, gameEngine.getInactivePlayer());
    }

    @Test
    void getInactivePlayerWhenPlayerTwoIsInactive(){
        when(player1.isActive()).thenReturn(true);
        assertEquals(player2, gameEngine.getInactivePlayer());
    }

    @Test
    void switchActivePlayer(){
        assertFalse(player1.isActive());
        gameEngine.switchActivePlayer();
        assertTrue(player1.isActive());
    }

    @Test
    void resolveEffectHealPlayer(){
        player1.setActive(true);
        player1.setHp(5);
        gameEngine.resolveEffect("HEAL_PLAYER_5");
        assertEquals(10, player1.getHp());
    }

    @Test
    void resolveEffectNegativeHealAmount(){
        player1.setActive(true);
        player1.setHp(5);
        gameEngine.resolveEffect("HEAL_PLAYER_-5");
        assertEquals(5, player1.getHp());
    }

    @Test
    void isGameOver() {
    }



    @Test
    void BreakCard(){

    }

}