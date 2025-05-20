package br.com.thalles89.model;

import br.com.thalles89.interfaces.Dealer;
import br.com.thalles89.interfaces.PlayerState;


public abstract class BettingPlayer extends Player {

    private final Bank bank;

    public BettingPlayer(String name, Hand hand, Bank bank) {
        super(name, hand);
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public void win() {
        bank.win();
        super.win();
    }

    @Override
    public void lose() {
        bank.lose();
        super.lose();
    }

    @Override
    public void standoff() {
        bank.standoff();
        super.standoff();
    }

    @Override
    public void blackjack() {
        bank.blackjack();
        super.blackjack();
    }

    @Override
    public String getName() {
        return super.getName() +": " + bank.toString();
    }

    @Override
    protected abstract Boolean hit(Dealer dealer);

    @Override
    protected PlayerState getInitialState() {
        return getBettingState();
    }
    protected PlayerState getBettingState() {
        return new Betting();
    }
    @Override
    protected PlayerState getPlayingState() {
        return new BetterPlaying();
    }
    protected PlayerState getDoublingDownState() {
        return new DoublingDown();
    }
    protected abstract void bet();
    protected abstract Boolean doubleDown(Dealer dealer);

    @Override
    public String toString() {
        return String.format("%s: %s", getName(), getHand());
    }

    private class Betting implements PlayerState {

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
            bet();
            setCurrentState(getWaitingState());
            dealer.doneBetting(BettingPlayer.this);
        }
    }

    private class DoublingDown implements PlayerState{

        @Override
        public void handPlayable() {
            setCurrentState(getStandingState());
            notifyStanding();
        }

        @Override
        public void handBlackjack() {
            setCurrentState(getBlackjackState());
            notifyBlackJack();
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
            bank.doubleDown();
            dealer.hit(BettingPlayer.this);
            getCurrentState().execute(dealer);
        }
    }

    private class BetterPlaying implements PlayerState{

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

            if(getHand().canDoubleDown() && doubleDown(dealer)){
                setCurrentState(getDoublingDownState());
                getCurrentState().execute(dealer);
                return;
            }else if(hit(dealer)){
                dealer.hit(BettingPlayer.this);
            }else{
                setCurrentState(getStandingState());
                notifyStanding();
            }
            getCurrentState().execute(dealer);
        }
    }


}
