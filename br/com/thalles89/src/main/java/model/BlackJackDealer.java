package model;

import interfaces.Dealer;
import interfaces.PlayerState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BlackJackDealer extends Player implements Dealer {
    private final DeckPile deck;
    private final List<Player> players = new ArrayList<>();
    private List<Player> bustedPlayers;
    private List<Player> blackjackPlayers;
    private List<Player> waitingPlayers;
    private List<Player> standingPlayers;

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

//        deck.reset();

        players.forEach(Player::reset);
    }

    public void newGame() {
        reset();
        deal();
        passTurn();
//        play(this);
    }

    @Override
    public void passTurn() {
        players.forEach(player -> player.play(this));
        this.play(this);
    }

    @Override
    public void hit(Player player) {
        player.addCard(deck.dealUp());
    }

    protected boolean hit(Dealer dealer) {
        return standingPlayers.size() > 0 && getHand().total() < 17;
    }

    @Override
    public void standing(Player player) {
        standingPlayers.add(player);
    }

    @Override
    public void blackjack(Player player) {
        blackjackPlayers.add(player);
    }

    @Override
    public void busted(Player player) {
        bustedPlayers.add(player);
    }

    @Override
    protected Boolean hit() {
        return getHand().total() < 16;
    }

    @Override
    public void stopPlay(Dealer dealer) {

    }

    @Override
    public void play(Dealer dealer) {
        exposeHand();
        super.play(dealer);
    }

    private void exposeHand() {
        getHand().turnOver();
        notifyChanged();
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
            setCurrentState(getWaitingState());
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
}
