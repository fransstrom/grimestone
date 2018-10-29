import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GUITest {

    private GUI gui;

    @Mock
    private GameEngine gameEngine;
    @Mock
    private Player mockPlayer;

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
    }

    @Test
    void printGameOverMenu() {
    }

    @Test
    void printStartMenu() {
    }
}