public class HealPlayerEffect implements IEffect{

    private int healAmount;
    private String effectDescription;

    public HealPlayerEffect( int healAmount ) {
        this.healAmount = healAmount;
        this.effectDescription = "Heal player for " + healAmount + " HP.";
    }


    @Override
    public void trigger() {
     // implement functionality ?
    }

    @Override
    public String getText() {
        return effectDescription;
    }
}
