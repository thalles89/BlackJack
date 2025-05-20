package br.com.thalles89.model;


import java.util.List;

/**
 * @author Thalles
 * @version 0.0.1
 * */
public class Rank {

    public static final Rank TWO = new Rank(2, "2");
    public static final Rank THREE = new Rank(3, "3");
    public static final Rank FOUR = new Rank(4, "4");
    public static final Rank FIVE = new Rank(5, "5");
    public static final Rank SIX = new Rank(6, "6");
    public static final Rank SEVEN = new Rank(7, "7");
    public static final Rank EIGHT = new Rank(8, "8");
    public static final Rank NINE = new Rank(9, "9");
    public static final Rank TEN = new Rank(10, "10");
    public static final Rank JACK = new Rank(10, "J");
    public static final Rank QUEEN = new Rank(10, "Q");
    public static final Rank KING = new Rank(10, "K");
    public static final Rank ACE = new Rank(11, "A");

    private final int value;
    private final String display;

    public Rank(int rank, String display) {
        this.value = rank;
        this.display = display;
    }

    public static final Rank[] RANK = {TWO, THREE, FOUR, FIVE, SIX, SEVEN,
            EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};

    public int getRank() {
        return this.value;
    }

    @Override
    public String toString() {
        return display;
    }

    public static final List<Rank> ranks = List.of(RANK);
}
