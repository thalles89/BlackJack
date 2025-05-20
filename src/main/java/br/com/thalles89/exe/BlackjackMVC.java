package br.com.thalles89.exe;

import br.com.thalles89.gui.mvc.*;
import br.com.thalles89.model.*;

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
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("BlackJack");
    }

    BlackJackDealer dealer;
    GUIPlayer human;
    OptimalPlayer optimal;
    SafePlayer safe;
    FlipPlayer flip;
    SmartPlayer smartPlayer;
    private final JPanel players = new JPanel(new GridLayout(0, 2));



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
        PlayerView vDealer = getPlayerView(dealer);

        GUIPlayer human = getHuman();
        PlayerView v1 = getPlayerView(human);

        SafePlayer player2 = getSafePlayer();
        PlayerView v2 = getPlayerView(player2);

        Player player3 = getOptimalPlayer();
        PlayerView v3 = getPlayerView(player3);

        Player player4 = getFlipPlayer();
        PlayerView v4 = getPlayerView(player4);

        Player player5 = getSmartPlayer();
        PlayerView v5 = getPlayerView(player5);


        dealer.addPlayer(human);
        dealer.addPlayer(player2);
        dealer.addPlayer(player3);
        dealer.addPlayer(player4);
        dealer.addPlayer(player5);

        addPlayers(List.of(vDealer, v1, v2, v3, v4, v5));
        addOptionView(human, dealer);
    }

    private BlackJackDealer getDealer() {
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

            Hand hand = new Hand();
            if (Objects.nonNull(input) && !input.isEmpty()) {
                human = new MultiHandPlyer(input, hand, new Bank(1000));
            }else{
                human = new MultiHandPlyer("Player 1", hand, new Bank(1000));
            }
        }
        return human;
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

    private SmartPlayer getSmartPlayer() {
        if (smartPlayer == null) {
            Hand hand = new Hand();
            smartPlayer = new SmartPlayer("Josiah", hand, new Bank(1000));
        }
        return smartPlayer;
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
        views.forEach(players::add);
        getContentPane().add(players, BorderLayout.CENTER);
    }

    private void addOptionView(GUIPlayer player, BlackJackDealer dealer) {
        OptionView optionView = new OptionView(player, dealer);
        optionView.setBackground(FOREST_GREEN);
        getContentPane().add(optionView, BorderLayout.SOUTH);
    }

}
