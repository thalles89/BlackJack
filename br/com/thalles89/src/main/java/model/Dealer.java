package model;

import java.util.Collections;



/**
 * @author Thalles
 * @version 0.0.1
 * */
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
