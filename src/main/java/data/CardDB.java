package data;

import cards.CreatureCard;
import cards.MagicCard;
import cards.SpecialCreatureCard;
import cards.effects.HealPlayerEffect;

import java.util.ArrayList;

public class CardDB {

    private static ArrayList<CreatureCard> creatureCardConfigs = new ArrayList<>();
    static {
        creatureCardConfigs.add(new CreatureCard("Mangry Hamster", 1, CreatureCard.Type.NEUTRAL, 1, 2, 1, 2));
        creatureCardConfigs.add(new CreatureCard("Trippy Toad",1, CreatureCard.Type.GRASS, 1 , 1, 1, 3));
        creatureCardConfigs.add(new CreatureCard("Spicy Lizard",1, CreatureCard.Type.FIRE, 1 , 1, 1, 3));
        creatureCardConfigs.add(new CreatureCard("Whimsy Walrus",1, CreatureCard.Type.WATER, 1 , 1, 2, 2));
    }
    private static ArrayList<SpecialCreatureCard> specialCreatureCardConfigs = new ArrayList<>();
    static {
        specialCreatureCardConfigs.add(new SpecialCreatureCard("Holy Dragon", 5, CreatureCard.Type.NEUTRAL, 3, 5, 5, 7, new HealPlayerEffect(5)));
    }
    private static ArrayList<MagicCard> magicCardConfigs = new ArrayList<>();
    static {
        magicCardConfigs.add(new MagicCard("Holy Fire", 3, new HealPlayerEffect(7)));
        magicCardConfigs.add(new MagicCard("Whimsy Walrus",1, new Attack));
    }

    public static ArrayList<CreatureCard> getCreatureCardConfigs() {
        return creatureCardConfigs;
    }

    public static ArrayList<SpecialCreatureCard> getSpecialCreatureCardConfigs() {
        return specialCreatureCardConfigs;
    }

    public static ArrayList<MagicCard> getMagicCardConfigs() {
        return magicCardConfigs;
    }
}
