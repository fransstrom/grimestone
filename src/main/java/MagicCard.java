public class MagicCard extends Card {
    IEffect effect;
    String effectText;
    boolean isConsumed;

    public MagicCard (IEffect effect){
        super();
        this.effect =effect;
    }

    String getText(){
        return this.effect.getText();
    }
}
