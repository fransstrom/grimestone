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
    private Player mockPlayer2;
    @Mock
    private CreatureCard creatureCard;

    @BeforeEach
    void setUp() {
        gui = new GUI(gameEngine);
    }

    @Test
    void render() {
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new CreatureCard());
        cardList.add(new CreatureCard());
        cardList.add(new CreatureCard());
        cardList.add(new CreatureCard());

        ArrayList<Card> cardList2 = new ArrayList<>();
        cardList2.add(new CreatureCard());
        cardList2.add(new CreatureCard());
        cardList2.add(new CreatureCard());
        cardList2.add(new CreatureCard());
        cardList2.add(new CreatureCard());

        ArrayList<Card> table = new ArrayList<>();
        table.add(new CreatureCard());

        ArrayList<Card> table2 = new ArrayList<>();
        table2.add(new CreatureCard());
        table2.add(new CreatureCard());

        ArrayList<Card> graveyard = new ArrayList<>();
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());
        graveyard.add(new CreatureCard());

        ArrayList<Card> emptyList = new ArrayList<>();

        //Created the Inactive player
        when(gameEngine.getInactivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(cardList);
        when(mockPlayer.getTable()).thenReturn(table);
        when(mockPlayer.getHp()).thenReturn(10);
        when(mockPlayer.getDeck()).thenReturn(cardList);
        when(mockPlayer.getGraveyard()).thenReturn(table);



        //Created the active player
        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer2);
        when(mockPlayer2.isActive()).thenReturn(true);
        when(mockPlayer2.getHand()).thenReturn(cardList2);
        when(mockPlayer2.getTable()).thenReturn(table2);
        when(mockPlayer2.getHp()).thenReturn(2);
        when(mockPlayer2.getDeck()).thenReturn(cardList2);
        when(mockPlayer2.getGraveyard()).thenReturn(graveyard);

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
        //Created a CardList with 4 Cards
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new CreatureCard());
        cardList.add(new CreatureCard());
        cardList.add(new CreatureCard());
        cardList.add(new CreatureCard());

        //Created the Inactive player
        when(gameEngine.getInactivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(cardList);
        gui.printPlayerCardsInHand(gameEngine.getInactivePlayer());
        verify(gameEngine, times(1)).getInactivePlayer();
        verify(mockPlayer, times(1)).getHand();

        //Created the active player
        when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        when(mockPlayer.getHand()).thenReturn(cardList);
        when(mockPlayer.isActive()).thenReturn(true);
        gui.printPlayerCardsInHand(gameEngine.getActivePlayer());
        verify(gameEngine, times(1)).getActivePlayer();
        verify(mockPlayer, times(2)).getHand();

    }

    @Test
    void printRemainingCards() {
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(new CreatureCard(10));
        deck.add(new CreatureCard(20));
        when(mockPlayer.getDeck()).thenReturn(deck);
        assertEquals(deck, mockPlayer.getDeck());
        gui.printRemainingCards(mockPlayer);
    }

    @Test
    void printCardsOnTable() {
        ArrayList<Card> table = new ArrayList<>();
        table.add(creatureCard);
        table.add(new CreatureCard());
        table.add(new CreatureCard());
        table.add(new CreatureCard());


        ArrayList<Card> table2 = new ArrayList<>();
        table2.add(new CreatureCard());
        table2.add(new CreatureCard());

        //Created the Active Player
        Mockito.when(gameEngine.getActivePlayer()).thenReturn(mockPlayer);
        Mockito.when(mockPlayer.getTable()).thenReturn(table);

        //Created the Inactive player
        when(gameEngine.getInactivePlayer()).thenReturn(mockPlayer2);
        Mockito.when(mockPlayer2.getTable()).thenReturn(table2);

        gui.printCardsOnTable();
        Mockito.verify(gameEngine, Mockito.times(1)).getInactivePlayer();
        Mockito.verify(gameEngine, Mockito.times(1)).getActivePlayer();
        Mockito.verify(mockPlayer, Mockito.times(1)).getTable();
        Mockito.verify(mockPlayer2, Mockito.times(1)).getTable();

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