import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    private CreatureCard creatureCard;

    @Mock
    private CreatureCard creatureCardInactive;

    @BeforeEach
    void setUp() {
        gui = new GUI(gameEngine);
    }

    @Test
    void render() {
    }

    @Test
    void printPlayerHP() {
        when(gameEngine.getPlayer1()).thenReturn(mockPlayer);
        when(gameEngine.getPlayer2()).thenReturn(mockPlayer);
        when(mockPlayer.getHp()).thenReturn(10);
        gui.printPlayerHP();
        verify(gameEngine, times(1)).getPlayer1();
        verify(gameEngine, times(1)).getPlayer2();
    }

    @Test
    void printCardsInHand() {
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(creatureCard);

        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(cardList);

        gui.printCardsInHand();
        verify(gameEngine, times(1)).getActivePlayer();
        verify(mockPlayer, times(1)).getHand();

    }

    @Test
    void printRemainingCards() {
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(new CreatureCard(10));
        deck.add(new CreatureCard(20));
        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getDeck()).thenReturn(deck);
        assertEquals(deck, mockPlayer.getDeck());
        gui.printRemainingCards();
        verify(gameEngine, Mockito.times(1)).getActivePlayer();
    }

    @Test
    void printCardsOnTable() {
        ArrayList<Card> table = new ArrayList<>();
        creatureCard = new CreatureCard();
        table.add(creatureCard);


        Mockito.when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        Mockito.when(mockPlayer.getTable()).thenReturn(table);
        gui.printCardsOnTable();
        Mockito.verify(gameEngine, Mockito.times(1)).getActivePlayer();
        Mockito.verify(mockPlayer, Mockito.times(1)).getTable();
    }

    @Test
    void printGameOverMenu() {
    }

    @Test
    void printStartMenu() {
    }

    @Test
    void printUsableCardsOnTable() {
        ArrayList<Card> table = new ArrayList<>();
        creatureCard = new CreatureCard();
        creatureCard.setActive(true);
        table.add(creatureCard);
        table.add(creatureCard);

        creatureCardInactive = new CreatureCard(40);
        creatureCardInactive.setActive(false);
        table.add(creatureCardInactive);

        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        when(gameEngine.getActivePlayer().getTable()).thenReturn(table);

        gui.printUsableCardsOnTable();

        verify(gameEngine, times(2)).getActivePlayer();
    }
}