package model;

import interfaces.Dealer;
import interfaces.PlayerState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BalckJackDealer extends Player implements Dealer {
    private final DeckPile deck;
    private List<Player> players = new ArrayList<>();
    private List<Player> bustedPlayers = new ArrayList<>();
    private List<Player> blackjackPlayers = new ArrayList<>();
    private List<Player> waitingPlayers = new ArrayList<>();
    private List<Player> standingPlayers = new ArrayList<>();
    private int indexPlayer;

    public BalckJackDealer(String name, Hand hand, DeckPile pile) {
        super(name, hand);
        this.deck = pile;
    }

    public void deal() {
        deck.shuffle();
        // distribui uma carta para cada jogado e por ultimo
        // para si mesmo (dealer)
        Player[] player = new Player[players.size()];
        players.toArray(player);

        for (Player item : player) {
            item.reset();
            item.addCard(deck.dealUp());
        }
        this.deck.dealUp();
        // distribui uma carta para cada jogado e por ultimo
        // para si mesmo (dealer) virada para baixo
        for (Player item : player) {
            item.addCard(deck.dealUp());
        }
        this.deck.dealDown();

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void newGame() {
        deal();
        passTurn();
    }

    @Override
    protected PlayerState getInitialState() {
        return new DealerWaiting();
    }

    @Override
    public void passTurn() {
//        players.forEach(player -> player.play(this));
        if (indexPlayer != players.size()) {
            Player player = players.get(indexPlayer);
            indexPlayer++;
            player.play(this);
        } else {
            this.play(this);
        }
    }

    @Override
    public void hit(Player player) {
        player.addCard(deck.dealUp());
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
        return getHand().total() > 16;
    }

    @Override
    public void stopPlay(Dealer dealer) {
//        super.stopPlay(dealer); // removido para intorremper o loop de jogo
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
        public void handChanged() {
            notifyChanged();
        }

        @Override
        public void execute(Dealer dealer) {
            deal();
            getCurrentState().execute(dealer);
        }
    }
}
