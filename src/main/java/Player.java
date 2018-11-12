import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class Player {
    private int hp;
    private int mana;
    private int maxMana;
    private boolean isActive;
    private boolean passTurn;
    private ArrayList<Card> hand;
    private ArrayList<Card> deck;
    private ArrayList<Card> graveyard;
    private ArrayList<Card> table;

    public Player() {
        this.hp = 20;
        this.mana = 0;
        this.maxMana = 0;
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.graveyard = new ArrayList<>();
        this.table = new ArrayList<>();
        this.isActive = false;
        this.passTurn = false;
        this.generateDeck();

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
        for (Card card : this.table) {
            if (((CreatureCard) card).getHp() < 1) {
                deadCards.add( card );
            }
        }
        this.table.removeAll( deadCards );
        this.graveyard.addAll( deadCards );
    }

    public boolean placeCardOnTable( int indexOfCard ) {
        if (getHand().size() == 0 || indexOfCard > getHand().size() || indexOfCard < 0) {
            return false;
        } else {
            this.table.add( hand.get( indexOfCard - 1 ) );
            hand.remove( indexOfCard - 1 );
            return true;
        }
    }

    public boolean drawCard() {
        if (getDeck().size() == 0) {
            return false;
        } else {
            hand.add( deck.get( 0 ) );
            deck.remove( 0 );

            return true;
        }
    }

    public void drawInitialHand() {
        for (int i = 0; i < 5; i++) {
            this.hand.add( deck.get( deck.size() - 1 ) );
            this.deck.remove( (deck.size() - 1) );
        }
    }

    public void generateDeck() {
        for (int i = 0; i < 10; i++) {
            this.deck.add( new CreatureCard() );
        }
    }

    public boolean noCardsLeftInDeck() {
        return this.deck.isEmpty();
    }

    public Card pickCardFromTable( int index ) {
        if (this.getTable().size() >= index && index > 0) {
            return this.table.get( index - 1 );
        }
        return null;
    }

    public boolean hasActiveCardsOnTable() {
        return this.table.stream().anyMatch( card -> ((CreatureCard) card).isActive() );
    }

    public void setCardsOnTableToActive() {
        this.table.forEach( card -> ((CreatureCard) card).setActive( true ) );
    }

    public void passTurn( Boolean passTurn ) {
        this.passTurn = passTurn;
    }

    public boolean hasPassedTurn() {
        return this.passTurn;
    }


    public void increaseMaxMana() {
        if (maxMana < 10) {
            maxMana++;
        }
    }

    public void refillMana() {
        mana = maxMana;
    }

    public boolean checkMana( int cardMana ) {
        return (this.mana >= cardMana);
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

    public void setActive( boolean active ) {
        this.isActive = active;
    }

    public int getMana() {
        return mana;
    }

    public void setMana( int mana ) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana( int maxMana ) {
        this.maxMana = maxMana;
    }
}
