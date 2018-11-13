import java.util.ArrayList;
import java.util.Random;

public class CardDB {

    private static final Random rand = new Random();

    private static ArrayList<CreatureCard> creatureCards = new ArrayList<>();
    static {
        creatureCards.add(new CreatureCard("Mangry Hamster", 1, CreatureCard.Type.NEUTRAL, 1, 2, 1, 2));
        creatureCards.add(new CreatureCard("Trippy Toad",1, CreatureCard.Type.GRASS, 1 , 1, 1, 3));
    }

    private static ArrayList<SpecialCreatureCard> specialCreatureCards = new ArrayList<>();
    static {
        specialCreatureCards.add(new SpecialCreatureCard("Holy Dragon", 5, CreatureCard.Type.NEUTRAL, 3, 5, 5, 7, new HealPlayerEffect(5)));
    }

    private static ArrayList<MagicCard> magicCards = new ArrayList<>();
    static {
        magicCards.add(new MagicCard("Holy Fire", 3, new HealPlayerEffect(7)));
    }

    public static Card getRandomCreatureCard(){
        int bound = creatureCards.size();
        int index = rand.nextInt(bound);
        return creatureCards.get(index);
    }

    public static Card getRandomSpecialCreatureCard(){
        int bound = specialCreatureCards.size();
        int index = rand.nextInt(bound);
        return specialCreatureCards.get(index);
    }

    public static Card getRandomMagicCard(){
        int bound = magicCards.size();
        int index = rand.nextInt(bound);
        return magicCards.get(index);
    }


}
