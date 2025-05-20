package br.com.thalles89.gui.mvc;

import br.com.thalles89.model.Card;
import br.com.thalles89.model.Rank;
import br.com.thalles89.model.Suit;

public class VCard extends Card {

    private final String image;

    public VCard(Suit suit, Rank rank, String image){
        super(suit, rank);
        this. image = image;
    }

    public String getImage(){
        if(isFaceUp()){
            return image;
        }

        return "src/main/resources/playing_cards/back_pile_red.png";
    }
}
