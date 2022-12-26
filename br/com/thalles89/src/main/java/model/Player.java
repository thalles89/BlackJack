package model;

import interfaces.Dealer;
import interfaces.PalyerListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Thalles
 * @version 0.0.1
 */
public abstract class Player {
    private String name;
    private final List<Card> cards = new ArrayList<>();
    private final List<PalyerListener> listeners = new ArrayList<>();
    private final Hand hand;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void addCard(Card card){
        hand.addCard(card);
        notifyListeners();
    }

    public void reset(){
        hand.reset();
    }

    public Boolean isBusted(){
        return hand.burst();
    }
    protected void notifyListeners() {

        for (PalyerListener list: listeners) {
            list.handChanged(this);
        }
    }

    public void play(Dealer dealer) {
        while (!isBusted() && hit()){
            dealer.hit(this);
        }
        stopPlay(dealer);
    }

    public void stopPlay(Dealer dealer) {
        dealer.passTurn();
    }

    public void addListener(PalyerListener iPalyerReceiver) {
        listeners.add(iPalyerReceiver);
    }

    protected Hand getHand() {
        return hand;
    }

    protected abstract Boolean hit();

    @Override
    public String toString() {
        return String.format("%s: %s",name,hand.toString());
    }
}
