package br.com.thalles89.gui.mvc;

import br.com.thalles89.interfaces.PlayerListener;
import br.com.thalles89.model.BlackJackDealer;
import br.com.thalles89.model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionViewController implements ActionListener, PlayerListener {

    private GUIPlayer guiPlayer;
    private OptionView view;
    private BlackJackDealer dealer;

    public OptionViewController(GUIPlayer guiPlayer, BlackJackDealer dealer, OptionView view) {
        this.guiPlayer = guiPlayer;
        this.guiPlayer.addListener(this);
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
                guiPlayer.takeCard();
            }
            case OptionView.STAND -> {
                view.enableDoubleDown(false);
                guiPlayer.stand();
            }
            case OptionView.NEW_GAME -> {
                view.enableDoubleDown(false);
                view.enableGameControls(false);
                view.enablePlayerControls(false);
                view.enableSplitControls(false);
                view.enableBettingControls(true);

                dealer.newGame();
            }
            case OptionView.BET10 -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(true);
                view.enableDoubleDown(true);
                guiPlayer.place10Bet();
            }
            case OptionView.BET50 -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(true);
                view.enableDoubleDown(true);
                guiPlayer.place50Bet();
            }
            case OptionView.BET100 -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(true);
                view.enableDoubleDown(true);
                guiPlayer.place100Bet();
            }
            case OptionView.DOUBLEDOWN -> {
                view.enableBettingControls(false);
                view.enablePlayerControls(false);
                view.enableGameControls(true);
                view.enableDoubleDown(false);
                guiPlayer.doubleDown(dealer);
            }
            case OptionView.SPLITHAND -> {
                view.enableBettingControls(true);
                guiPlayer.trySplitHand();
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
