public class CreatureCard extends Card {

    private boolean active;
    private int hp;
    private int activationCountdown;
    private int attack;
    private int defense;
    private Type type;


    public enum Type{
        WATER, FIRE, GRASS, DRAGON, ZOMBIE, HOLY, FAIRY, PSYCHIC, GHOST, NEUTRAL
    }

    public CreatureCard(){
        super();
        this.activationCountdown = 1;
        this.attack = 1;
        this.defense = 1;
        this.type = Type.NEUTRAL;
    }

    public CreatureCard(int hp){
        super();
        this.hp = hp;
        this.activationCountdown = 1;
        this.attack = 1;
        this.defense = 1;
        this.type = Type.NEUTRAL;
    }

    //If HP is not provided, a random value will be generated
    public CreatureCard(String name, int manaCost, Type type, int activationCountdown, int attack, int defense){
        super(name, manaCost);
        //Random HP from 1-7
        this.type = type;
        this.hp = RandomNumberGenerator.roll();
        this.activationCountdown = activationCountdown;
        this.active = false;
        this.attack = attack;
        this.defense = defense;
    }

    public CreatureCard(String name, int manaCost, Type type, int activationCountdown, int attack, int defense, int hp) {
        super(name, manaCost);
        this.type = type;
        this.activationCountdown = activationCountdown;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.active = false;
    }

    public boolean isSuperEffective(Type opponentType){
        switch (this.type){
            case FIRE:
                return opponentType == Type.GRASS;
            case GRASS:
                return opponentType == Type.WATER;
            case WATER:
                return opponentType == Type.FIRE;
            case ZOMBIE:
                return opponentType == Type.FAIRY;
            case FAIRY:
                return opponentType == Type.DRAGON;
            case HOLY:
                return opponentType == Type.ZOMBIE;
            case DRAGON:
                return opponentType == Type.PSYCHIC;
            case PSYCHIC:
                return opponentType == Type.GHOST;
            case GHOST:
                return opponentType == Type.HOLY;
        }
        return false;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
