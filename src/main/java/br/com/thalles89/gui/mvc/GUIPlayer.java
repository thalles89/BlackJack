package br.com.thalles89.gui.mvc;

import br.com.thalles89.interfaces.Dealer;
import br.com.thalles89.interfaces.PlayerState;
import br.com.thalles89.model.*;

public class GUIPlayer extends BettingPlayer {

    protected Dealer dealer;

    public GUIPlayer(String name, Hand hand, Bank bank) {
        super(name, hand, bank);
    }

    @Override
    protected Boolean hit(Dealer dealer) {
        return true;
    }

    @Override
    protected void bet() {

    }

    public void place10Bet() {
        getBank().place10Bet();
        setCurrentState(getWaitingState());
        dealer.doneBetting(this);
    }

    public void place50Bet() {
        getBank().place50Bet();
        setCurrentState(getWaitingState());
        dealer.doneBetting(this);
    }

    public void place100Bet() {
        getBank().place100Bet();
        setCurrentState(getWaitingState());
        dealer.doneBetting(this);
    }

    @Override
    protected Boolean doubleDown(Dealer dealer) {
        setCurrentState(getDoublingDownState());
        getDoublingDownState().execute(dealer);
        return true;
    }

    public void takeCard() {
        dealer.hit(this);
    }

    public void stand() {
       setCurrentState(getStandingState());
       notifyStanding();
       getCurrentState().execute(dealer);
    }

    @Override
    public void play(Dealer dealer) {
        this.dealer = dealer;
        super.play(dealer);
    }

    @Override
    protected PlayerState getPlayingState() {
        return new Playing();
    }

    @Override
    protected PlayerState getBettingState() {
        return new Betting();
    }

    protected void trySplitHand() {
    }

    private class Playing implements PlayerState {

        @Override
        public void handPlayable() {

        }

        @Override
        public void handBlackjack() {
            setCurrentState(getBlackjackState());
            notifyBlackJack();
            getCurrentState().execute(dealer);
        }

        @Override
        public void handBusted() {
            setCurrentState(getBustedState());
            notifyBusted();
            getCurrentState().execute(dealer);
        }

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {

        }
    }

    private static class Betting implements PlayerState{

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

        }
    }

}
