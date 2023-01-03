package gui.mvc;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CardView extends JLabel {

    private ImageIcon icon;

    public CardView(VCard card){
        getImage(card.getImage());
        setIcon(icon);
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    private void getImage(String name){
        icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(name)));
    }

}
