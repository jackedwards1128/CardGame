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
        this.beginGame();
        Deck deck = new Deck();
        deck.shuffle();
        this.pile = deck;
    }

    private void beginGame() {
        printInstructions();

        Scanner input = new Scanner(System.in);

        System.out.println("How many players will be participating ? ");

        playerCount = input.nextInt();
    }

    private static void printInstructions() {
        System.out.println("WELCOME TO POKER wahwahwahh");
    }
}
