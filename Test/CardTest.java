import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CardTest {

    //Card is abstract so we need to create spy for testing
    @Spy
    private Card card;

    @BeforeEach
    void setUp() {
    }

    @Test
    void isSuperEffective() {
        card.setType(Card.Type.WATER);
        assertTrue(card.isSuperEffective(Card.Type.FIRE));
        assertFalse(card.isSuperEffective(Card.Type.WATER));
        card.setType(Card.Type.FIRE);
        assertTrue(card.isSuperEffective(Card.Type.GRASS));
        assertFalse(card.isSuperEffective(Card.Type.FIRE));
    }
}