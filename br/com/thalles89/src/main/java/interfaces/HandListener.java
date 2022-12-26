package interfaces;

import model.Player;

public interface HandListener {
    public void handPlayable();
    public void handBlackjack();
    public void handBusted();
    public void handChanged();
}
