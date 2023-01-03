package model;

import java.util.LinkedList;
import java.util.List;



/**
 * @author Thalles
 * @version 0.0.1
 * */
public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>();
        buildCards();
    }

    protected void buildCards() {

        for (int i=0; i<Suit.SUIT.length;i++){
            for (int j = 0; j < Rank.RANK.length; j++){
                deck.add(new Card(Suit.SUIT[i], Rank.RANK[j]));
            }
        }
    }

    public int size() {
        return deck.size();
    }

    public List<Card> getList() {
        return this.deck;
    }


    public void addToStack(DeckPile stack){
        stack.addCards(deck);
    }


}
