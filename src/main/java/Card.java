import java.util.UUID;

public abstract class Card {

    public enum Type{
        WATER, FIRE, GRASS, GHOST
    }

    private String name;
    private int manaCost;
    private Type type;

    public Card(String name, int manaCost, Type type) {
        this.name = name;
        this.manaCost = manaCost;
        this.type = type;
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
}
