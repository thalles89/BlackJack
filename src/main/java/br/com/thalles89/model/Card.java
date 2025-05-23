package br.com.thalles89.model;

/**
 * @author Thalles
 * @version 0.0.1
 * */
public class Card {

    private final Rank rank;
    private final Suit suit;
    private boolean faceUp;

    public Card(Suit suite, Rank rank) {
        this.suit = suite;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public void setFaceUp(Boolean faceUp) {
        this.faceUp = faceUp;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    @Override
    public String toString() {
        if(faceUp){
            return String.format("%s%s", rank, suit);
        }
        return String.format("%s%s", "#", "#");
    }
}
