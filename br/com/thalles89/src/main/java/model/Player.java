package model;

import interfaces.Dealer;
import interfaces.PlayerListener;
import interfaces.PlayerState;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Thalles
 * @version 0.0.1
 */
public abstract class Player {
    private final String name;
    private List<PlayerListener> listeners;
    private final Hand hand;
    private PlayerState currentState;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
        listeners = new ArrayList<>();
        setCurrentState(getInitialState());
    }

    public void addCard(Card card) {
        hand.addCard(card);
//        notifyChanged();
    }

    public void reset() {
        hand.reset();
        setCurrentState(getInitialState());
//        notifyChanged();
    }

    public void play(Dealer dealer) {
        currentState.execute(dealer);
    }

    public void addListener(PlayerListener iPalyerReceiver) {
        listeners.add(iPalyerReceiver);
    }

    protected Hand getHand() {
        return hand;
    }

    protected abstract Boolean hit(Dealer dealer);
    protected void setCurrentState(PlayerState state) {
        currentState = state;
        hand.setHolder(state);
    }
    public void win(){
        notifyWin();
    }
    public void lose(){
        notifyLose();
    }
    public void standoff(){
        notifyStandoff();
    }
    public void blackjack(){
        notifyBlackJack();
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
    private void notifyStandoff() {
        listeners.forEach(pl->pl.playerStandOff(this));
    }
    private void notifyWin() {
        listeners.forEach(player->player.playerWon(this));
    }
    private void notifyLose() {
        listeners.forEach(player->player.playerLost(this));
    }
    @Override
    public String toString() {
        return String.format("%s: %s", name, hand.toString());
    }
    protected PlayerState getPlayingState() {
        return new Playing();
    }
    protected final PlayerState getCurrentState() {
        return currentState;
    }
    protected PlayerState getBustedState(){
        return new Busted();
    }

    protected PlayerState getBlackjackState(){return new BlackJack(); }

    protected PlayerState getWaitingState(){return new Waiting(); }

    protected PlayerState getInitialState(){return new Waiting(); }

    protected PlayerState getStandingState(){return new Standing(); }

    protected String getName() {
        return this.name;
    }

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
            dealer.blackjack(Player.this);
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

            if(hit(dealer)){
                dealer.hit(Player.this);
            }else{
                setCurrentState(getStandingState());
                notifyStanding();
            }
            currentState.execute(dealer);
        }
    }

}
