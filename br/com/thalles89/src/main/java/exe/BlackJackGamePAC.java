package exe;

import gui.pac.GUIPlayer;
import gui.pac.VBlackJackDealer;
import gui.pac.VPlayerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BlackJackGamePAC extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new BlackJackGamePAC();
        frame.getContentPane().setBackground(new Color(35,140,35));
        frame.setSize(1024,768);
        frame.setVisible(true);
    }

    private VPlayerFactory player = new VPlayerFactory();
    private JPanel players = new JPanel(new GridLayout(0, 1));

    public BlackJackGamePAC(){
        setUP();
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wa);
    }


    public void setUP(){

        VBlackJackDealer dealer = this.player.getDealer();
        GUIPlayer player  =  this.player.getHuman();
        dealer.addPlayer(player);
        players.add(dealer.view());
        players.add(player.view());
        getContentPane().add(players, BorderLayout.CENTER);
    }
}
