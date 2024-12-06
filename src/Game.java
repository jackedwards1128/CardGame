import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Define instance variables
    private int playerCount;
    private Deck pile;
    private int bet;
    private ArrayList<Card> middleCards;
    private ArrayList<Player> players;
    private ArrayList<Player> playersCopy;
    private final String[] ranks = new String[] {"High Card", "Pair", "Two Pair", "Three of a Kind",
            "Straight", "Flush", "Full House", "Quads", "Straight Flush", "Royal Flush"};

    public Game() {
        // Begin game by creating arraylist of players and the deck
        players = new ArrayList<Player>();
        playersCopy = new ArrayList<Player>();
        Deck deck = new Deck();
        deck.shuffle();
        this.pile = deck;


        middleCards = new ArrayList<Card>();

        this.beginGame();
    }

    private void beginGame() {
        // Print instructions to the player
        printInstructions();

        // Determine player count
        Scanner input = new Scanner(System.in);
        System.out.println("How many players will be participating ? ");
        playerCount = input.nextInt();

        // Gather names of each player, add them to players arraylist, and give them a pocket
        for (int i = 0; i < playerCount; i++) {
            System.out.println("What is player " + (i+1) + "'s name? ");

            // fixes an input bug
            if (i == 0) {
                input.nextLine();
            }
            String playerName = input.nextLine();

            players.add(new Player(playerName));

            ArrayList<Card> pocket = new ArrayList<Card>();

            // pull two cards out of the pile and put them into a player's pocket
            pocket.add(pile.remove(0));
            pocket.add(pile.remove(0));

            players.get(i).createHand(pocket);
            playersCopy.add(players.get(i));
        }

        // Display hands to players privately
        showHands();

        // Flop
        flop();
        gatherBets(0);

        // Turn
        revealCard();
        showCards();
        gatherBets(0);

        // River
        revealCard();
        showCards();
        gatherBets(0);

        // Grand reveal
        grandReveal();
    }

    // Privately show pockets to all players
    private void showHands() {
        for (int i = 0; i < playerCount; i++) {
            Scanner input = new Scanner(System.in);

            // Warn player
            System.out.println(players.get(i).getName() + ", click enter once you are ready to see your hand");
            input.nextLine();

            System.out.print(players.get(i).getName() + " has a ");
            System.out.println(players.get(i).getHandString());
            System.out.println("click enter once you done seeing your hand");

            // When enter is pressed it pushes the pocket out of view of the next player
            input.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    // Run flop by revealing the middle three cards and displaying them
    private void flop() {
        for(int i = 0; i < 3; i++) {
            revealCard();
        }
        showCards();
    }

    // Display cards to all players
    private void showCards() {
        System.out.print("//////// MIDDLE CARDS: ");

        for(int i = 0; i < middleCards.size(); i++) {
            System.out.print(middleCards.get(i).toString());

            // Makes sure the print doesn't end with a comma
            if (i != middleCards.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println(" ////////");
    }

    // Add a card from the deck to the middle cards
    private void revealCard() {
        middleCards.add(pile.remove(0));
    }

    // Ask each player their desired move, and discern bets accordingly
    private void gatherBets(int startingSpot) {
        for(int i = startingSpot; i < playerCount; i++) {
            char choice = '0';

            // Gather player's valid choice
            while (choice != 'c' && choice != 'r' && choice != 'f') {
                System.out.println(players.get(i).getName() + ", do you want to (f)old, (c)heck/call, or (r)aise?");
                Scanner input = new Scanner(System.in);
                choice = input.nextLine().charAt(0);
            }

            // Fold / Check or Call / Raise
            switch (choice) {
                case 'f':
                    // Remove player from players arraylist
                    System.out.println(players.get(i).getName() + " folded out of the round");
                    players.remove(i);
                    playerCount--;
                    i--;
                    break;
                case 'c':
                    // Set bet of player to overall bet
                    players.get(i).setBet(bet);
                    System.out.println(players.get(i).getName() + " matched to a bet of $" + bet);
                    break;
                case 'r':
                    // Wait for user to enter a bet higher than current bet
                    int newBet = -1;

                    while (newBet <= bet) {
                        System.out.println("what would you like to raise the bet to? Must be greater than the current bet. ");
                        Scanner input = new Scanner(System.in);
                        newBet = input.nextInt();
                    }

                    // Set the overall bet to the raised value
                    bet = newBet;
                    players.get(i).setBet(bet);
                    System.out.println(players.get(i).getName() + " raise the a bet to $" + bet);
                    break;
            }
        }
        // Make sure all players have an even bet. If not, recursively recall the function
        for(int k = 0; k < playerCount; k++) {
            if(players.get(k).getBet() != bet) {
                gatherBets(k);
            }
        }
    }

    // Reveal the ranks of the players and their representative bets
    private void grandReveal() {
        showCards();
        for (int i = 0; i < playersCopy.size(); i++) {
            System.out.print(playersCopy.get(i).getName() + " had a ");
            System.out.println(playersCopy.get(i).getHandString());
            playersCopy.get(i).determineRank(middleCards);
            System.out.println("They got a " + ranks[playersCopy.get(i).getRank()]);
            System.out.println("They bet " + playersCopy.get(i).getBet());
        }
    }

    // Print the instructions of poker
    private static void printInstructions() {
        System.out.println("WELCOME TO POKER wahwahwahh");
    }

    // Getter
    public ArrayList<Card> getMiddleCards() {
        return middleCards;
    }

    public static void main(String[] args) {
        Game poker = new Game();
    }
}



