package gui.mvc;

import interfaces.PlayerListener;
import model.BlackJackDealer;
import model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionViewController implements ActionListener, PlayerListener {

    private GUIPlayer model;
    private OptionView view;
    private BlackJackDealer dealer;

    public OptionViewController(GUIPlayer model, BlackJackDealer dealer, OptionView view) {
        this.model = model;
        this.model.addListener(this);
        this.dealer = dealer;
        this.view = view;
        this.view.enablePlayerControls(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {
            case OptionView.QUIT -> System.exit(0);

            case OptionView.HIT -> {
                view.enableDoubleDown(false);
                model.takeCard();
            }
            case OptionView.STAND -> {
                view.enableDoubleDown(false);
                model.stand();
            }
            case OptionView.NEW_GAME -> {
                view.enableDoubleDown(false);
                view.enableGameControls(false);
                view.enablePlayerControls(false);
                view.enableBettingControls(true);

                dealer.newGame();
            }
            case OptionView.BET10 -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(true);
                view.enableDoubleDown(true);
                model.place10Bet();
            }
            case OptionView.BET50 -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(true);
                view.enableDoubleDown(true);
                model.place50Bet();
            }
            case OptionView.BET100 -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(true);
                view.enableDoubleDown(true);
                model.place100Bet();
            }
            case OptionView.DOUBLEDOWN -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(false);
                view.enableGameControls(true);
                view.enableDoubleDown(false);
                model.doubleDown(dealer);
            }
        }

    }

    @Override
    public void playerChanged(Player player) {

    }

    @Override
    public void playerBusted(Player player) {
        view.enablePlayerControls(false);
        view.enableGameControls(true);
        view.enableDoubleDown(false);
    }

    @Override
    public void playerStanding(Player player) {
        view.enablePlayerControls(false);
        view.enableGameControls(true);
    }

    @Override
    public void playerBlackjack(Player player) {
        view.enablePlayerControls(false);
        view.enableGameControls(true);
        view.enableDoubleDown(false);
    }

    @Override
    public void playerWon(Player player) {
        view.enablePlayerControls(false);
        view.enableGameControls(true);
    }

    @Override
    public void playerLost(Player player) {
        view.enablePlayerControls(false);
        view.enableGameControls(true);
        view.enableDoubleDown(false);
    }

    @Override
    public void playerStandOff(Player player) {
        view.enablePlayerControls(false);
        view.enableGameControls(true);
    }
}
