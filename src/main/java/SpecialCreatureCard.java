public class SpecialCreatureCard extends CreatureCard {
    private IEffect effect;

    public SpecialCreatureCard(IEffect effect){
        super();
        this.effect = effect;
    }

    public SpecialCreatureCard(String name, int manaCost, Type type, int activationCountdown, int attack, int defense, IEffect effect){
        super(name, manaCost, type, activationCountdown, attack, defense);
        this.effect = effect;
    }

    String getText(){
        return this.effect.getText();
    }
}
