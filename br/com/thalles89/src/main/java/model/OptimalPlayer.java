package model;

import interfaces.Dealer;

public class OptimalPlayer extends BettingPlayer {

    public OptimalPlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        int total = getHand().total();
        Card card = dealer.getUpCard();

        if (total >= 17) {
            return false;
        }
        if (total == 16) {
            return card.getRank() == Rank.SEVEN ||
                    card.getRank() == Rank.EIGHT ||
                    card.getRank() == Rank.NINE;
        }
        if (total == 13 || total == 14 || total == 15) {
            return card.getRank() != Rank.TWO &&
                    card.getRank() != Rank.THREE &&
                    card.getRank() != Rank.FOUR &&
                    card.getRank() != Rank.FIVE &&
                    card.getRank() != Rank.SIX;
        }
        if (total == 12) {
            return card.getRank() != Rank.FOUR &&
                    card.getRank() != Rank.FIVE &&
                    card.getRank() != Rank.SIX;
        }
        return true;
    }

    @Override
    protected void bet() {
        getBank().place50Bet();
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        int total = getHand().total();
        Card card = dealer.getUpCard();
        if (total == 11) {
            return true;

        }
        if (total == 10) {

            return card.getRank().getRank() != Rank.TEN.getRank() && card.getRank() != Rank.ACE;

        }
        if (total == 9) {
            return card.getRank() == Rank.TWO
                    || card.getRank() == Rank.THREE
                    || card.getRank() == Rank.FOUR
                    || card.getRank() == Rank.FIVE
                    || card.getRank() == Rank.SIX;
        }
        return false;
    }
}
