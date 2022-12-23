import model.Card;
import model.Dealer;
import model.Deck;
import model.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Thalles
 * @version 0.0.1
 * */
public class App {

    public static void main(String[] args) throws ClassNotFoundException {

        Deck d = new Deck();
        Dealer dealer = new Dealer(d);
        dealer.shuffle(d);

        List<Player> players = List.of(
                new Player("Thalles"),
                new Player("Dealer")

        );

        players.forEach(player -> {
            List<Card> cardsDealdToPLayer = new ArrayList<>();
            cardsDealdToPLayer.add(dealer.dealCard());
            cardsDealdToPLayer.add(dealer.dealCard());
            player.receiveCards(cardsDealdToPLayer);
        });

        players.forEach(player -> {
            System.out.println(player.getName());
            player.seeCards().forEach(card -> System.out.println(card.display()));
        });
//TODO Fazer a iteração de jogadores e dealer
    }

}
