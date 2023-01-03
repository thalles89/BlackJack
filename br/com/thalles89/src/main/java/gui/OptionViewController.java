package gui;

import interfaces.PlayerListener;
import model.BlackJackDealer;
import model.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionViewController implements ActionListener, PlayerListener {

    public OptionViewController(GUIPlayer player, BlackJackDealer dealer, OptionView optionView) {
    }

    @Override
    public void playerChanged(Player player) {

    }

    @Override
    public void playerBusted(Player player) {

    }

    @Override
    public void playerStanding(Player player) {

    }

    @Override
    public void playerBlackjack(Player player) {

    }

    @Override
    public void playerWon(Player player) {

    }

    @Override
    public void playerLost(Player player) {

    }

    @Override
    public void playerStandOff(Player player) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
