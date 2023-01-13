package gui.pac;

import interfaces.Displayable;
import model.Card;
import model.Console;
import model.Rank;
import model.Suit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class VCard extends Card implements Displayable {

    private String image;
    private CardView view;

    public VCard(Suit suit, Rank rank, String image) {
        super(suit, rank);
        this.image = image;
        view = new CardView(getImage());
    }


    public void setFaceUp(Boolean faceUp) {
        super.setFaceUp(faceUp);
        view.changed();
    }

    public String getImage() {
        if (isFaceUp()) {
            return image;
        }
        return "br/com/thalles89/src/main/java.playing_cards/back_pile_red.png";
    }

    @Override
    public JComponent view() {
        return view;
    }

    private class CardView extends JLabel {
        public CardView(String card) {
            setImage(card);
            setBackground(Color.WHITE);
            setOpaque(true);
        }

        public void changed() {
            setImage(getImage());
        }

        private void setImage(String image) {
            try {
            File file = new File(getImage());
            BufferedImage bufferedImage = ImageIO.read(file);

            Image resultingImage = bufferedImage.getScaledInstance(
                    (bufferedImage.getWidth() / 3),
                    (bufferedImage.getHeight() / 3),
                    Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resultingImage);
            setIcon(icon);
            } catch (IOException e) {
                Console.INSTANCE.printMessage("Error on reder image");
            }
        }
    }

}
