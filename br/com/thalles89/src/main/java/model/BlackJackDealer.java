package model;

import interfaces.Dealer;
import interfaces.PlayerState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BlackJackDealer extends Player implements Dealer {
    private final DeckPile deck;
    private final List<Player> players = new ArrayList<>();
    private List<Player> bustedPlayers;
    private List<Player> blackjackPlayers;
    protected List<Player> waitingPlayers;
    private List<Player> standingPlayers;
    protected List<Player> bettingPlayers;

    public BlackJackDealer(String name, Hand hand, DeckPile pile) {
        super(name, hand);
        this.deck = pile;
    }

    public void deal() {
        deck.shuffle();
        players.forEach(player -> player.addCard(deck.dealUp()));
        this.addCard(deck.dealUp());
        players.forEach(player -> player.addCard(deck.dealUp()));
        this.addCard(deck.dealDown());
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void reset() {

        super.reset();

        waitingPlayers = new ArrayList<>();
        standingPlayers = new ArrayList<>();
        bustedPlayers = new ArrayList<>();
        blackjackPlayers = new ArrayList<>();
        bettingPlayers = new ArrayList<>(players);
        deck.reset();
        players.forEach(Player::reset);

    }

    public void newGame() {
        reset();
        play(this);
    }

    @Override
    public void hit(Player player) {
        player.addCard(deck.dealUp());
    }

    /**
     * @param dealer receives the table dealer to execute the play actions
     * @return a boolean success operation
     * Dealer buys if player's hand is grater than dealer's
     * or if dealer's hand is lower than 17 (soft 17 strategy)
     */
    protected Boolean hit(Dealer dealer) {
        AtomicBoolean dealerMayHit = new AtomicBoolean(false);
        standingPlayers.forEach(player -> {
            if (player.getHand().isGreaterThan(getHand())) {
                dealerMayHit.set(true);
            } else {
                dealerMayHit.set(standingPlayers.size() > 0 && getHand().total() < 17);
            }
        });
        return dealerMayHit.get();
    }

    @Override
    public void standing(Player player) {
        standingPlayers.add(player);
        play(this);
    }

    @Override
    public void blackjack(Player player) {
        blackjackPlayers.add(player);
        play(this);
    }

    @Override
    public void busted(Player player) {
        bustedPlayers.add(player);
        play(this);
    }

    @Override
    public void doneBetting(BettingPlayer bettingPlayer) {
        waitingPlayers.add(bettingPlayer);
        play(this);
    }

    protected void exposeHand() {
        getHand().turnOver();
        notifyChanged();
    }

    @Override
    protected PlayerState getInitialState() {
        return getCollectingBets();
    }

    @Override
    protected PlayerState getBustedState() {
        return new DealerBusted();
    }

    @Override
    protected PlayerState getBlackjackState() {
        return new DealerBlackJack();
    }

    @Override
    protected PlayerState getWaitingState() {
        return new DealerWaiting();
    }

    @Override
    protected PlayerState getStandingState() {
        return new DealerStanding();
    }

    public PlayerState getDealingState() {
        return new DealerDealing();
    }

    public PlayerState getCollectingBets(){
        return  new DealerCollectingBets();
    }


    public Card getUpCard(){
        for (Card c : getHand().getCards()) {
            return c;
        }
        return null;
    }
    private class DealerBusted implements PlayerState {

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
            standingPlayers.forEach(Player::win);
            blackjackPlayers.forEach(Player::win);
            bustedPlayers.forEach(Player::lose);
        }
    }

    private class DealerBlackJack implements PlayerState {

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
            exposeHand();
            players.forEach(player -> {
                if (player.getHand().blackjack()) {
                    player.standoff();
                } else {
                    player.lose();
                }
            });
        }
    }

    private class DealerStanding implements PlayerState {

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
            standingPlayers.forEach(player -> {
                if (player.getHand().isEquals(getHand())) {
                    player.standoff();
                } else if (player.getHand().isGreaterThan(getHand())) {
                    player.win();
                } else {
                    player.lose();
                }
            });

            blackjackPlayers.forEach(Player::win);
            bustedPlayers.forEach(Player::lose);
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
                player.play(dealer);
            } else {
                setCurrentState(getPlayingState());
                exposeHand();
                getCurrentState().execute(dealer);
            }

        }
    }

    private class DealerDealing implements PlayerState {

        @Override
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void handPlayable() {
            setCurrentState(getWaitingState()); // goes to waiting state
        }

        @Override
        public void handBlackjack() {
            setCurrentState(getBlackjackState());
            notifyBlackJack();
        }

        @Override
        public void handBusted() {

        }

        @Override
        public void execute(Dealer dealer) {
            deal();
            getCurrentState().execute(dealer);

        }
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
                    player.play(dealer);
                });

            } else {
                setCurrentState(getDealingState());
                getCurrentState().execute(dealer);
            }

        }
    }

}
