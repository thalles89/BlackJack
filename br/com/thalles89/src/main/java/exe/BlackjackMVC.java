package exe;

import threaded.ThreadedBlackjackDealer;
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
import java.util.Objects;

public class BlackjackMVC extends JFrame {

    private static final Color FOREST_GREEN = new Color(35, 140, 35);

    public static void main(String[] args) {
        JFrame frame = new BlackjackMVC();
        frame.getContentPane().setBackground(FOREST_GREEN);
        frame.setSize(1024, 768);
        frame.setVisible(true);
        frame.setTitle("BlackJack");
    }

    private BlackJackDealer dealer;
    private GUIPlayer human;
    OptimalPlayer optimal;
    SafePlayer safe;
    FlipPlayer flip;
    SmartPlayer smartPlayer;
    private JPanel players = new JPanel(new GridLayout(0, 1));


    public BlackjackMVC() {
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

        GUIPlayer player1 = getHuman();
        PlayerView v2 = getPlayerView(player1);

        SafePlayer player2 = getSafePlayer();
        PlayerView v3 = getPlayerView(player2);

        OptimalPlayer player3 = getOptimalPlayer();
        PlayerView v4 = getPlayerView(player3);

        dealer.addPlayer(player1);
//        dealer.addPlayer(player2);
//        dealer.addPlayer(player3);

        List<PlayerView> views = List.of(v1, v2);
//        List<PlayerView> views = List.of(v1, v2, v3, v4);

        addPlayers(views);

        addOptionView(player1, dealer);
    }

    private BlackJackDealer getDealer() { // TODO REFATORAR DEALER MULTITRHEAD
        if (dealer == null) {
            dealer = new BlackJackDealer("Dealer",
                    new Hand(),
                    getCards());
        }
        return dealer;
    }

    private GUIPlayer getHuman() {
        if (human == null) {

            String input = popupMessage();

            if (Objects.nonNull(input) && !input.equals("")) {
                Hand hand = new Hand();
                human = new GUIPlayer(input, hand, new Bank(1000));
            }
            return human;

        }
        return null;
    }

    private String popupMessage() {
        return JOptionPane.showInputDialog(
                null,
                "Como quer ser chamado",
                "Qual é seu nome?",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private OptimalPlayer getOptimalPlayer() {
        if (optimal == null) {
            Hand hand = new Hand();
            optimal = new OptimalPlayer("Frank", hand, new Bank(1000));
        }
        return optimal;
    }

    private SafePlayer getSafePlayer() {
        if (safe == null) {
            Hand hand = new Hand();
            safe = new SafePlayer("Jhon", hand, new Bank(1000));
        }
        return safe;
    }

    private FlipPlayer getFlipPlayer() {
        if (flip == null) {
            Hand hand = new Hand();
            flip = new FlipPlayer("Mark", hand, new Bank(1000));
        }
        return flip;
    }

    private DeckPile getCards() {
        DeckPile cards = new DeckPile();
        for (int i = 0; i < 4; i++) {
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
        getContentPane().add(players, BorderLayout.NORTH);
    }

    private void addOptionView(GUIPlayer player, BlackJackDealer dealer) {
        OptionView optionView = new OptionView(player, dealer);
        optionView.setBackground(FOREST_GREEN);
        getContentPane().add(optionView, BorderLayout.SOUTH);
    }

}
