package threaded;

import interfaces.Dealer;
import interfaces.PlayerState;
import model.BlackJackDealer;
import model.DeckPile;
import model.Hand;
import model.Player;

import java.util.Collections;
import java.util.Optional;

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

                for (Player player: bettingPlayers) {
                    bettingPlayers.remove(player);
                    Runnable runnable = () -> player.play(dealer);
                    runnable.run();
                    Thread thread = new Thread(runnable);
                    thread.start();
                }

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

            while(!waitingPlayers.isEmpty()){
                Player player = waitingPlayers.stream().findFirst().get();
                waitingPlayers.remove(player);

                Runnable runnable = () -> player.play(dealer);
                runnable.run();
                Thread thread = new Thread(runnable);
                thread.start();
            }
            setCurrentState(getPlayingState());
            exposeHand();
            getCurrentState().execute(dealer);

//            if (!waitingPlayers.isEmpty()) {
//
//                for (Player player: waitingPlayers) {
//                    player = waitingPlayers.stream().findFirst().get();
//                    waitingPlayers.remove(player);
//
//                    Player finalPlayer = player;
//                    Runnable runnable = () -> finalPlayer.play(dealer);
//                    runnable.run();
//                    Thread thread = new Thread(runnable);
//                    thread.start();
//                }
//
//            } else {
//                setCurrentState(getPlayingState());
//                exposeHand();
//                getCurrentState().execute(dealer);
//            }

        }
    }

}
