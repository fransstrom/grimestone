import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureCardTest {

    CreatureCard card;

    @BeforeEach
    void setUp() {
        card = new CreatureCard("test_card", 1, Card.Type.NEUTRAL, 10, 2, 1);
    }

    @Test
    void isActive() {
        assertFalse(card.isActive());
        card.setActive(true);
        assertTrue(card.isActive());
    }

    @Test
    void setActive() {
        card.setActive(true);
        assertTrue(card.isActive());
    }
}