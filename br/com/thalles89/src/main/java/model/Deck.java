package model;

import java.util.LinkedList;
import java.util.List;



/**
 * @author Thalles
 * @version 0.0.1
 * */
public class Deck {

    private List<Card> deck;

    public Deck() {
        buildCards();
    }

    protected void buildCards() {

        this.deck = new LinkedList<>();

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

    protected void setDeck(List<Card> deck){
        this.deck = deck;
    }

}
