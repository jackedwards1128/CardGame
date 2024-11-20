import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int playerCount;
    private Deck pile;
    private ArrayList<Player> players;

    public static void main(String[] args) {
        Game poker = new Game();
    }

    public Game() {
        players = new ArrayList<Player>();
        Deck deck = new Deck();
        deck.shuffle();
        this.pile = deck;
        this.beginGame();
    }

    private void beginGame() {
        printInstructions();

        Scanner input = new Scanner(System.in);

        System.out.println("How many players will be participating ? ");

        playerCount = input.nextInt();

        for (int i = 0; i < playerCount; i++) {
            System.out.println("What is player " + (i+1) + "'s name? ");
            String playerName = input.nextLine();

            players.add(new Player(playerName, i));

            ArrayList<Card> pocket = new ArrayList<Card>();

            // pull two cards out of the pile and put them into a player's pocket
            pocket.add(pile.remove(0));
            pocket.add(pile.remove(0));

            players.get(i).createHand(pocket);


            input.nextLine();
        }

        showHands();

    }

    private void showHands() {
        for (int i = 0; i < playerCount; i++) {
            Scanner input = new Scanner(System.in);

            System.out.println("click enter once you are ready to see your hand");
            input.nextLine();

            System.out.print(players.get(i).getName() + " has a ");
            System.out.println(players.get(i).getHandString());

            input.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        }
    }

    private static void printInstructions() {
        System.out.println("WELCOME TO POKER wahwahwahh");
    }
}



