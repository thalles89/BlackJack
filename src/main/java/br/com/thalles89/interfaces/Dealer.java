package br.com.thalles89.interfaces;

import br.com.thalles89.model.BettingPlayer;
import br.com.thalles89.model.Card;
import br.com.thalles89.model.Player;

public interface Dealer {

    void hit(Player player);
    void standing(Player player);
    void blackjack(Player player);
    void busted(Player player);
    void doneBetting(BettingPlayer bettingPlayer);
    Card getUpCard();
}
