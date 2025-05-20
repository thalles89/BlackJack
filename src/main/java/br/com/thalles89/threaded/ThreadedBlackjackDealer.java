package br.com.thalles89.threaded;

import br.com.thalles89.interfaces.Dealer;
import br.com.thalles89.interfaces.PlayerState;
import br.com.thalles89.model.*;

public class ThreadedBlackjackDealer extends BlackJackDealer {

    public ThreadedBlackjackDealer(String name, Hand hand, DeckPile pile) {
        super(name, hand, pile);
    }

    @Override
    public PlayerState getCollectingBets() {
        return new DealerCollectingBets();
    }

    @Override
    protected PlayerState getWaitingState() {
        return new DealerWaiting();
    }

    private class DealerCollectingBets implements PlayerState {

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
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {

            if (!bettingPlayers.isEmpty()) {
                Player player = bettingPlayers.get(0);
                bettingPlayers.remove(player);
                Runnable runnable = () -> player.play(dealer);
                Thread thread = new Thread(runnable);
                thread.start();
            } else {
                setCurrentState(getDealingState());
                getCurrentState().execute(dealer);
            }

        }
    }

    private class DealerWaiting implements PlayerState {

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
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {

            if (!waitingPlayers.isEmpty()) {
                Player player = waitingPlayers.get(0);
                waitingPlayers.remove(player);
                Runnable runnable = () -> player.play(dealer);
//                runnable.run();
                Thread thread = new Thread(runnable);
                thread.start();
            } else {
                setCurrentState(getPlayingState());
                exposeHand();
                getCurrentState().execute(dealer);
            }

        }
    }

}
