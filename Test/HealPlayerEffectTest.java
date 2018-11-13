import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HealPlayerEffectTest {

    private MagicCard magicCard;
    private Player player;

    @BeforeEach
    void setup() {
        magicCard = new MagicCard("Holy Fire", 3, Card.Type.NEUTRAL, new HealPlayerEffect(7));
        player = new Player();
    }

    @Test
    void testHealing() {
        magicCard.getEffect().trigger(player);
        assertEquals(20, player.getHp());

        player.setHp(12);
        magicCard.getEffect().trigger(player);
        assertEquals(19, player.getHp());

        player.setHp(16);
        magicCard.getEffect().trigger(player);
        assertEquals(20, player.getHp());
    }

}