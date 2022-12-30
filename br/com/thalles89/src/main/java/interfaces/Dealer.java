package interfaces;

import model.BettingPlayer;
import model.Player;

public interface Dealer {

    void hit(Player player);
    void standing(Player player);
    void blackjack(Player player);
    void busted(Player player);
    void doneBetting(BettingPlayer bettingPlayer);
}
