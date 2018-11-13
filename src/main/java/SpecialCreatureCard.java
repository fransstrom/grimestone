public class SpecialCreatureCard extends CreatureCard {
    private IEffect effect;

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

    public IEffect getEffect(){
        return effect;
    }
}
