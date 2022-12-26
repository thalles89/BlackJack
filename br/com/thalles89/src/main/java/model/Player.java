package model;

import interfaces.Dealer;
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
        currentState = new Waiting();
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

    protected void setCurrentState(PlayerState state) {
        currentState = state;
    }

    public void win(){
        notifyWin();
    }

    protected void notifyChanged() {
        listeners.forEach(pl->pl.playerChanged(this));
    }
    protected void notifyBusted() {
        listeners.forEach(pl->pl.playerBusted(this));
    }
    protected void notifyBlackJack() {
        listeners.forEach(pl->pl.playerBlackjack(this));
    }
    private void notifyStanding() {
        listeners.forEach(pl->pl.playerStanding(this));
    }
    private void notifyStanoff() {
        listeners.forEach(pl->pl.playerStandOff(this));
    }
    private void notifyWin() {
        listeners.forEach(player->player.playerWon(this));
    }
    private void notifyLoose() {
        listeners.forEach(player->player.playerLost(this));
    }

    public void lose(){
        notifyLoose();
    }

    public void standoff(){
        notifyStanoff();
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

    public PlayerState getCurrentState() {
        return currentState;
    }

    protected PlayerState getBustedState(){
        return new Busted();
    }

    protected PlayerState getBlackjackState(){return new BlackJack(); }

    protected PlayerState getWaitingState(){return new Waiting(); }

    protected PlayerState getInitialState(){return getWaitingState(); }

    protected PlayerState getStandingState(){return new Standing(); }

    private class Waiting implements PlayerState {

        @Override
        public void handPlayable() {
            setCurrentState(getPlayingState());
        }

        @Override
        public void handBlackjack() {
            setCurrentState(getBlackjackState());
            notifyBlackJack();
        }

        @Override
        public void handBusted() {}

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {}
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
            dealer.busted(Player.this);
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
            dealer.standing(Player.this);
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
                notifyStanding();
            }
            currentState.execute(dealer);
        }
    }

}
