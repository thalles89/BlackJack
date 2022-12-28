package model;

import interfaces.Dealer;
import interfaces.PlayerState;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private List<Player> bettingPlayers;

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

        waitingPlayers = new ArrayList<>();
        standingPlayers = new ArrayList<>();
        bustedPlayers = new ArrayList<>();
        blackjackPlayers = new ArrayList<>();
        bettingPlayers = new LinkedList<>(players);
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

    protected Boolean hit(Dealer dealer) {
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
    public void doneBetting(BettingPlayer bettingPlayer) {
        waitingPlayers.add(bettingPlayer);
        play(this);
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
    protected PlayerState getInitialState() {
        return new DealerCollectingBets();
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
                    player.blackjack();
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

    private class DealerCollectingBets implements PlayerState{

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

            if(!bettingPlayers.isEmpty()){
                Player player = bettingPlayers.get(0);
                bettingPlayers.remove(player);
                player.play(dealer);
            }else{
                setCurrentState(getDealingState());
                getCurrentState().execute(dealer);
            }

        }
    }

}
