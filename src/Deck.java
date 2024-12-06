import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private static final int size = 52;
    private final String[] suits = new String[] {"Spades", "Clubs", "Hearts", "Diamonds"};
    private final String[] ranks = new String[] {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
                                                "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    public Deck() {
        deck = new ArrayList<Card>();
        for(int i = 0; i <= 12; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(ranks[i], suits[j], i));
            }
        }
    }

    public void add(int index, Card nCard) {
        deck.add(index, nCard);
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card getRandomCard() {
        return deck.get((int)(Math.random()*52));
    }

    public void shuffle() {
        for (int i = 0; i < 52; i++) {
            Card temp = deck.get(i); // c = a
            int swap = (int)(Math.random() * 52); // b

            deck.set(i, deck.get(swap)); // a = b
            deck.set(swap, temp); // b = c
        }
    }

    public Card remove(int index) {
        return deck.remove(index);
    }
}


