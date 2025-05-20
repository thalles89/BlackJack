package br.com.thalles89.gui.mvc;

import br.com.thalles89.model.Card;
import br.com.thalles89.model.Deck;
import br.com.thalles89.model.Rank;
import br.com.thalles89.model.Suit;

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

        deck.add(new VCard(Suit.CLUBS, Rank.TWO,    "src/main/resources/playing_cards/2_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.THREE,  "src/main/resources/playing_cards/3_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.FOUR,   "src/main/resources/playing_cards/4_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.FIVE,   "src/main/resources/playing_cards/5_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.SIX,    "src/main/resources/playing_cards/6_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.SEVEN,  "src/main/resources/playing_cards/7_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.EIGHT,  "src/main/resources/playing_cards/8_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.NINE,   "src/main/resources/playing_cards/9_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.TEN,    "src/main/resources/playing_cards/10_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.JACK,   "src/main/resources/playing_cards/jack_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.QUEEN,  "src/main/resources/playing_cards/queen_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.KING,   "src/main/resources/playing_cards/king_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.ACE,    "src/main/resources/playing_cards/ace_of_clubs.png"));

        deck.add(new VCard(Suit.HEARTS, Rank.TWO,   "src/main/resources/playing_cards/2_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.THREE, "src/main/resources/playing_cards/3_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.FOUR,  "src/main/resources/playing_cards/4_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.FIVE,  "src/main/resources/playing_cards/5_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.SIX,   "src/main/resources/playing_cards/6_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.SEVEN, "src/main/resources/playing_cards/7_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.EIGHT, "src/main/resources/playing_cards/8_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.NINE,  "src/main/resources/playing_cards/9_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.TEN,   "src/main/resources/playing_cards/10_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.JACK,  "src/main/resources/playing_cards/jack_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.QUEEN, "src/main/resources/playing_cards/queen_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.KING,  "src/main/resources/playing_cards/king_of_hearts.png"));

        deck.add(new VCard(Suit.SPADES, Rank.TWO,   "src/main/resources/playing_cards/2_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.THREE, "src/main/resources/playing_cards/3_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.FOUR,  "src/main/resources/playing_cards/4_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.FIVE,  "src/main/resources/playing_cards/5_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.SIX,   "src/main/resources/playing_cards/6_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.SEVEN, "src/main/resources/playing_cards/7_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.EIGHT, "src/main/resources/playing_cards/8_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.NINE,  "src/main/resources/playing_cards/9_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.TEN,   "src/main/resources/playing_cards/10_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.JACK,  "src/main/resources/playing_cards/jack_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.QUEEN, "src/main/resources/playing_cards/queen_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.KING,  "src/main/resources/playing_cards/king_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.ACE,   "src/main/resources/playing_cards/ace_of_spades.png"));

        deck.add(new VCard(Suit.DIAMONDS, Rank.TWO,     "src/main/resources/playing_cards/2_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.THREE,   "src/main/resources/playing_cards/3_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.FOUR,    "src/main/resources/playing_cards/4_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.FIVE,    "src/main/resources/playing_cards/5_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.SIX,     "src/main/resources/playing_cards/6_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.SEVEN,   "src/main/resources/playing_cards/7_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.EIGHT,   "src/main/resources/playing_cards/8_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.NINE,    "src/main/resources/playing_cards/9_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.TEN,     "src/main/resources/playing_cards/10_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.JACK,    "src/main/resources/playing_cards/jack_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.QUEEN,   "src/main/resources/playing_cards/queen_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.KING,    "src/main/resources/playing_cards/king_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.ACE,     "src/main/resources/playing_cards/ace_of_diamonds.png"));

    }

}
