package gui.pac;

import interfaces.Dealer;
import interfaces.Displayable;
import interfaces.PlayerListener;
import interfaces.PlayerState;
import model.Bank;
import model.BlackJackDealer;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIPlayer extends VBettingPlayer implements Displayable {

    private BlackJackDealer dealer;
    private JComponent view;
    Color FOREST_GREEN = new Color(35, 142, 35);

    public GUIPlayer(String name, VHand hand, Bank bank, VBlackJackDealer blackJackDealer) {
        super(name, hand, bank);
        this.dealer = blackJackDealer;
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        return true;
    }

    @Override
    protected void bet() {

    }

    public void place10Bet() {
        getBank().place10Bet();
        setCurrentState(getWaitingState());
        dealer.doneBetting(this);
    }

    public void place50Bet() {
        getBank().place50Bet();
        setCurrentState(getWaitingState());
        dealer.doneBetting(this);
    }

    public void place100Bet() {
        getBank().place100Bet();
        setCurrentState(getWaitingState());
        dealer.doneBetting(this);
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        setCurrentState(getDoublingDownState());
        getDoublingDownState().execute(dealer);
        return true;
    }

    public void takeCard() {
        dealer.hit(this);
    }

    public void stand() {
        setCurrentState(getStandingState());
        notifyStanding();
        getCurrentState().execute(dealer);
    }

    @Override
    protected PlayerState getPlayingState() {
        return new Playing();
    }

    @Override
    protected PlayerState getBettingState() {
        return new Betting();
    }

    @Override
    public JComponent view() {
        if (view == null) {
            view = new JPanel(new BorderLayout());
            JComponent pv = super.view();
            GUIView cv = new GUIView();
            addListener(cv);
            view.add(pv, BorderLayout.CENTER);
            view.add(cv, BorderLayout.SOUTH);
        }
        return view;
    }

    private class Playing implements PlayerState {

        @Override
        public void handPlayable() {

        }

        @Override
        public void handBlackjack() {
            setCurrentState(getBlackjackState());
            notifyBlackJack();
            getCurrentState().execute(dealer);
        }

        @Override
        public void handBusted() {
            setCurrentState(getBustedState());
            notifyBusted();
            getCurrentState().execute(dealer);
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {

        }
    }

    private static class Betting implements PlayerState {

        @Override
        public void handPlayable() {

        }

        @Override
        public void handBlackjack() {

        }

        @Override
        public void handBusted() {

        }

        @Override
        public void handChanged() {

        }

        @Override
        public void execute(Dealer dealer) {

        }
    }


    private class GUIView extends JPanel implements PlayerListener, ActionListener {

        public static final String NEW_GAME = "NEW";
        public static final String QUIT = "QUIT";
        public static final String STAND = "STAND";
        public static final String HIT = "HIT";
        public static final String BET10 = "BET10";
        public static final String BET50 = "BET50";
        public static final String BET100 = "BET100";
        public static final String DOUBLEDOWN = "DOUBLE";

        public static final JButton bet10 = new JButton("$10");
        public static final JButton bet50 = new JButton("$50");
        public static final JButton bet100 = new JButton("$100");
        public static final JButton newGame = new JButton("New Game");
        public static final JButton quit = new JButton("Quit");
        public static final JButton hit = new JButton("Hit");
        public static final JButton stand = new JButton("Stand");
        public static final JButton doubledown = new JButton("Double Down");

        public GUIView() {
            super(new BorderLayout());
            GUIPlayer.this.addListener(this);
            buildGUI();
        }

        private void buildGUI() {
            JPanel bettingControls = new JPanel();
            JPanel gameControls = new JPanel();

            add(bettingControls, BorderLayout.NORTH);
            add(gameControls, BorderLayout.SOUTH);

            bettingControls.setBackground(FOREST_GREEN);
            gameControls.setBackground(FOREST_GREEN);
            newGame.setActionCommand(NEW_GAME);
            newGame.addActionListener(this);
            quit.addActionListener(this);
            quit.setActionCommand(QUIT);
            stand.setActionCommand(STAND);
            stand.addActionListener(this);
            doubledown.setActionCommand(DOUBLEDOWN);
            doubledown.addActionListener(this);
            hit.setActionCommand(HIT);
            hit.addActionListener(this);
            bet10.setActionCommand(BET10);
            bet10.addActionListener(this);
            bet50.setActionCommand(BET50);
            bet50.addActionListener(this);
            bet100.setActionCommand(BET100);
            bet100.addActionListener(this);
            bettingControls.add(bet10);
            bettingControls.add(bet50);
            bettingControls.add(bet100);
            gameControls.add(doubledown);
            gameControls.add(hit);
            gameControls.add(stand);
            gameControls.add(quit);
            gameControls.add(newGame);
            enableBettingControls(false);
            enableDoubleDown(false);
            enablePlayerControls(false);
            enableGameControls(true);
        }

        private void enableGameControls(boolean b) {
            quit.setEnabled(b);
            newGame.setEnabled(b);
        }

        private void enablePlayerControls(boolean b) {
            hit.setEnabled(b);
            stand.setEnabled(b);
        }

        private void enableDoubleDown(boolean b) {
            doubledown.setEnabled(b);
        }

        private void enableBettingControls(boolean b) {
            bet10.setEnabled(b);
            bet50.setEnabled(b);
            bet100.setEnabled(b);
        }


        @Override
        public void actionPerformed(ActionEvent event) {
            switch (event.getActionCommand()) {
                case QUIT -> System.exit(0);

                case HIT -> {
                    enableDoubleDown(false);
                    takeCard();
                }
                case STAND -> {
                    enableDoubleDown(false);
                    stand();
                }
                case NEW_GAME -> {
                    enableDoubleDown(false);
                    enableGameControls(false);
                    enablePlayerControls(false);
                    enableBettingControls(true);
                    dealer.newGame();
                }
                case BET10 -> {
                    enableBettingControls(false);
                    enablePlayerControls(true);
                    enableDoubleDown(true);
                    place10Bet();
                }
                case BET50 -> {
                    enableBettingControls(false);
                    enablePlayerControls(true);
                    enableDoubleDown(true);
                    place50Bet();
                }
                case BET100 -> {
                    enableBettingControls(false);
                    enablePlayerControls(true);
                    enableDoubleDown(true);
                    place100Bet();
                }
                case DOUBLEDOWN -> {
                    enableBettingControls(false);
                    enablePlayerControls(false);
                    enableGameControls(true);
                    enableDoubleDown(false);
                    doubleDown(dealer);
                }
            }
        }

        @Override
        public void playerChanged(Player player) {

        }

        @Override
        public void playerBusted(Player player) {
            enablePlayerControls(false);
            enableGameControls(true);
        }

        @Override
        public void playerStanding(Player player) {
            enablePlayerControls(false);
            enableGameControls(true);
        }

        @Override
        public void playerBlackjack(Player player) {
            enableDoubleDown(false);
            enablePlayerControls(false);
            enableGameControls(true);
        }

        @Override
        public void playerWon(Player player) {
            enablePlayerControls(false);
            enableGameControls(true);
        }

        @Override
        public void playerLost(Player player) {
            enableDoubleDown(false);
            enablePlayerControls(false);
            enableGameControls(true);
        }

        @Override
        public void playerStandOff(Player player) {
            enablePlayerControls(false);
            enableGameControls(true);
        }
    }
}
