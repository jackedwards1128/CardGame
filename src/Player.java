import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;
    private int bet;
    private int rank;
    private int order;

    private final static int NUM_POSSIBLE_HANDS = 2520;

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

    public void determineRank(ArrayList<Card> middleCards) {
        ArrayList<Card> fullHand = new ArrayList<Card>();
        fullHand.addAll(middleCards);
        fullHand.addAll(hand);
        ArrayList<Card> tempFullHand = new ArrayList<Card>();
        tempFullHand.addAll(fullHand);


        // since seven choose five is the same as seven choose two, choose two indexes to skip when selecting cards
        // and cycle through all possible choices for these skips
        for (int skipOne = 0; skipOne < 7; skipOne++) {
            int skipTwo = 0;

            while (true) {

                ArrayList<Card> supposedHand = new ArrayList<Card>();

                int indexToAdd = 0;
                while (indexToAdd < tempFullHand.size()) {
                    if(7 - tempFullHand.size() == skipOne || 7 - tempFullHand.size() == skipTwo) {
                        indexToAdd++;
                    }
                    supposedHand.add(tempFullHand.remove(indexToAdd));
                }


                rank = judgeHand(supposedHand);


                // increment the second skip value, and make the second skip hop over the first skip if the overlap
                // if both skips reach 6, then break out of the loop
                skipTwo++;
                if (skipTwo == skipOne) {
                    if (skipTwo == 6) {
                        break;
                    }
                    skipTwo++;
                }
            }

        }





    }

    private int judgeHand(ArrayList<Card> givenHand) {
        return 0;
    }
}
