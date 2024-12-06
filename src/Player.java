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

    public int getRank() {
        return rank;
    }

    public void determineRank(ArrayList<Card> middleCards) {
        ArrayList<Card> fullHand = new ArrayList<Card>();
        fullHand.addAll(middleCards);
        fullHand.addAll(hand);
        ArrayList<Card> tempFullHand = new ArrayList<Card>();
        tempFullHand.addAll(fullHand);

//        for (Card Card : fullHand) {
//            System.out.println(Card.toString());
//        }


        // since seven choose five is the same as seven choose two, choose two indexes to skip when selecting cards
        // and cycle through all possible choices for these skips
        rank = -1;
        for (int skipOne = 0; skipOne < 7; skipOne++) {
            for (int skipTwo = 0; skipTwo < 7; skipTwo++) {
                if (skipOne == skipTwo) {
                    continue;
                }
                ArrayList<Card> supposedHand = new ArrayList<Card>();

                supposedHand.addAll(fullHand);


//                System.out.println(skipOne);
//                System.out.println(skipTwo);
                supposedHand.set(skipOne, new Card("Ace", "Spades", -1));
                supposedHand.set(skipTwo, new Card("Ace", "Spades", -1));

                for (int i = 0; i < supposedHand.size(); i++) {
                    if (supposedHand.get(i).getValue() == -1) {
                        supposedHand.remove(i--);
                    }
                }

//                for (Card Card : supposedHand) {
//                    System.out.println(Card.toString());
//                }

                rank = Math.max(judgeHand(supposedHand), rank);

            }



        }


    }

    private int judgeHand(ArrayList<Card> givenHand) {
        // 9 royal flush
        // 8 straight flush
        // 7 quads
        // 6 full house
        // 5 flush
        // 4 straight
        // 3 trips
        // 2 two pair
        // 1 pair
        // 0 high
        int[] frequency = new int[13];
        boolean straight = false;
        boolean flush = false;

        for (Card Card : givenHand) {
            frequency[Card.getValue()]++;
        }


        // determine presence of straight
        int straightCounter = 0;
        for (int i = 0; i < 13; i++) {
            if(frequency[i] > 0) {
                straightCounter++;
            } else {
                straightCounter = 0;
            }

            // if five cards in an ascending row are present, straight present
            if(straightCounter == 5) {
                straight = true;
                break;
            }
        }

//        for (Card Card : givenHand) {
//            System.out.println(Card.toString());
//        }
//        System.out.println("\n");

        // determine presence of flush
        flush = true;
        for (int i = 1; i < 5; i++) {
            // if the suit of the given card is not the same as the suit of the first card then no flush
            if (!givenHand.get(i).getSuit().equals(givenHand.get(0).getSuit())) {
                flush = false;
                break;
            }
        }


        // ROYAL FLUSH & STRAIGHT FLUSH
        if (flush && straight) {
            boolean royal = false;
            for (Card Card : givenHand) {
                if (Card.getValue() == 12) {
                    royal = true;
                    break;
                }
            }
            if (royal) {
                return 9;
            } else {
                return 8;
            }
        }

        if (flush) {
            return 5;
        }
        if (straight) {
            return 4;
        }

        // QUADS, TRIPS, DUBS, 2DUBS, FULL HOUSE
        int trips = 0;
        int pairs = 0;
        for (int freq : frequency) {
            switch (freq){
                case 4:
                    return 7;
                case 3:
                    trips++;
                    break;
                case 2:
                    pairs++;
            }
        }
        if (trips == 1 && pairs == 1) {
            return 6;
        }
        else if (trips == 1) {
            return 3;
        }
        else if (pairs == 2) {
            return 2;
        }
        else if (pairs == 1) {
            return 1;
        }

        return 0;

    }
}













