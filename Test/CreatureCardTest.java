import cards.CreatureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureCardTest {

    CreatureCard card;

    @BeforeEach
    void setUp() {
        card = new CreatureCard("test_card", 1, CreatureCard.Type.NEUTRAL, 10, 2, 1, 7);
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

    @Test
    void isSuperEffective() {
        card.setType(CreatureCard.Type.WATER);
        assertTrue(card.isSuperEffective(CreatureCard.Type.FIRE));
        assertFalse(card.isSuperEffective(CreatureCard.Type.WATER));
        card.setType(CreatureCard.Type.FIRE);
        assertTrue(card.isSuperEffective(CreatureCard.Type.GRASS));
        assertFalse(card.isSuperEffective(CreatureCard.Type.FIRE));
    }
}