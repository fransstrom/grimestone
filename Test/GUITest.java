import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;


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
    }

    @Test
    void printCardsOnTable() {
    }

    @Test
    void printGameOverMenu() {
    }

    @Test
    void printStartMenu() {
    }
}