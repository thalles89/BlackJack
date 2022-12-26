package interfaces;

import model.Player;

public interface PalyerListener {

    public void playerChanged(Player player);
    public void playerBusted(Player player);
    public void playerStanding(Player player);
    public void playerBlackjack(Player player);
    public void playerWon(Player player);
    public void playerLost(Player player);
    public void playerStandOff(Player player);
}
