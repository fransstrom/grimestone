package data;

import cards.CreatureCard;
import cards.MagicCard;
import cards.SpecialCreatureCard;
import cards.effects.AttackPlayerEffect;
import cards.effects.HealPlayerEffect;
import cards.effects.RushEffect;
import java.util.ArrayList;

public class CardDB {

    private static ArrayList<CreatureCard> creatureCardConfigs = new ArrayList<>();
    private static ArrayList<SpecialCreatureCard> specialCreatureCardConfigs = new ArrayList<>();
    private static ArrayList<MagicCard> magicCardConfigs = new ArrayList<>();

    static {

        creatureCardConfigs.add(new CreatureCard("Mangry Hamster", 1, CreatureCard.Type.NEUTRAL, 1, 4, 1, 2));
        creatureCardConfigs.add(new CreatureCard("Trippy Toad",1, CreatureCard.Type.GRASS, 1 , 2, 1, 3));
        creatureCardConfigs.add(new CreatureCard("Spicy Lizard",1, CreatureCard.Type.FIRE, 1 , 2, 1, 3));
        creatureCardConfigs.add(new CreatureCard("Whimsy Walrus",1, CreatureCard.Type.WATER, 1 , 2, 2, 2));


        specialCreatureCardConfigs.add(new SpecialCreatureCard("Holy Dragon", 5, CreatureCard.Type.DRAGON, 3, 10, 5, 7, new HealPlayerEffect(5)));
        specialCreatureCardConfigs.add(new SpecialCreatureCard( "Terror Rex", 3, CreatureCard.Type.NEUTRAL, 2, 4,4,6, new AttackPlayerEffect( 4 ) ));
      
      
      
        magicCardConfigs.add(new MagicCard("Holy Fire", 3, new HealPlayerEffect(7)));
        magicCardConfigs.add(new MagicCard("Blue Canon",5, new AttackPlayerEffect(5)));
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
