package br.com.thalles89.gui.mvc;

import br.com.thalles89.model.BlackJackDealer;

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
    public static final String DOUBLEDOWN = "DOUBLE";
    public static final String SPLITHAND = "SPLITHAND";

    public static final JButton bet10 = new JButton("$10");
    public static final JButton bet50 = new JButton("$50");
    public static final JButton bet100 = new JButton("$100");
    public static final JButton newGame = new JButton("New Game");
    public static final JButton quit = new JButton("Quit");
    public static final JButton hit = new JButton("Hit");
    public static final JButton stand = new JButton("Stand");
    public static final JButton doubleDown = new JButton("Double Down");
    public static final JButton splitHand = new JButton("Split Hand");

    static final Color FOREST_GREEN = new Color(35, 142, 35);

    public OptionView(GUIPlayer player, BlackJackDealer dealer){
        super(new BorderLayout());
        attachController(makeController(player, dealer));
        buildGui();
    }

    public void attachController(OptionViewController controller){
        newGame.addActionListener(controller);
        quit.addActionListener(controller);
        hit.addActionListener(controller);
        stand.addActionListener(controller);
        doubleDown.addActionListener(controller);
        bet10.addActionListener(controller);
        bet50.addActionListener(controller);
        bet100.addActionListener(controller);
    }

    public void enableDoubleDown(boolean enable){
        doubleDown.setEnabled(enable);
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
        newGame.setEnabled(enable);
    }

    private OptionViewController makeController(GUIPlayer player, BlackJackDealer dealer) {
        return new OptionViewController(player, dealer, this);
    }

    private void buildGui() {
        JPanel bettingControls = new JPanel();
        JPanel gameControls = new JPanel();
        add(bettingControls, BorderLayout.NORTH);
        add(gameControls, BorderLayout.SOUTH);
        bettingControls.setBackground(FOREST_GREEN);
        gameControls.setBackground(FOREST_GREEN);
        newGame.setActionCommand(NEW_GAME);
        stand.setActionCommand(STAND);
        quit.setActionCommand(QUIT);
        doubleDown.setActionCommand(DOUBLEDOWN);
        hit.setActionCommand(HIT);
        bet10.setActionCommand(BET10);
        bet50.setActionCommand(BET50);
        bet100.setActionCommand(BET100);
        splitHand.setActionCommand(SPLITHAND);
        bettingControls.add(bet10);
        bettingControls.add(bet50);
        bettingControls.add(bet100);
        gameControls.add(doubleDown);
        gameControls.add(hit);
        gameControls.add(stand);
        gameControls.add(quit);
        gameControls.add(newGame);
        gameControls.add(splitHand);
        enableBettingControls(false);
        enableDoubleDown(false);
        enablePlayerControls(false);
        enableGameControls(true);
        enableSplitControls(false);
    }

    public void enableSplitControls(boolean enable) {
        splitHand.setEnabled(enable);
    }
}
