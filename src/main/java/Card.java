import java.util.UUID;

public abstract class Card {

    public enum Type{
        WATER, FIRE, GRASS, NEUTRAL
    }

    private String name;
    private int manaCost;
    private Type type;

    public Card(){
        this.name = "";
        this.manaCost = 1;
        this.type = Type.NEUTRAL;
    }

    public Card(String name, int manaCost, Type type) {
        this.name = name;
        this.manaCost = manaCost;
        this.type = type;
    }

    public boolean isSuperEffective(Type opponentType){
        switch (this.type){
            case FIRE:
                return opponentType == Type.GRASS;
            case GRASS:
                return opponentType == Type.WATER;
            case WATER:
                return opponentType == Type.FIRE;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
