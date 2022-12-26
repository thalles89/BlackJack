package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<>();
    private static final int BALCKJACK = 21;

    public void addCard(Card card) {
        cards.add(card);
    }

    public Boolean burst() {
        return total() > BALCKJACK;
    }

    public void reset() {
        cards.clear();
    }

    public void turnOver() {
        for (Card card : cards) {
            card.setFaceUp(true);
        }
    }

    public int total() {
        int total = 0;
        for (Card card : cards) {
            total += card.getRank().getRank();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder returnMsg = new StringBuilder(" ");
        for (Card c: cards) {
            returnMsg.append(c.toString());
        }
        return returnMsg.toString();
    }


}
