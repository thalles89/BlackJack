package br.com.thalles89.gui.mvc;

import br.com.thalles89.interfaces.PlayerListener;
import br.com.thalles89.model.Card;
import br.com.thalles89.model.Hand;
import br.com.thalles89.model.Player;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PlayerView extends JPanel implements PlayerListener {

    private final JPanel panelOfCards = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private TitledBorder border;

    public PlayerView(Player player) {
        super(new BorderLayout());
        buildGUI(player);
        player.addListener(this);
    }

    @Override
    public void playerChanged(Player player) {
        border.setTitle(player.getName());
        border.setTitleColor(Color.WHITE);
        panelOfCards.removeAll();

        Hand hand = player.getHand();

        List<Card> cardList = hand.getCards();

        cardList.forEach(c -> {
            VCard card = (VCard) c;
            JLabel label = new CardView(card);
            panelOfCards.add(label);
        });
        revalidate();
        repaint();
    }

    @Override
    public void playerBusted(Player player) {
        border.setTitle(player.getName() + " Busted!!!");
        panelOfCards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerBlackjack(Player player) {
        border.setTitle(player.getName() + " Blackjack!!!");
        panelOfCards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerStanding(Player player) {
        border.setTitle(player.getName() + " Standing!!!");
        panelOfCards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerWon(Player player) {
        border.setTitle(player.getName() + " Won!!!");
        border.setTitleColor(Color.cyan);
        panelOfCards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerLost(Player player) {
        border.setTitle(player.getName() + " Lost!!!");
        border.setTitleColor(Color.red);
        panelOfCards.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void playerStandOff(Player player) {
        border.setTitle(player.getName() + " StandOff!!!");
        border.setTitleColor(Color.darkGray);
        panelOfCards.repaint();
        revalidate();
        repaint();
    }

    private void buildGUI(Player player) {
        add(panelOfCards, BorderLayout.NORTH);
        add(panelOfCards, BorderLayout.CENTER);
        border = new TitledBorder(player.getName());
        panelOfCards.setBorder(border);
        panelOfCards.setBackground(new Color(0, 142, 35));
        border.setTitleColor(Color.BLACK);
        panelOfCards.repaint();
        revalidate();
        repaint();
    }
}
