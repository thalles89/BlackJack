package gui.mvc;

import model.Card;
import model.Rank;
import model.Suit;

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

        return "br/com/thalles89/src/main/java/playing_cards/back_pile_red.png";
    }
}
