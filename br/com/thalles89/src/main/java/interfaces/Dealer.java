package interfaces;

import model.Player;

public interface Dealer {

    public void passTurn();
    public void hit(Player player);
    public void standing(Player player);
    public void blackjack(Player player);
    public void busted(Player player);
}
