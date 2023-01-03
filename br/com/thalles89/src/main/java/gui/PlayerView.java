package gui;

import interfaces.PlayerListener;
import model.Card;
import model.Hand;
import model.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class PlayerView extends JPanel implements PlayerListener {

    private JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private TitledBorder titledBorder;

    public PlayerView(Player player) {
        super(new BorderLayout());
        buildGUI(player);
        player.addListener(this);
    }

    @Override
    public void playerChanged(Player player) {
        titledBorder.setTitle(getName());
        cards.removeAll();
        Hand hand = player.getHand();

        List<Card> cardList = hand.getCards();

        cardList.forEach(c -> {
            VCard card = (VCard) c;
            JLabel label = new CardView(card);
            cards.add(label);
        });
        revalidate();
        repaint();
    }

    @Override
    public void playerBusted(Player player) {
        titledBorder.setTitle(player.getName() + " Busted!!!");
        cards.repaint();
    }

    @Override
    public void playerStanding(Player player) {
        titledBorder.setTitle(player.getName() + " Standing!!!");
        cards.repaint();

    }

    @Override
    public void playerBlackjack(Player player) {
        titledBorder.setTitle(player.getName() + " Blackjack!!!");
        cards.repaint();

    }

    @Override
    public void playerWon(Player player) {
        titledBorder.setTitle(player.getName() + " Won!!!");
        cards.repaint();

    }

    @Override
    public void playerLost(Player player) {
        titledBorder.setTitle(player.getName() + " Lost!!!");
        cards.repaint();

    }

    @Override
    public void playerStandOff(Player player) {
        titledBorder.setTitle(player.getName() + " StandOff!!!");
        cards.repaint();

    }

    private void buildGUI(Player player) {
        add(cards);
        titledBorder = new TitledBorder(player.getName());
        cards.setBorder(titledBorder);
        cards.setBackground(new Color(35, 142, 35));
        titledBorder.setTitleColor(Color.BLACK);
    }
}
