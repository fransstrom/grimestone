import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void roll() {
        int randomNum = RandomNumberGenerator.roll();
        assertTrue(randomNum > 0 && randomNum <= 10);
    }
}