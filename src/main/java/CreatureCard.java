public class CreatureCard extends Card {
    private boolean active;
    private int hp;

    public CreatureCard(int hp) {
        super();
        this.hp = hp;
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
