package gui.mvc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardView extends JLabel {
    private final Icon icon;
    public CardView(VCard card) {

        try {
            File file = new File(card.getImage());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image resultingImage = bufferedImage.getScaledInstance(
                    (bufferedImage.getWidth() / 5),
                    (bufferedImage.getHeight() / 5),
                    Image.SCALE_SMOOTH);
            icon = new ImageIcon(resultingImage);
            setIcon(icon);

            setOpaque(false);

            setIcon(new ImageIcon(String.valueOf(file)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Icon getIcon() {
        return icon;
    }
}
