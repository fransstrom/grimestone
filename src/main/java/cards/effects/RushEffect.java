package cards.effects;

public class RushEffect implements IEffect {

    private String effectDescription;

    public RushEffect() {
        this.effectDescription = "Rush attack!";
    }

    @Override
    public String trigger() {
        return "RUSH_TRUE";
    }

    @Override
    public String getText() {
        return effectDescription;
    }
}
