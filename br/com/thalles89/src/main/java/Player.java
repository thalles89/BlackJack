import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;

    private List<Card> cards = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }
    
    List<Card> receiveCards(List<Card> dealedCards){
        cards.addAll(dealedCards);
        return cards;
    }

    List<Card> seeCards(){
        return cards;
    }
}
