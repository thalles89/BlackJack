

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

    public Suit getSuit() {
        return suit;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public Card setFaceUp() {
        this.faceUp = true;
        return this;
    }

    public void setFaceDown() {
        this.faceUp = false;
    }

    public String display() {
        return rank.toString() + suit.toString();
    }

}
