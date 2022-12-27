package interfaces;

import model.Player;

public interface Dealer {

    void passTurn();
    void hit(Player player);
    void standing(Player player);
    void blackjack(Player player);
    void busted(Player player);
}
