public class SpecialCreatureCard extends CreatureCard{
    IEffect effect;
    String effectText;

    SpecialCreatureCard(IEffect effect){
        super();
        this.effect = effect;
    }

    String getText(){
       return this.effect.getText();
    }

}
