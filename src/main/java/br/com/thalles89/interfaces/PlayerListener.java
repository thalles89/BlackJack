package br.com.thalles89.interfaces;

import br.com.thalles89.model.Player;

public interface PlayerListener {

    void playerChanged(Player player);
    void playerBusted(Player player);
    void playerStanding(Player player);
    void playerBlackjack(Player player);
    void playerWon(Player player);
    void playerLost(Player player);
    void playerStandOff(Player player);
}
