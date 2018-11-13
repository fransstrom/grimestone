public class CreatureCard extends Card {

    private boolean active;
    private int hp;
    private int activationCountdown;
    private int attack;
    private int defense;

    public CreatureCard(){
        super();
        this.activationCountdown = 1;
        this.attack = 1;
        this.defense = 1;
    }

    public CreatureCard(int hp){
        super();
        this.hp = hp;
        this.activationCountdown = 1;
        this.attack = 1;
        this.defense = 1;
    }

    //If HP is not provided, a random value will be generated
    public CreatureCard(String name, int manaCost, Type type, int activationCountdown, int attack, int defense){
        super(name, manaCost, type);
        //Random HP from 1-7
        this.hp = RandomNumberGenerator.roll();
        this.activationCountdown = activationCountdown;
        this.active = false;
        this.attack = attack;
        this.defense = defense;
    }

    public CreatureCard(String name, int manaCost, Type type, int activationCountdown, int attack, int defense, int hp) {
        super(name, manaCost, type);
        this.activationCountdown = activationCountdown;
        this.attack = attack;
        this.defense = defense;
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

    public void setActivationCountdown(int activationCountdown) {
        this.activationCountdown = activationCountdown;
    }

    public int getActivationCountdown() {
        return activationCountdown;
    }
}
