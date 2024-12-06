import java.util.ArrayList;

public class Deck {
    // Define instance variables
    // Deck holds the shuffled deck that cards are drawn out of
    private ArrayList<Card> deck;
    private static final int size = 52;
    private final String[] suits = new String[] {"Spades", "Clubs", "Hearts", "Diamonds"};
    private final String[] ranks = new String[] {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
                                                "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    public Deck() {
        // Add the non-shuffled cards to deck
        deck = new ArrayList<Card>();
        for(int i = 0; i <= 12; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(ranks[i], suits[j], i));
            }
        }
    }

    public void add(int index, Card nCard) {
        // add a card to deck (used for debugging)
        deck.add(index, nCard);
    }


    // Getters
    public int getSize() {
        return size;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card getRandomCard() {
        return deck.get((int)(Math.random()*52));
    }

    // Shuffle deck
    public void shuffle() {
        for (int i = 0; i < 52; i++) {
            // Go through each card and swap it with a random index
            Card temp = deck.get(i); // c = a
            int swap = (int)(Math.random() * 52); // b

            deck.set(i, deck.get(swap)); // a = b
            deck.set(swap, temp); // b = c
        }
    }

    // Remove given index
    public Card remove(int index) {
        return deck.remove(index);
    }
}


