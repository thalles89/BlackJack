package br.com.thalles89.gui.mvc;

import br.com.thalles89.interfaces.PlayerState;
import br.com.thalles89.model.Bank;
import br.com.thalles89.model.Hand;

import java.util.ArrayList;
import java.util.List;

public class MultiHandPlyer extends GUIPlayer {

    private final List<Hand> hands = new ArrayList<>();
    private int currentHandIndex = 0;
    private final Bank bank;



    public MultiHandPlyer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
        this.bank = bank;
        hands.add(hand);
    }

    @Override
    protected void trySplitHand() {

    }

    @Override
    public void takeCard() {
        dealer.hit(this);
        System.out.println(getCurrentHand());
    }

    public Hand getCurrentHand() {
        return hands.get(currentHandIndex);
    }

    public boolean hasNextHand() {
        return currentHandIndex < hands.size() - 1;
    }


    @Override
    public void reset() {
        for (Hand hand : hands) {
            hand.reset();
        }
        super.reset();
    }

    @Override
    protected PlayerState getInitialState() {
        return getWaitingState();
    }


    public Bank getBank() {
        return bank;
    }


}
