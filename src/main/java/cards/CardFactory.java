package cards;

import data.CardDB;

import java.util.Random;

public class CardFactory {

    private static final Random random = new Random();

    public enum entityType {
        CREATURE_CARD, SPECIAL_CREATURE_CARD, MAGIC_CARD
    }

    public static Card getRandomCardOfType(entityType entityType){
        int bound;
        int index;
        switch (entityType){
            case CREATURE_CARD:
                bound = CardDB.getCreatureCardConfigs().size();
                index = random.nextInt(bound);
                CreatureCard creatureCardConfig = CardDB.getCreatureCardConfigs().get(index);
                return new CreatureCard(
                        creatureCardConfig.name, creatureCardConfig.manaCost,
                        creatureCardConfig.type, creatureCardConfig.activationCountdown,
                        creatureCardConfig.attack, creatureCardConfig.defense, creatureCardConfig.hp);
            case SPECIAL_CREATURE_CARD:
                bound = CardDB.getSpecialCreatureCardConfigs().size();
                index = random.nextInt(bound);
                SpecialCreatureCard specialCreatureCardConfig = CardDB.getSpecialCreatureCardConfigs().get(index);
                return new SpecialCreatureCard(
                        specialCreatureCardConfig.name, specialCreatureCardConfig.manaCost,
                        specialCreatureCardConfig.type, specialCreatureCardConfig.activationCountdown,
                        specialCreatureCardConfig.attack, specialCreatureCardConfig.defense, specialCreatureCardConfig.hp,
                        specialCreatureCardConfig.effect);
            case MAGIC_CARD:
                bound = CardDB.getMagicCardConfigs().size();
                index = random.nextInt(bound);
                MagicCard magicCardConfig = CardDB.getMagicCardConfigs().get(index);
                return new MagicCard(magicCardConfig.name, magicCardConfig.manaCost, magicCardConfig.effect);
        }
        return null;
    }

}
