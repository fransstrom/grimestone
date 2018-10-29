import java.util.Random;

public abstract class RandomNumberGenerator {

    private static Random random = new Random();

    public static int roll() {
        return random.nextInt(10) + 1;
    }
}
