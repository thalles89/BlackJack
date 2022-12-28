package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DeckPile {

    List<Card> stack;
    private int index;
    public void addCards(List<Card> deck){
       stack = new ArrayList<>(deck);
    }

    public void shuffle(){
        Collections.shuffle(stack);
    }

    public Card dealUp(){
        Card card = deal();
        return card.setFaceUp(true);
    }

    private Card deal() {

        if(index != stack.size()){
            Card card = stack.get(index);
            index++;
            return card;
        }

        return null;
    }

    public Card dealDown(){
        Card card = deal();
        return card.setFaceUp(false);
    }


    public void reset() {

    }
}
