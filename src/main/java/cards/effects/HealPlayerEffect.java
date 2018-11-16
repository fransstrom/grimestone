package cards.effects;

public class HealPlayerEffect implements IEffect {

    private int healAmount;
    private String effectDescription;

    public HealPlayerEffect( int healAmount ) {
        this.healAmount = healAmount;
        this.effectDescription = "Heal Player " + healAmount + " HP";
    }


    @Override
    public String trigger() {
     return "HEAL_PLAYER_" + healAmount;
    }

    @Override
    public String getText() {
        return effectDescription;
    }
}
