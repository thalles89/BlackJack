import model.*;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BalckJackGame {

    public static void main(String[] args) throws ClassNotFoundException {

        DeckPile stack = new DeckPile();

        for (int i = 0; i < 4; i++) {
            Deck deck = new Deck();
            deck.addToStack(stack);
            stack.shuffle();
        }

        Hand dealerHand = new Hand();
        BalckJackDealer dealer = new BalckJackDealer("Dealer", dealerHand, stack);

        Hand humanHand = new Hand();
        HumanPlayer human = new HumanPlayer("Human", humanHand);

        dealer.addListener(Console.INSTANCE);
        human.addListener(Console.INSTANCE);

        dealer.addPlayer(human);
        dealer.addPlayer(dealer);
        dealer.newGame();
    }

}
