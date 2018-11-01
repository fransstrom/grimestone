import java.util.ArrayList;

public class Player {
    private int hp;
    private boolean isActive;
    private ArrayList<Card> hand;
    private ArrayList<Card> deck;
    private ArrayList<Card> graveyard;
    private ArrayList<Card> table;

    public Player() {
        this.hp = 10;
        this.hand = new ArrayList<Card>();
        this.deck = new ArrayList<Card>();
        this.graveyard = new ArrayList<Card>();
        this.table = new ArrayList<Card>();
        this.isActive=false;
    }

    public boolean isAlive() {
        return (this.hp > 0);
    }

    public boolean deckIsEmpty() {
        return (deck.isEmpty());
    }

    public boolean tableIsEmpty() {
        return this.table.isEmpty();
    }

    public void moveDeadCardToGraveyard() {
        ArrayList<Card> deadCards = new ArrayList<>();
        for(Card card: this.table){
            if(((CreatureCard)card).getHp() < 1){
               deadCards.add(card);
            }
        }
        this.table.removeAll(deadCards);
        this.graveyard.addAll(deadCards);
    }

    public boolean placeCardOnTable(int indexOfCard) {
        if (getHand().size() == 0 || indexOfCard > getHand().size()) {
            return false;
        } else {
            this.table.add(hand.get(indexOfCard));
            hand.remove(indexOfCard);
            return true;
        }
    }

    public boolean drawCard() {
        if (getDeck().size() == 0) {
            return false;
        }else{
            hand.add(deck.get(0));
            deck.remove(0);

            return true;
        }
    }

    public void drawInitialHand() {
        for (int i = 0; i < 5; i++) {
            hand.add(deck.get(deck.size() - 1));
            deck.remove((deck.size() - 1));
        }
    }

    public void generateDeck() {
        for (int i = 0; i < 10; i++) {
            deck.add( new CreatureCard( RandomNumberGenerator.roll() ) );
        }
    }

    public boolean noCardsLeft() {
        return false;
    }

    public int getHp() {
        return hp;
    }

    public void setHp( int hp ) {
        this.hp = hp;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand( ArrayList<Card> hand ) {
        this.hand = hand;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck( ArrayList<Card> deck ) {
        this.deck = deck;
    }

    public ArrayList<Card> getGraveyard() {
        return graveyard;
    }

    public void setGraveyard( ArrayList<Card> graveyard ) {
        this.graveyard = graveyard;
    }

    public ArrayList<Card> getTable() {
        return table;
    }

    public void setTable( ArrayList<Card> table ) {
        this.table = table;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
