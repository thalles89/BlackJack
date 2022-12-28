package model;


/**
 * @author Thalles
 * @version 0.0.1
 * */
public class Suit {
    public static final Suit HEARTS = new Suit((char) 3);
    public static final Suit DIAMONDS = new Suit((char) 4);
    public static final Suit CLUBS = new Suit((char) 5);
    public static final Suit SPADES = new Suit((char) 6);

    public static final Suit[] SUIT = {HEARTS, DIAMONDS, CLUBS, SPADES };

    private final char display;
    public Suit(char display){
        this.display = display;
    }

    @Override
    public String toString() {
        return String.valueOf(display);
    }
}
