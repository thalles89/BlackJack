package model;

import interfaces.Dealer;
import interfaces.HandListener;
import interfaces.PalyerListener;
import interfaces.PlayerState;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Thalles
 * @version 0.0.1
 */
public abstract class Player {
    private String name;
    private final List<PalyerListener> listeners = new ArrayList<>();
    private final Hand hand;
    private PlayerState currentState;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
        notifyChanged();
    }

    public void reset() {
        hand.reset();
    }

    public Boolean isBusted() {
        return hand.bust();
    }

    protected void notifyBusted() {

    }
    protected void notifyChanged() {

    }
    protected void notifyBlackJack() {

    }

    public void play(Dealer dealer) {
        currentState.execute(dealer);
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

    private void setCurrentState(PlayerState state) {
        currentState = state;
    }

    public void win(){
        notifyWin();
    }

    private void notifyWin() {
    }

    public void loose(){
        notifyLoose();
    }

    private void notifyLoose() {
    }

    public void standoff(){
        notifyStanoff();
    }

    private void notifyStanoff() {
    }

    public void blackjack(){
        notifyBlackJack();
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, hand.toString());
    }

    protected PlayerState getPlayingState() {
        return new Playing();
    }

    protected PlayerState getBustedState(){
        return new Busted();
    }

    protected PlayerState getBlackjackState(){return new BlackJack(); }

    protected PlayerState getWaitingState(){return new Waiting(); }

    protected PlayerState getInitialState(){return new Waiting(); }

    protected PlayerState getStandingState(){return new Waiting(); }

    private class Waiting implements PlayerState {

        @Override
        public void handPlayable() {
            setCurrentState(getPlayingState());
        }

        @Override
        public void handBlackjack() {
            setCurrentState(getPlayingState());
            notifyBlackJack();
        }

        @Override
        public void handBusted() {
            //não faz nada
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {
//não faz nada
        }
    }

    private class Busted implements PlayerState {

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

        @Override
        public void execute(Dealer dealer) {
            dealer.busted(Player.this); // TODO falta implementar o metodo busted
        }
    }

    private class BlackJack implements PlayerState {

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

        @Override
        public void execute(Dealer dealer) {
            dealer.blackjack(Player.this); // TODO falta implementar o metodo busted
        }
    }

    private class Standing implements PlayerState {

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

        @Override
        public void execute(Dealer dealer) {
            dealer.standing(Player.this); // TODO falta implementar o metodo busted
        }
    }

    private class Playing implements PlayerState {

        @Override
        public void handPlayable() {
        }

        @Override
        public void handBlackjack() {
        }

        @Override
        public void handBusted() {
            setCurrentState(getBustedState());
            notifyBusted();
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {

            if(hit()){
                dealer.hit(Player.this);
            }else{
                setCurrentState(getStandingState());
            }
            currentState.execute(dealer);
        }
    }

}
