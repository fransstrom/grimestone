import java.util.ArrayList;

public class Player {
    private int hp;
   private ArrayList<Card> hand;
   private ArrayList<Card> deck;
   private ArrayList<Card> graveyard;
   private ArrayList<Card> table;

    public Player() {
        this.hp = 10;
        this.hand = new ArrayList<Card>( );
        this.deck = new ArrayList<Card>(  );
        this.graveyard = new ArrayList<Card>(  );
        this.table = new ArrayList<Card>(  );
    }

    public boolean isAlive() {
        return false;
    }

    public boolean deckIsEmpty() {
        return false;
    }

    public boolean tableIsEmpty() {
        return false;
    }

    public void moveDeadCardToGraveyard() {

    }

    public void placeCardOnTable() {

    }

    public boolean drawCard() {
        if(getDeck().size() == 0){
            return false;
        }else{
            hand.add(deck.get(1));
            deck.remove(1);
            return true;
        }
    }

    public void drawInitialHand() {

    }

    public void generateDeck() {

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(ArrayList<Card> graveyard) {
        this.graveyard = graveyard;
    }

    public ArrayList<Card> getTable() {
        return table;
    }

    public void setTable(ArrayList<Card> table) {
        this.table = table;
    }
}
