import cards.Card;
import cards.CardFactory;
import cards.CreatureCard;
import cards.MagicCard;
import data.CardDB;

import java.util.ArrayList;
import java.util.Collections;

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
    private int maxHp;
    private final int maxNumberOfCardsOnDisplay = 5;
    private final int initialAmountOfCardsInHand=3;

    public Player() {
        this.hp = 20;
        this.maxHp = this.hp;
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
        return (this.hp > 0) && (!this.noCardsLeftInDeck());
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
                deadCards.add(card);
            }
        }
        this.table.removeAll(deadCards);
        this.graveyard.addAll(deadCards);
    }

    public boolean placeCardOnTable(int indexOfCard) {

        if (getHand().size() == 0 || indexOfCard > getHand().size() || indexOfCard < 0 || getTable().size() >= maxNumberOfCardsOnDisplay) {
            return false;
        } else {
            this.table.add(hand.get(indexOfCard - 1));
            hand.remove(indexOfCard - 1);
            return true;
        }
    }

    public boolean drawCard() {
        if (deck.size() == 0) {
            return false;
        } else if (hand.size() == maxNumberOfCardsOnDisplay) {
            graveyard.add(deck.get(0));
            deck.remove(0);
            System.out.println("\033[0;93mHand full, the card is moved straight to Graveyeard\n\033[0m");
            sleep(1000);
            return false;
        } else {
            hand.add(deck.get(0));
            deck.remove(0);
            return true;
        }
    }

    public void drawInitialHand() {
        for (int i = 0; i < initialAmountOfCardsInHand; i++) {
            this.hand.add(deck.get(deck.size() - 1));
            this.deck.remove((deck.size() - 1));
        }
    }

    public void generateDeck() {
        for (int i = 0; i < 10; i++) {
            this.deck.add(CardFactory.getRandomCardOfType(CardFactory.entityType.CREATURE_CARD));
        }
        for (int i = 0; i < 5; i++) {
            this.deck.add(CardFactory.getRandomCardOfType(CardFactory.entityType.SPECIAL_CREATURE_CARD));
            this.deck.add(CardFactory.getRandomCardOfType(CardFactory.entityType.MAGIC_CARD));
        }
        Collections.shuffle(this.deck);
    }

    public boolean noCardsLeftInDeck() {
        return this.deck.isEmpty();
    }

    public Card pickCardFromTable(int index) {
        if (this.getTable().size() >= index && index > 0) {
            return this.table.get(index - 1);
        }
        return null;
    }

    public boolean hasActiveCardsOnTable() {
        return this.table.stream().anyMatch(card -> ((CreatureCard) card).isActive());
    }

    public void setCardsOnTableToActive() {
        this.table.forEach(card -> ((CreatureCard) card).setActivationCountdown(((CreatureCard) card).getActivationCountdown() - 1));
        this.table.stream()
                .filter(card -> ((CreatureCard) card).getActivationCountdown() < 1)
                .forEach(card -> ((CreatureCard) card).setActive(true));
    }

    public void passTurn(Boolean passTurn) {
        this.passTurn = passTurn;
    }

    public boolean hasPassedTurn() {
        return this.passTurn;
    }

    public void heal(int healAmount) {
        if ((this.hp + healAmount) > maxHp) {
            this.hp = maxHp;
        } else this.hp += healAmount;
    }

    public void incrementMaxMana() {
        if (maxMana < 10) {
            maxMana++;
        }
    }

    public void refillMana() {
        mana = maxMana;
    }

    public boolean checkMana(int cardMana) {
        if (this.mana >= cardMana) {
            return true;
        } else {
            System.out.println("\033[0;93mNot enough mana!\n\033[0m");
            sleep(1000);
            return false;
        }
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public String playCard(int index) {
        if (index <= hand.size() && index > 0) {
            Card card = hand.get(index - 1);
            if (card instanceof CreatureCard && checkMana(card.getManaCost())) {
                if (placeCardOnTable(index)) {
                    reduceMana(card.getManaCost());
                    return "PLAYED_CREATURECARD";
                }
            } else if (card instanceof MagicCard && checkMana(card.getManaCost())) {
                reduceMana(card.getManaCost());
                graveyard.add(hand.get(index - 1));
                hand.remove(index - 1);
                return ((MagicCard) card).trigger();
            }
        }
        return "FAULTY_CHOICE";
    }

    public void reduceMana(int cardManaCost) {
        mana -= cardManaCost;
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

