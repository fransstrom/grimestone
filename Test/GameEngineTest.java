import cards.Card;
import cards.CreatureCard;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEngineTest {

    private GameEngine gameEngine;
    @Spy
    Player player1;
    @Spy
    Player player2;
    @Spy
    BattleLogic battleLogic;
    @Spy
    ArrayList<Card> mocklist;
    @Spy
    CreatureCard spyCreatureCard;
    @Mock
    InputProcessor inputProcessor;

    @BeforeEach
    void SetUp() {
        gameEngine = new GameEngine(player1, player2, battleLogic, inputProcessor);
    }

    @Test
    void startGame() throws SQLException, ClassNotFoundException {
        player1.setHp(0);
        when(inputProcessor.nextLine()).thenReturn("test");
        gameEngine.startGame();
        verify(player1, times(1)).gameSetUp();
        verify(player2, times(1)).gameSetUp();
    }

    @Test
    void attackWithCardsOnTable() {
        mocklist.add(spyCreatureCard);
        player1.setTable(mocklist);
        when(battleLogic.getDefendingPlayer()).thenReturn(player1);
        doNothing().when(battleLogic).cardVsCard();
        when(inputProcessor.nextInt()).thenReturn(1);
        gameEngine.attack();
        verify(battleLogic, times(0)).cardVsPlayer();
        verify(battleLogic, times(1)).cardVsCard();
    }

    @Test
    void attackWithoutCardsOnTable() {
        when(player1.getTable()).thenReturn(mocklist);
        when(battleLogic.getDefendingPlayer()).thenReturn(player1);
        when(battleLogic.getDefendingPlayer().getTable().isEmpty()).thenReturn(true);
        doNothing().when(battleLogic).cardVsPlayer();
        gameEngine.attack();
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

        assertFalse(gameEngine.isGameOver());
        player2.setHp(-1);
        assertTrue(gameEngine.isGameOver());
        player2.setHp(1);
        assertFalse(gameEngine.isGameOver());
    }

    @Test
    void gameOverByRunningOutOfCards() {
        when(player1.noCardsLeftInDeck()).thenReturn(false);
        when(player2.noCardsLeftInDeck()).thenReturn(false);
        assertFalse(gameEngine.isGameOver());

        when(player1.noCardsLeftInDeck()).thenReturn(true);
        assertTrue(gameEngine.isGameOver());

        when(player1.noCardsLeftInDeck()).thenReturn(false);
        when(player2.noCardsLeftInDeck()).thenReturn(true);
        assertTrue(gameEngine.isGameOver());
    }

    @Test
    void getInactivePlayerWhenPlayerOneIsInactive() {
        when(player1.isActive()).thenReturn(false);
        assertEquals(player1, gameEngine.getInactivePlayer());
    }

    @Test
    void getInactivePlayerWhenPlayerTwoIsInactive() {
        when(player1.isActive()).thenReturn(true);
        assertEquals(player2, gameEngine.getInactivePlayer());
    }

    @Test
    void switchActivePlayer() {
        assertFalse(player1.isActive());
        gameEngine.switchActivePlayer();
        assertTrue(player1.isActive());
    }

    @Test
    void resolveEffectHealPlayer() {
        player1.setActive(true);
        player1.setHp(5);
        gameEngine.resolveEffect("HEAL_PLAYER_5");
        assertEquals(10, player1.getHp());
    }

    @Test
    void resolveEffectNegativeHealAmount() {
        player1.setActive(true);
        player1.setHp(5);
        gameEngine.resolveEffect("HEAL_PLAYER_-5");
        assertEquals(10, player1.getHp());
    }

    @Test
    void resolveSetCreatureToActive() {
        player1.setActive(true);
        spyCreatureCard.setActive(false);
        player1.getTable().add(spyCreatureCard);
        gameEngine.resolveEffect("RUSH_TRUE");
        assertTrue(spyCreatureCard.isActive());
    }

    @Test
    void resolvePlayerAttackEffect() {
        player1.setActive(true);
        player2.setHp(10);
        player1.getTable().add(spyCreatureCard);
        gameEngine.resolveEffect("ATTACK_PLAYER_5");
        assertEquals(5,player2.getHp());
    }

    @Test
    void resolvePlayerEffectAttackNegativeDamage() {
        player1.setActive(true);
        player2.setHp(10);
        player1.getTable().add(spyCreatureCard);
        gameEngine.resolveEffect("ATTACK_PLAYER_-5");
        assertEquals(5,player2.getHp());
    }

    @Test
    void isGameOver() {
    }


    @Test
    void BreakCard() {

    }

}