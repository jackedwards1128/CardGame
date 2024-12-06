import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int playerCount;
    private Deck pile;
    private int bet;
    private ArrayList<Card> middleCards;
    private ArrayList<Player> players;
    private ArrayList<Player> playersCopy;
    private final String[] ranks = new String[] {"High Card", "Pair", "Two Pair", "Three of a Kind",
            "Straight", "Flush", "Full House", "Quads", "Straight Flush", "Royal Flush"};

    public Game() {
        players = new ArrayList<Player>();
        playersCopy = new ArrayList<Player>();
        Deck deck = new Deck();
        deck.shuffle();
        this.pile = deck;


//        pile.add(0, new Card("Seven", "Diamonds", 6));
//        pile.add(0, new Card("Six", "Diamonds", 5));
//        pile.add(0, new Card("Five", "Diamonds", 4));
//        pile.add(0, new Card("Four", "Diamonds", 3));
//        pile.add(0, new Card("Three", "Diamonds", 2));
//
//        pile.add(0, new Card("Two", "Diamonds", 1));
//        pile.add(0, new Card("Ace", "Diamonds", 0));



        middleCards = new ArrayList<Card>();

        this.beginGame();
    }

    private void beginGame() {
        printInstructions();

        Scanner input = new Scanner(System.in);

        System.out.println("How many players will be participating ? ");

        playerCount = input.nextInt();

        for (int i = 0; i < playerCount; i++) {
            System.out.println("What is player " + (i+1) + "'s name? ");
            if (i == 0) {
                input.nextLine();
            }
            String playerName = input.nextLine();

            players.add(new Player(playerName, i));

            ArrayList<Card> pocket = new ArrayList<Card>();

            // pull two cards out of the pile and put them into a player's pocket
            pocket.add(pile.remove(0));
            pocket.add(pile.remove(0));

            players.get(i).createHand(pocket);
            playersCopy.add(players.get(i));

            //input.nextLine();
        }

        showHands();

        // flop
        flop();
        gatherBets(0);

        // turn
        revealCard();
        showCards();
        gatherBets(0);

        // river
        revealCard();
        showCards();
        gatherBets(0);

        // grand reveal
        grandReveal();
    }

    private void showHands() {
        for (int i = 0; i < playerCount; i++) {
            Scanner input = new Scanner(System.in);

            System.out.println(players.get(i).getName() + ", click enter once you are ready to see your hand");
            input.nextLine();

            System.out.print(players.get(i).getName() + " has a ");
            System.out.println(players.get(i).getHandString());
            System.out.println("click enter once you done seeing your hand");

            input.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
//            System.out.println("\n\n\n");
        }
    }

    private void flop() {
        for(int i = 0; i < 3; i++) {
            revealCard();
        }
        showCards();
    }

    private void showCards() {
        System.out.print("//////// MIDDLE CARDS: ");

        for(int i = 0; i < middleCards.size(); i++) {
            System.out.print(middleCards.get(i).toString());
            if (i != middleCards.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" ////////");
    }

    private void revealCard() {
        middleCards.add(pile.remove(0));
    }

    private void gatherBets(int startingSpot) {
        for(int i = startingSpot; i < playerCount; i++) {
            char choice = '0';

            while (choice != 'c' && choice != 'r' && choice != 'f') {
                System.out.println(players.get(i).getName() + ", do you want to (f)old, (c)heck/call, or (r)aise?");
                Scanner input = new Scanner(System.in);
                choice = input.nextLine().charAt(0);
//                choice = 'c';
            }

            boolean playerFolded = false;
            switch (choice) {
                case 'f':
                    System.out.println(players.get(i).getName() + " folded out of the round");
                    players.remove(i);
                    playerCount--;
                    playerFolded = true;
                    i--;
                    break;
                case 'c':
                    players.get(i).setBet(bet);
                    System.out.println(players.get(i).getName() + " matched to a bet of $" + bet);
                    break;
                case 'r':
                    int newBet = -1;

                    while (newBet <= bet) {
                        System.out.println("what would you like to raise the bet to? ");
                        Scanner input = new Scanner(System.in);
                        newBet = input.nextInt();
                    }

                    bet = newBet;
                    players.get(i).setBet(bet);
                    System.out.println(players.get(i).getName() + " raise the a bet to $" + bet);
                    break;
            }
        }
        for(int k = 0; k < playerCount; k++) {
            if(players.get(k).getBet() != bet) {
                gatherBets(k);
            }
        }
    }

    private void grandReveal() {
        showCards();
        for (int i = 0; i < playersCopy.size(); i++) {
            System.out.print(playersCopy.get(i).getName() + " had a ");
            System.out.println(playersCopy.get(i).getHandString());
            playersCopy.get(i).determineRank(middleCards);
            System.out.println("They got a " + ranks[playersCopy.get(i).getRank()]);
        }
    }


    private static void printInstructions() {
        System.out.println("WELCOME TO POKER wahwahwahh");
    }

    public ArrayList<Card> getMiddleCards() {
        return middleCards;
    }

    public static void main(String[] args) {
        Game poker = new Game();
    }
}



