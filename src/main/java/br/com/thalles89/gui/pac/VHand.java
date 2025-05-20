package br.com.thalles89.gui.pac;

import br.com.thalles89.interfaces.Displayable;
import br.com.thalles89.model.Card;
import br.com.thalles89.model.Console;
import br.com.thalles89.model.Hand;

import javax.swing.*;
import java.awt.*;

public class VHand extends Hand implements Displayable {

  private HandView view = new HandView();

    @Override
    public JComponent view() {
        return view;
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        view.changed();
    }

    @Override
    public void reset() {
        super.reset();
        view.changed();
    }

    private class HandView extends JPanel{
        HandView(){
            super(new FlowLayout(FlowLayout.LEFT));
            setBackground(new Color(35,140,35));
        }

        void changed(){
            removeAll();

            getCards().forEach(card -> {
                VCard vCard = (VCard) card;
                add(vCard.view());
                Console.INSTANCE.printMessage(getCards().toString());
            });
            revalidate();
        }
    }
}
