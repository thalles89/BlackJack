import java.util.Collections;

public class Dealer {
	private final Deck deck;
	
	public Dealer(Deck deck) {
		this.deck = deck;
	}
	

	public void shuffle(Deck deck){
		Collections.shuffle(deck.getList());
	}

	public Card dealCard() {
		
		if(deck.size()>0) {
			return deck.removeFromFront();
		}
		
		return null;
	}

}
