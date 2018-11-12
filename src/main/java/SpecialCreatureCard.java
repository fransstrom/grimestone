public class SpecialCreatureCard extends CreatureCard {
    private IEffect effect;

    SpecialCreatureCard(IEffect effect){
        super();
        this.effect = effect;
    }

    String getText(){
        return this.effect.getText();
    }
}
