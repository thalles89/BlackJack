package br.com.thalles89.gui.pac;

import br.com.thalles89.interfaces.Dealer;
import br.com.thalles89.model.Bank;
import br.com.thalles89.model.Deck;
import br.com.thalles89.model.DeckPile;

public class VPlayerFactory {
    private Dealer dealer;
    private GUIPlayer human;
    private DeckPile pile;

    public VBlackJackDealer getDealer(){
        if (dealer==null){
            VHand hand = getHand();
            DeckPile cards = getCards();
            dealer = new VBlackJackDealer("Dealer", hand, cards);
        }
        return (VBlackJackDealer) dealer;
    }


    public GUIPlayer getHuman(){
        if(human==null){
            VHand hand = getHand();
            Bank bank = new Bank(1000);
            human = new GUIPlayer("Player", hand, bank, getDealer());
        }
        return human;
    }


    public DeckPile getCards(){

        if(pile==null){
            pile = new DeckPile();
            for(int i=0;i<4;i++){
                pile.shuffle();
                Deck deck = new VDeck();
                deck.addToStack(pile);
                pile.shuffle();
            }
        }

        return pile;
    }



    public VHand getHand(){
        return new VHand();
    }
}
