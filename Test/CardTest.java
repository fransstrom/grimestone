import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CardTest {

    //Card is abstract so we need to create mock for testing
    @Mock
    private Card card;

    @BeforeEach
    void setUp() {
    }

    @Test
    void isSuperEffective() {

    }
}