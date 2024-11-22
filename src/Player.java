import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;
    private int bet;
    private int rank;
    private int order;

    public Player(String name, int order) {
        hand = new ArrayList<Card>();
        this.name = name;
        this.order = order;
    }

    public void createHand(ArrayList<Card> pocket) {
        hand = pocket;
    }

    public String getHandString() {
        return hand.get(0).toString() + " and a " + hand.get(1).toString();
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public void determineRank() {
        int[] frequency = new int[13];
        int[] pairs = new int[3];


    }
}
