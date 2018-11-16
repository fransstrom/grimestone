package cards;

import cards.effects.IEffect;

public class SpecialCreatureCard extends CreatureCard {
    protected IEffect effect;

    public SpecialCreatureCard(IEffect effect){
        super();
        this.effect = effect;
    }

    public SpecialCreatureCard(String name, int manaCost, Type type, int activationCountdown, int attack, int defense, int hp, IEffect effect){
        super(name, manaCost, type, activationCountdown, attack, defense, hp);
        this.effect = effect;
    }

    public String getText(){
        return this.effect.getText();
    }

    public String trigger(){
        return this.effect.trigger();
    }
}
