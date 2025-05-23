package br.com.thalles89.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckPile {

    List<Card> stack = new ArrayList<>();
    private int index;
    public void addCards(List<Card> cards){
       stack.addAll(cards);
    }

    public void shuffle(){
        Collections.shuffle(stack);
    }

    public Card dealUp(){
        Card card = deal();
        assert card != null;
        card.setFaceUp(true);
        return card;
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
        assert card != null;
        card.setFaceUp(false);
        return card;
    }


    public List<Card> getStack() {
        return stack;
    }

    public void reset() {
        stack.forEach(card -> {
            card.setFaceUp(false);
        });
    }
}
