import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck = new LinkedList<>();
        buildCards();
    }

    private List<Card> buildCards() {

        for (int i=0; i<Suit.SUIT.length;i++){
            for (int j = 0; j<Rank.RANK.length; j++){
                deck.add(new Card(Suit.SUIT[i], Rank.RANK[j]));
            }
        }
        return deck;
    }


    @Deprecated
    public Card get(int index) {
        if (index < deck.size()) {
            return deck.get(index);
        }
        return null;
    }

    @Deprecated
    public void replace(int index, Card card) {
        deck.set(index, card);
    }

    public int size() {
        return deck.size();
    }

    public Card removeFromFront() {
        if (deck.size() > 0) {
            return ((LinkedList<Card>) deck).removeFirst();
        }
        return null;
    }

    public void returnToBack(Card card) {
        deck.add(card);
    }

    public List<Card> getList() {
        return this.deck;
    }

}
