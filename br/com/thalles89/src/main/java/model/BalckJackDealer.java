package model;

import interfaces.Dealer;
import interfaces.PalyerListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Thalles
 * @version 0.0.1
 */
public class BalckJackDealer extends Player implements Dealer {
    private final DeckPile deck;
    private List<Player> players = new ArrayList<>();
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
    protected Boolean hit() {
        return getHand().total() > 16;
    }

    @Override
    public void stopPlay(Dealer dealer) {
//        super.stopPlay(dealer);
    }

    @Override
    public void play(Dealer dealer) {
        exposeCards();
        super.play(dealer);
    }

    private void exposeCards() {
        getHand().turnOver();
        notifyListeners();
    }

}
