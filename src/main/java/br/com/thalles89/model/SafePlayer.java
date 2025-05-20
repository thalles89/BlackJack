package br.com.thalles89.model;

import br.com.thalles89.interfaces.Dealer;

public class SafePlayer extends BettingPlayer{

    public SafePlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        return false;
    }

    @Override
    protected void bet() {
        getBank().place10Bet();
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        return false;
    }
}
