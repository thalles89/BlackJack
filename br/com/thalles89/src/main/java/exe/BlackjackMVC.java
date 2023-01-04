package exe;

import core.threaded.ThreadedBlackjackDealer;
import gui.mvc.GUIPlayer;
import gui.mvc.OptionView;
import gui.mvc.PlayerView;
import gui.mvc.VDeck;
import model.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class BlackjackMVC extends JFrame {

    private static final Color FOREST_GREEN = new Color(35, 140, 35);

    public static void main(String[] args) {
        JFrame frame = new BlackjackMVC();
        frame.getContentPane().setBackground(FOREST_GREEN);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    private BlackJackDealer dealer;
    private GUIPlayer human;
    private JPanel players = new JPanel(new GridLayout(0,1));


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

    private PlayerView getPlayerView(Player player) {
        PlayerView view = new PlayerView(player);
        view.setBackground(FOREST_GREEN);
        return view;
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

    private BlackJackDealer getDealer() {
        if(dealer == null){
            dealer = new ThreadedBlackjackDealer("Dealer",
                    new Hand(),
                    getCards());
        }
        return dealer;
    }

    private GUIPlayer getHuman() {
        if(human == null){
            Hand hand = new Hand();
            human = new GUIPlayer("Player", hand, new Bank(1000));
        }
        return human;
    }

    private DeckPile getCards() {
        DeckPile cards = new DeckPile();
        for (int i=0; i<4; i++){
            cards.shuffle();
            Deck deck = new VDeck();
            deck.addToStack(cards);
            cards.shuffle();
        }
        return cards;
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
