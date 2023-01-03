package gui;

import model.BlackJackDealer;

import javax.swing.*;
import java.awt.*;

public class OptionView extends JPanel {

    public static final String NEW_GAME = "NEW";
    public static final String QUIT = "QUIT";
    public static final String STAND = "STAND";
    public static final String HIT = "HIT";
    public static final String BET10 = "BET10";
    public static final String BET50 = "BET50";
    public static final String BET100 = "BET100";
    public static final String DD = "DOUBLE";

    public static final JButton bet10 = new JButton("$10");
    public static final JButton bet50 = new JButton("$50");
    public static final JButton bet100 = new JButton("$100");
    public static final JButton deal = new JButton(" New Game");
    public static final JButton quit = new JButton("Quit");
    public static final JButton hit = new JButton("Hit");
    public static final JButton stand = new JButton("Stand");
    public static final JButton doubledown = new JButton("Double Down");
    private GUIPlayer player;
    private BlackJackDealer dealer;

    private static final Color FOREST_GREEN = new Color(47, 153, 36);

    public OptionView(GUIPlayer player, BlackJackDealer dealer){
        super(new BorderLayout());
        this.player = player;
        this.dealer = dealer;
        attachController(makeController());
        buildGui();
    }

    public void attachController(OptionViewController controller){
        deal.addActionListener(controller);
        quit.addActionListener(controller);
        hit.addActionListener(controller);
        stand.addActionListener(controller);
        doubledown.addActionListener(controller);
        bet10.addActionListener(controller);
        bet50.addActionListener(controller);
        bet100.addActionListener(controller);
    }

    public void enableDoubleDown(boolean enable){
        doubledown.setEnabled(enable);
    }

    public void enableBettingControls(boolean enable){
        bet10.setEnabled(enable);
        bet50.setEnabled(enable);
        bet100.setEnabled(enable);
    }

    public void enablePlayerControls(boolean enable){
        hit.setEnabled(enable);
        stand.setEnabled(enable);
    }

    public void enableGameControls(boolean enable){
        quit.setEnabled(enable);
        deal.setEnabled(enable);
    }

    private OptionViewController makeController() {
        return new OptionViewController(player, dealer, this);
    }

    private void buildGui() {
        JPanel bettingControls = new JPanel();
        JPanel gameControls = new JPanel();
        add(bettingControls, BorderLayout.NORTH);
        add(gameControls, BorderLayout.SOUTH);
        bettingControls.setBackground(FOREST_GREEN);
        gameControls.setBackground(FOREST_GREEN);
        deal.setActionCommand(NEW_GAME);
        stand.setActionCommand(STAND);
        quit.setActionCommand(QUIT);
        doubledown.setActionCommand(DD);
        hit.setActionCommand(HIT);
        bet10.setActionCommand(BET10);
        bet50.setActionCommand(BET50);
        bet100.setActionCommand(BET100);
        bettingControls.add(bet10);
        bettingControls.add(bet50);
        bettingControls.add(bet100);
        gameControls.add(doubledown);
        gameControls.add(hit);
        gameControls.add(stand);
        gameControls.add(quit);
        enableBettingControls(false);
        enableDoubleDown(false);
        enablePlayerControls(false);
    }
}
