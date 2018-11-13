public class MagicCard extends Card {
    private IEffect effect;
    private boolean isConsumed;

    public MagicCard (IEffect effect){
        super();
        this.effect =effect;
    }

    public MagicCard(String name, int manaCost, Type type, IEffect effect) {
       super(name, manaCost, type);
       this.effect = effect;
    }

    public String getText(){
        return this.effect.getText();
    }

    public IEffect getEffect(){
        return this.effect;
    }

}
