public class MagicCard extends Card {
    private IEffect effect;
    private boolean isConsumed;

    public MagicCard (IEffect effect){
        super();
        this.effect =effect;
    }

    public MagicCard(String name, int manaCost, IEffect effect) {
       super(name, manaCost);
       this.effect = effect;
    }

    public String getText(){
        return this.effect.getText();
    }

    public String trigger(){
        return this.effect.trigger();
    }

}
