import java.util.ArrayList;

public class CardDB {

    private static ArrayList<CreatureCard> creatureCards = new ArrayList<>();
    static {
        creatureCards.add(new CreatureCard("Mangry Hamster", 1, Card.Type.NEUTRAL, 1, 2, 1, 2));
        creatureCards.add(new CreatureCard("Trippy Toad",1, Card.Type.GRASS, 1 , 1, 1, 3));
    }

    private static ArrayList<SpecialCreatureCard> specialCreatureCards = new ArrayList<>();
    static {
        specialCreatureCards.add(new SpecialCreatureCard("Holy Dragon", 5, Card.Type.NEUTRAL, 3, 5, 5, 7, new HealPlayerEffect(5)));
    }

    private static ArrayList<MagicCard> magicCards = new ArrayList<>();
    static {
        magicCards.add(new MagicCard("Holy Fire", 3, Card.Type.NEUTRAL, new HealPlayerEffect(7)));
    }


}
