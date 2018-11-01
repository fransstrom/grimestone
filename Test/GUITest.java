import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GUITest {

    private GUI gui;

    @Mock
    private GameEngine gameEngine;
    @Mock
    private Player mockPlayer;
    @Mock
    private Player mockPlayer2;
    @Mock
    private CreatureCard creatureCard;
    @Mock
    private CreatureCard creatureCardInactive;

    @Spy
    ArrayList<Card> table;
    @Spy
    ArrayList<Card> table2;
    @Spy
    ArrayList<Card> deck;
    @Spy
    ArrayList<Card> deck2;
    @Spy
    ArrayList<Card> graveyard;
    @Spy
    ArrayList<Card> graveyard2;
    @Spy
    ArrayList<Card> hand;
    @Spy
    ArrayList<Card> hand2;

    @BeforeEach
    void setUp() {
        gui = new GUI(gameEngine);
        table.add(creatureCard);
        table.add(creatureCard);

        table2.add(creatureCard);

        deck.add(creatureCard);

        deck2.add(creatureCard);

        graveyard.add(creatureCard);

        graveyard2.add(creatureCard);

        hand.add(creatureCard);
        hand.add(creatureCard);
        hand.add(creatureCard);
        hand.add(creatureCard);

        hand2.add(creatureCard);
        hand2.add(creatureCard);
        hand2.add(creatureCard);
        hand2.add(creatureCard);
        hand2.add(creatureCard);
    }

    @Test
    void render() {
        when(creatureCard.getHp()).thenReturn(5).thenReturn(3).thenReturn(7).thenReturn(1).thenReturn(4).thenReturn(2);

        //Created the Inactive player
        when(gameEngine.getInactivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(hand);
        when(mockPlayer.getTable()).thenReturn(table);
        when(mockPlayer.getHp()).thenReturn(10);
        when(mockPlayer.getDeck()).thenReturn(deck);
        when(mockPlayer.getGraveyard()).thenReturn(graveyard);

        //Created the active player
        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer2);
        when(mockPlayer2.isActive()).thenReturn(true);
        when(mockPlayer2.getHand()).thenReturn(hand2);
        when(mockPlayer2.getTable()).thenReturn(table2);
        when(mockPlayer2.getHp()).thenReturn(2);
        when(mockPlayer2.getDeck()).thenReturn(deck2);
        when(mockPlayer2.getGraveyard()).thenReturn(graveyard2);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        gui.printPlayerHud(gameEngine.getInactivePlayer());
        System.out.printf("\n");
        gui.printPlayerCardsInHand(gameEngine.getInactivePlayer());
        System.out.printf("\n\n");
        gui.printCardsOnTable();
        System.out.printf("\n\n");
        gui.printPlayerCardsInHand(gameEngine.getActivePlayer());
        System.out.printf("\n\n");
        gui.printPlayerHud(gameEngine.getActivePlayer());
        System.out.printf("\n------------------------------------------------------------------------------------------------------------------------------");
    }

    @Test
    void printPlayerHP() {
        when(mockPlayer.getHp()).thenReturn(10);
        gui.printPlayerHP(mockPlayer);
    }

    @Test
    void printCardsInHand() {
        when(creatureCard.getHp()).thenReturn(5).thenReturn(3).thenReturn(7);

        //Created the Inactive player
        when(gameEngine.getInactivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(hand);
        gui.printPlayerCardsInHand(gameEngine.getInactivePlayer());
        verify(gameEngine, times(1)).getInactivePlayer();
        verify(mockPlayer, times(1)).getHand();

        //Created the active player
        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(hand2);
        when(mockPlayer.isActive()).thenReturn(true);
        gui.printPlayerCardsInHand(gameEngine.getActivePlayer());
        verify(gameEngine, times(1)).getActivePlayer();
        verify(mockPlayer, times(2)).getHand();

    }

    @Test
    void printRemainingCards() {
        when(mockPlayer.getDeck()).thenReturn(deck);
        assertEquals(deck, mockPlayer.getDeck());
        gui.printRemainingCards(mockPlayer);
    }

    @Test
    void printCardsOnTable() {
        when(creatureCard.getHp()).thenReturn(5).thenReturn(3).thenReturn(7);

        //Created the Active Player
        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getTable()).thenReturn(table);

        //Created the Inactive player
        when(gameEngine.getInactivePlayer()).thenReturn(mockPlayer2);
        when(mockPlayer2.getTable()).thenReturn(table2);

        gui.printCardsOnTable();
        verify(gameEngine, Mockito.times(1)).getInactivePlayer();
        verify(gameEngine, Mockito.times(1)).getActivePlayer();
        verify(mockPlayer, Mockito.times(1)).getTable();
        verify(mockPlayer2, Mockito.times(1)).getTable();
    }

    @Test
    void printGameOverMenu() {
        gui.printGameOverMenu();
    }

    @Test
    void printStartMenu() {
        gui.printStartMenu();
    }
}