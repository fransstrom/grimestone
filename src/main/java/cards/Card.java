import java.util.UUID;

public abstract class Card {


    private String name;
    private int manaCost;
    public Card(){
        this.name = "";
        this.manaCost = 1;
    }

    public Card(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }


    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

}
