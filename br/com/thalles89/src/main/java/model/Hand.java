package model;

import interfaces.HandListener;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();
    private static final int BLACKJACK = 21;
    private HandListener holder;
    private int aces;

    public Hand() {
        setHolder(new HandListener() {
            @Override
            public void handPlayable() {
            }

            @Override
            public void handBlackjack() {
            }

            @Override
            public void handBusted() {
            }

            @Override
            public void handChanged() {
            }
        });
    }

    public void addCard(Card card) {
        cards.add(card);
        holder.handChanged();

        if (card.getRank() == Rank.ACE) {
            aces++;
        }
        if (bust()) {
            holder.handBusted();
            return;
        }
        if (blackjack()) {
            holder.handBlackjack();
            return;
        }
        if (cards.size() >= 2) {
            holder.handPlayable();
        }
    }

    public Boolean canDoubleDown() {
        if (!blackjack()) {
            return this.cards.size() == 2;
        }else {
            return false;
        }


    }

    public void setHolder(HandListener listener) {
        this.holder = listener;
    }

    public boolean blackjack() {
        return (cards.size() == 2) && (total() == BLACKJACK);
    }

    public Boolean bust() {
        return total() > BLACKJACK;
    }

    public void reset() {
        this.cards.clear();
        this.aces = 0;
    }

    public void turnOver() {
        for (Card card : cards) {
            card.setFaceUp(true);
        }
    }

    public int total() {
        int total = 0;
        // this loop sums the cards in hand
        for (Card card : cards) {
            total += card.getRank().getRank();
        }
        // this loop subtract the ace value in 10 if hand is grater than 21
        int tempAces = aces;
        while (total > BLACKJACK && tempAces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder returnMsg = new StringBuilder(" ");
        for (Card c : cards) {
            returnMsg.append(c.toString());
        }
        return returnMsg.toString();
    }

    public Boolean isEquals(Hand hand) {
        return this.total() == hand.total();
    }

    public Boolean isGreaterThan(Hand hand) {
        return this.total() > hand.total();
    }
}
