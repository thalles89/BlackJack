package core.threaded;

import interfaces.Dealer;
import interfaces.PlayerState;
import model.BlackJackDealer;
import model.DeckPile;
import model.Hand;
import model.Player;

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
                bettingPlayers.forEach(player -> {
                    bettingPlayers.remove(player);
                    Runnable runnable = () -> player.play(dealer);
                    runnable.run();
                    Thread thread = new Thread(runnable);
                    thread.start();
                });
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
                runnable.run();
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