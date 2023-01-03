package gui.mvc;

import model.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static gui.mvc.OptionView.FOREST_GREEN;

public class BlackjackMVC extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new BlackjackMVC();
        frame.getContentPane().setBackground(FOREST_GREEN);
        frame.setSize(580, 480);
        frame.setVisible(true);
    }

    private BlackJackDealer dealer;
    private GUIPlayer human;
    private JPanel players = new JPanel(new GridBagLayout());


    public BlackjackMVC(){
        setUp();
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wa);
    }

    private void setUp() {

        BlackJackDealer dealer = getDealer();
        PlayerView v1 = getPlayerView(dealer);

        GUIPlayer player = getHuman();
        PlayerView v2 = getPlayerView(player);


        List<PlayerView> views = List.of(v1,v2);

        addPlayers(views);
        dealer.addPlayer(player);
        addOptionView(player, dealer);
    }

    private GUIPlayer getHuman() {
        if(human == null){
            Hand hand = new Hand();
            Bank bank = new Bank(1000);
            human = new GUIPlayer("dealer", hand, bank); // TODO mudar para o ThreadSafe
        }
        return human;
    }

    private PlayerView getPlayerView(Player player) {
        PlayerView view = new PlayerView(player);
        view.setBackground(FOREST_GREEN);
        return view;
    }

    private BlackJackDealer getDealer() {
        if(dealer == null){
            Hand dealerHand = new Hand();
            DeckPile cards = getCards();
            dealer = new BlackJackDealer("dealer", dealerHand, cards); // TODO mudar para o ThreadSafe
        }
        return dealer;
    }

    private DeckPile getCards() {
        DeckPile pile = new DeckPile();
        for (int i=0; i<4; i++){
            pile.shuffle();
            Deck deck = new VDeck();
            deck.addToStack(pile);
            pile.shuffle();
        }
        return pile;
    }

    private void addPlayers(List<PlayerView> views) {
        players.setBackground(FOREST_GREEN);
        views.forEach(p -> players.add(p));
        getContentPane().add(players, BorderLayout.CENTER);
    }

    private void addOptionView(GUIPlayer player, BlackJackDealer dealer) {
        OptionView optionView = new OptionView(player, dealer);
        optionView.setBackground(FOREST_GREEN);
        getContentPane().add(optionView, BorderLayout.SOUTH);
    }


}
