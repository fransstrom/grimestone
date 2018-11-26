package cards.effects;

public class AttackPlayerEffect implements IEffect {

    private int damage;
    private String effectDescription;

    public AttackPlayerEffect(int damage) {
        this.damage = damage;
        this.effectDescription = "Attack player " + damage + " DMG";
    }

    @Override
    public String trigger() {
        return "ATTACK_PLAYER_" + damage;
    }

    @Override
    public String getText() {
        return effectDescription;
    }
}
