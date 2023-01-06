package gui.mvc;

import interfaces.PlayerListener;
import model.Card;
import model.Hand;
import model.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PlayerView extends JPanel implements PlayerListener {

    private JPanel cards = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private TitledBorder border;

    public PlayerView(Player player) {
        super(new BorderLayout());
        buildGUI(player);
        player.addListener(this);
    }

    @Override
    public void playerChanged(Player player) {
        border.setTitle(player.getName());
        cards.removeAll();

        Hand hand = player.getHand();

        List<Card> cardList = hand.getCards();

        cardList.forEach(c -> {
            VCard card = (VCard) c;
            JLabel label = new CardView(card);
            cards.add(label);
        });
        System.out.println(player.getHand());
        revalidate();
        repaint();
    }

    @Override
    public void playerBusted(Player player) {
        border.setTitle(player.getName() + " Busted!!!");
        cards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerBlackjack(Player player) {
        border.setTitle(player.getName() + " Blackjack!!!");
        cards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerStanding(Player player) {
        border.setTitle(player.getName() + " Standing!!!");
        cards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerWon(Player player) {
        border.setTitle(player.getName() + " Won!!!");
        cards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerLost(Player player) {
        border.setTitle(player.getName() + " Lost!!!");
        cards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerStandOff(Player player) {
        border.setTitle(player.getName() + " StandOff!!!");
        cards.repaint();
        revalidate();
        repaint();
    }

    private void buildGUI(Player player) {
        add(cards, BorderLayout.NORTH);
        border = new TitledBorder(player.getName());
        cards.setBorder(border);
        cards.setBackground(new Color(35, 142, 35));
        border.setTitleColor(Color.BLACK);
        cards.repaint();
        revalidate();
        repaint();
    }
}
