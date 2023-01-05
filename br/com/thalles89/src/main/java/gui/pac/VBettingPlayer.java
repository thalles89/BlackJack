package gui.pac;

import interfaces.Dealer;
import interfaces.Displayable;
import interfaces.PlayerListener;
import model.Bank;
import model.BettingPlayer;
import model.Hand;
import model.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public abstract class VBettingPlayer extends BettingPlayer implements Displayable {

    private BettingView view;

    public VBettingPlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        return true;
    }

    @Override
    protected void bet() {

    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        return true;
    }

    @Override
    public JComponent view() {
        if (view == null) {
            view = new BettingView((VHand) getHand());
            addListener(view);
        }
        return view;
    }

    private class BettingView extends JPanel implements PlayerListener {
        private TitledBorder border;

        public BettingView(VHand hand) {
            super(new FlowLayout(FlowLayout.LEFT));
            buildGUI(hand.view());
        }

        @Override
        public void playerChanged(Player player) {
            border.setTitle(VBettingPlayer.this.getName());
            repaint();
        }

        @Override
        public void playerBusted(Player player) {
            border.setTitle(VBettingPlayer.this.getName() + " Busted!!!");
            repaint();
        }

        @Override
        public void playerBlackjack(Player player) {
            border.setTitle(VBettingPlayer.this.getName() + " Blackjack!!!");
            repaint();
        }

        @Override
        public void playerStanding(Player player) {
            border.setTitle(VBettingPlayer.this.getName() + " Standing!!!");
            repaint();
        }

        @Override
        public void playerWon(Player player) {
            border.setTitle(VBettingPlayer.this.getName() + " Won!!!");
            repaint();
        }

        @Override
        public void playerLost(Player player) {
            border.setTitle(VBettingPlayer.this.getName() + " Lost!!!");
            repaint();
        }

        @Override
        public void playerStandOff(Player player) {
            border.setTitle(VBettingPlayer.this.getName() + " StandOff!!!");
            repaint();
        }

        private void buildGUI(JComponent hand) {
            border = new TitledBorder(VBettingPlayer.this.getName());
            setBorder(border);
            setBackground(new Color(35,142,35));
            border.setTitleColor(Color.BLACK);
            add(hand);
        }

    }


}
