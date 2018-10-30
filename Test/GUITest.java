import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
        Mockito.when(gameEngine.getPlayer1()).thenReturn(mockPlayer);
        Mockito.when(gameEngine.getPlayer2()).thenReturn(mockPlayer);
        Mockito.when(mockPlayer.getHp()).thenReturn(10);
        gui.printPlayerHP();
        Mockito.verify(gameEngine, Mockito.times(1)).getPlayer1();
        Mockito.verify(gameEngine, Mockito.times(1)).getPlayer2();
    }

    @Test
    void printCardsInHand() {
    }

    @Test
    void printRemainingCards() {
    }

    @Test
    void printCardsOnTable() {
        ArrayList<Card> table = new ArrayList<>();
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
}