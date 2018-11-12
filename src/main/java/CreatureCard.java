public class CreatureCard extends Card {

    private boolean active;
    private int hp;

    //If HP is not provided, a random value will be generated
    public CreatureCard(String name, int manaCost, Type type){
        super(name, manaCost, type);
        //Random HP from 1-7
        this.hp = RandomNumberGenerator.roll();
        this.active = false;
    }

    public CreatureCard(String name, int manaCost, Type type, int hp) {
        super(name, manaCost, type);
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
