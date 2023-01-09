package model;

import interfaces.Dealer;

public class FlipPlayer extends BettingPlayer{
    private boolean hit;
    private boolean shouldHitOnce;

    public FlipPlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        return (shouldHitOnce && !hit);
    }

    @Override
    protected void bet() {
        getBank().place10Bet();
        shouldHitOnce = !shouldHitOnce;
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        return false;
    }
}
