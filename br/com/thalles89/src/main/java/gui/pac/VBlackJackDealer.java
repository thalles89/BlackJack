package gui.pac;

import model.BlackJackDealer;
import threaded.ThreadedBlackjackDealer;
import interfaces.Displayable;
import interfaces.PlayerListener;
import model.DeckPile;
import model.Hand;
import model.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class VBlackJackDealer extends ThreadedBlackjackDealer implements Displayable {

private DealerView view;

    public VBlackJackDealer(String name, Hand hand, DeckPile pile) {
        super(name, hand, pile);
    }

    @Override
    public void newGame() {
        reset();
        play(this);
    }



    @Override
    public JComponent view() {
        if (view == null) {
            view = new DealerView((VHand) getHand());
            addListener(view);
        }
        return view;
    }

    private class DealerView extends JPanel implements PlayerListener {

        private TitledBorder border;
        String name = VBlackJackDealer.this.getName();
        public DealerView(VHand hand) {
            super(new FlowLayout(FlowLayout.LEFT));
            border = new TitledBorder(name);
            setBorder(border);
            setBackground(new Color(35,142,35));
            border.setTitleColor(Color.black);
            add(hand.view());
            repaint();
        }

        @Override
        public void playerChanged(Player player) {
            border.setTitle(name);
            repaint();
        }

        @Override
        public void playerBusted(Player player) {
            border.setTitle(name + " BUSTED");
            repaint();
        }

        @Override
        public void playerStanding(Player player) {
            border.setTitle(name + " STANDING");
            repaint();
        }

        @Override
        public void playerBlackjack(Player player) {
            border.setTitle(name + " BLACKJACK");
            repaint();
        }

        @Override
        public void playerWon(Player player) {
            border.setTitle(name + " WON");
            repaint();
        }

        @Override
        public void playerLost(Player player) {
            border.setTitle(name + " LOST");
            repaint();
        }

        @Override
        public void playerStandOff(Player player) {
            border.setTitle(name + " DROPPOFF");
            repaint();
        }
    }
}
