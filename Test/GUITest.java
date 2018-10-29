import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GUITest {

    GUI gui;

    @Mock
    GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        gui = new GUI(gameEngine);
    }

    @Test
    void render() {
    }

    @Test
    void printPlayerHP() {
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