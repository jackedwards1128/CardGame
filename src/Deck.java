import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private static final int size = 52;
    private final String[] suits = new String[] {"Spades", "Clubs", "Hearts", "Diamonds"};
    private final String[] ranks = new String[] {"Ace", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
                                                "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    public Deck() {
        deck = new ArrayList<Card>();
        for(int i = 0; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(ranks[i], suits[j], i));
            }
        }
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
}
