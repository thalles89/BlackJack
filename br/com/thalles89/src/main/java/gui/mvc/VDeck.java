package gui.mvc;

import model.Card;
import model.Deck;
import model.Rank;
import model.Suit;

import java.util.LinkedList;
import java.util.List;

public class VDeck extends Deck {


    List<Card> deck;

    public VDeck() {
        buildCards();
    }
    @Override
    protected void buildCards() {

        deck = new LinkedList<>();
        setDeck(deck);

        deck.add(new VCard(Suit.CLUBS, Rank.TWO,    "br/com/thalles89/src/main/java/playing_cards/2_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.THREE,  "br/com/thalles89/src/main/java/playing_cards/3_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.FOUR,   "br/com/thalles89/src/main/java/playing_cards/4_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.FIVE,   "br/com/thalles89/src/main/java/playing_cards/5_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.SIX,    "br/com/thalles89/src/main/java/playing_cards/6_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.SEVEN,  "br/com/thalles89/src/main/java/playing_cards/7_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.EIGHT,  "br/com/thalles89/src/main/java/playing_cards/8_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.NINE,   "br/com/thalles89/src/main/java/playing_cards/9_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.TEN,    "br/com/thalles89/src/main/java/playing_cards/10_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.JACK,   "br/com/thalles89/src/main/java/playing_cards/jack_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.QUEEN,  "br/com/thalles89/src/main/java/playing_cards/queen_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.KING,   "br/com/thalles89/src/main/java/playing_cards/king_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.ACE,    "br/com/thalles89/src/main/java/playing_cards/ace_of_clubs.png"));

        deck.add(new VCard(Suit.HEARTS, Rank.TWO,   "br/com/thalles89/src/main/java/playing_cards/2_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.THREE, "br/com/thalles89/src/main/java/playing_cards/3_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.FOUR,  "br/com/thalles89/src/main/java/playing_cards/4_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.FIVE,  "br/com/thalles89/src/main/java/playing_cards/5_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.SIX,   "br/com/thalles89/src/main/java/playing_cards/6_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.SEVEN, "br/com/thalles89/src/main/java/playing_cards/7_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.EIGHT, "br/com/thalles89/src/main/java/playing_cards/8_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.NINE,  "br/com/thalles89/src/main/java/playing_cards/9_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.TEN,   "br/com/thalles89/src/main/java/playing_cards/10_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.JACK,  "br/com/thalles89/src/main/java/playing_cards/jack_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.QUEEN, "br/com/thalles89/src/main/java/playing_cards/queen_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.KING,  "br/com/thalles89/src/main/java/playing_cards/king_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.ACE,   "br/com/thalles89/src/main/java/playing_cards/ace_of_hearts.png"));

        deck.add(new VCard(Suit.SPADES, Rank.TWO,   "br/com/thalles89/src/main/java/playing_cards/2_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.THREE, "br/com/thalles89/src/main/java/playing_cards/3_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.FOUR,  "br/com/thalles89/src/main/java/playing_cards/4_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.FIVE,  "br/com/thalles89/src/main/java/playing_cards/5_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.SIX,   "br/com/thalles89/src/main/java/playing_cards/6_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.SEVEN, "br/com/thalles89/src/main/java/playing_cards/7_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.EIGHT, "br/com/thalles89/src/main/java/playing_cards/8_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.NINE,  "br/com/thalles89/src/main/java/playing_cards/9_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.TEN,   "br/com/thalles89/src/main/java/playing_cards/10_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.JACK,  "br/com/thalles89/src/main/java/playing_cards/jack_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.QUEEN, "br/com/thalles89/src/main/java/playing_cards/queen_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.KING,  "br/com/thalles89/src/main/java/playing_cards/king_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.ACE,   "br/com/thalles89/src/main/java/playing_cards/ace_of_spades.png"));

        deck.add(new VCard(Suit.DIAMONDS, Rank.TWO,     "br/com/thalles89/src/main/java/playing_cards/2_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.THREE,   "br/com/thalles89/src/main/java/playing_cards/3_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.FOUR,    "br/com/thalles89/src/main/java/playing_cards/4_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.FIVE,    "br/com/thalles89/src/main/java/playing_cards/5_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.SIX,     "br/com/thalles89/src/main/java/playing_cards/6_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.SEVEN,   "br/com/thalles89/src/main/java/playing_cards/7_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.EIGHT,   "br/com/thalles89/src/main/java/playing_cards/8_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.NINE,    "br/com/thalles89/src/main/java/playing_cards/9_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.TEN,     "br/com/thalles89/src/main/java/playing_cards/10_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.JACK,    "br/com/thalles89/src/main/java/playing_cards/jack_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.QUEEN,   "br/com/thalles89/src/main/java/playing_cards/queen_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.KING,    "br/com/thalles89/src/main/java/playing_cards/king_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.ACE,     "br/com/thalles89/src/main/java/playing_cards/ace_of_diamonds.png"));

    }


}
