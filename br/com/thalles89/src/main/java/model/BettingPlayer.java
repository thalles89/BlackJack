package model;

import interfaces.Dealer;
import interfaces.PlayerState;

public abstract class BettingPlayer extends Player {

    private Bank bank;

    public BettingPlayer(String name, Hand hand, Bank bank) {
        super(name, hand);
        this.bank=bank;
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
    protected Boolean hit(Dealer dealer) {
        return null;
    }

    @Override
    protected PlayerState getInitialState() {
        return getBettingState();
    }

    protected PlayerState getBettingState(){
        return new Betting();
    }

    protected abstract void bet();

    @Override
    public String toString() {
        return String.format("%s: %s", getName(), getHand());
    }

    private class Betting implements PlayerState{

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
}
