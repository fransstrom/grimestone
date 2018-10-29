import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class GameEngineTest{

    @Test
    void setUpNewGame(){
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        player1.drawInitialHand();
        verify(player1, times(1)).drawInitialHand();
        player2.drawInitialHand();
        verify(player2, times(1)).drawInitialHand();
    }

}