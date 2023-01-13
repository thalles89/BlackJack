package exe;

import model.*;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BlackJackGame {

    private static Boolean playAgain(){

        while (true) {
            Console.INSTANCE.printMessage("Would like to play?\n[Y]es / [N]o");
            String response = Console.INSTANCE.readInput("invalid");
            if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            }
        }
        
    }

    public static void main(String[] args) {

        DeckPile stack = new DeckPile();

        for (int i = 0; i < 4; i++) {
            Deck deck = new Deck();
            deck.addToStack(stack);
            stack.shuffle();
        }

        Hand dealerHand = new Hand();
        BlackJackDealer dealer = new BlackJackDealer("Dealer", dealerHand, stack);

        Hand player1Hand = new Hand();
        CommandLinePlayer player1 = new CommandLinePlayer("player1", player1Hand, new Bank(1000));
        player1.addListener(Console.INSTANCE);
        Hand player2Hand = new Hand();
        Player player2 = new SafePlayer("player2", player2Hand, new Bank(1000));
        player2.addListener(Console.INSTANCE);
        Hand player3Hand = new Hand();
        Player player3 = new OptimalPlayer("player3", player3Hand, new Bank(1000));
        player3.addListener(Console.INSTANCE);

        dealer.addListener(Console.INSTANCE);

        dealer.addPlayer(player1);
        dealer.addPlayer(player2);
        dealer.addPlayer(player3);

        do {
            dealer.newGame();
        }while (playAgain());

    }

}
