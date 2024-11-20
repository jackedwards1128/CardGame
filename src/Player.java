import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;
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

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }
}
