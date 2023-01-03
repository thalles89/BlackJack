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

    protected void buildCards() {
        deck = new LinkedList<>();

        deck.add(new VCard(Suit.CLUBS, Rank.TWO, "playing_cards/2_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.THREE, "playing_cards/3_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.FOUR, "playing_cards/4_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.FIVE, "playing_cards/5_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.SIX, "playing_cards/6_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.SEVEN, "playing_cards/7_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.EIGHT, "playing_cards/8_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.NINE, "playing_cards/9_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.TEN, "playing_cards/10_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.JACK, "playing_cards/jack_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.QUEEN, "playing_cards/queen_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.KING, "playing_cards/king_of_clubs.png"));
        deck.add(new VCard(Suit.CLUBS, Rank.ACE, "playing_cards/ace_of_clubs.png"));

        deck.add(new VCard(Suit.HEARTS, Rank.TWO, "playing_cards/2_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.THREE, "playing_cards/3_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.FOUR, "playing_cards/4_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.FIVE, "playing_cards/5_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.SIX, "playing_cards/6_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.SEVEN, "playing_cards/7_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.EIGHT, "playing_cards/8_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.NINE, "playing_cards/9_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.TEN, "playing_cards/10_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.JACK, "playing_cards/jack_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.QUEEN, "playing_cards/queen_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.KING, "playing_cards/king_of_hearts.png"));
        deck.add(new VCard(Suit.HEARTS, Rank.ACE, "playing_cards/ace_of_hearts.png"));

        deck.add(new VCard(Suit.SPADES, Rank.TWO, "playing_cards/2_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.THREE, "playing_cards/3_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.FOUR, "playing_cards/4_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.FIVE, "playing_cards/5_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.SIX, "playing_cards/6_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.SEVEN, "playing_cards/7_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.EIGHT, "playing_cards/8_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.NINE, "playing_cards/9_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.TEN, "playing_cards/10_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.JACK, "playing_cards/jack_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.QUEEN, "playing_cards/queen_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.KING, "playing_cards/king_of_spades.png"));
        deck.add(new VCard(Suit.SPADES, Rank.ACE, "playing_cards/ace_of_spades.png"));

        deck.add(new VCard(Suit.DIAMONDS, Rank.TWO, "playing_cards/2_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.THREE, "playing_cards/3_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.FOUR, "playing_cards/4_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.FIVE, "playing_cards/5_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.SIX, "playing_cards/6_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.SEVEN, "playing_cards/7_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.EIGHT, "playing_cards/8_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.NINE, "playing_cards/9_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.TEN, "playing_cards/10_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.JACK, "playing_cards/jack_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.QUEEN, "playing_cards/queen_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.KING, "playing_cards/king_of_diamonds.png"));
        deck.add(new VCard(Suit.DIAMONDS, Rank.ACE, "playing_cards/ace_of_diamonds.png"));

    }


}
