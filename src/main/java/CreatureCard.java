public class CreatureCard extends Card {

    private boolean active;
    private int hp;

    public CreatureCard(){
        super();
        //Random HP from 1-7
        this.hp = RandomNumberGenerator.roll();
        this.active = false;
    }

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
