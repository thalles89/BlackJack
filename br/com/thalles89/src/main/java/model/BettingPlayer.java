package model;

import interfaces.Dealer;
import interfaces.PlayerState;


public abstract class BettingPlayer extends Player {

    private Bank bank;


    public BettingPlayer(String name, Hand hand, Bank bank) {
        super(name, hand);
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    @Override
    public void win() {
        super.win();
        bank.win();
    }

    @Override
    public void lose() {
        super.lose();
        bank.lose();
    }

    @Override
    public void standoff() {
        super.standoff();
        bank.standoff();
    }

    @Override
    public void blackjack() {
        super.blackjack();
        bank.blackjack();
    }

    @Override
    public String getName() {
        return super.getName() +"\n" + bank.toString();
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
            notifyStandoff();
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
            }

            if(hit(dealer)){
                dealer.hit(BettingPlayer.this);
            }else{
                setCurrentState(getStandingState());
                notifyStanding();
            }
            getCurrentState().execute(dealer);
        }
    }


}
