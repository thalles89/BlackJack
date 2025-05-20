package br.com.thalles89.model;

import br.com.thalles89.interfaces.Dealer;

public class SmartPlayer extends BettingPlayer{

    public SmartPlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        return !(getHand().total() > 11);
    }

    @Override
    protected void bet() {
        if (!(getHand().total() > 11)) {
            getBank().place10Bet();
        }else {
            getBank().place50Bet();

        }
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        return false;
    }
}
