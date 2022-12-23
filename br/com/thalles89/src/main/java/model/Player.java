package model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Thalles
 * @version 0.0.1
 * */
public class Player {
    private String name;

    private List<Card> cards = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
    public List<Card> receiveCards(List<Card> dealedCards){
        cards.addAll(dealedCards);
        return cards;
    }

    public List<Card> seeCards(){
        return cards;
    }
}
