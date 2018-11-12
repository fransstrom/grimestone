public class MagicCard extends Card {
    private IEffect effect;
    private boolean isConsumed;

    public MagicCard (IEffect effect){
        super();
        this.effect =effect;
    }

    String getText(){
        return this.effect.getText();
    }
}
