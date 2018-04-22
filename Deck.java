package gofish_assn;

import java.util.ArrayList;

import gofish_assn.Card.Suits;
//import gofish_assn.Card;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck
	final int NUM_SHUFFLES = 30;	//number of times to shuffle
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return none
	 * A constructor for the Deck class. It adds a card with ranks 1-13 for every suit into the deck. 
	 */
	public Deck() {
		for(int cardRank = 1; cardRank <= 13; cardRank++){
			Card c = new Card(cardRank, Suits.club);
			deck.add(c) ;
		}
		for(int cardRank = 1; cardRank <= 13; cardRank++){
			Card c = new Card(cardRank, Suits.diamond);
			deck.add(c) ;
		}
		for(int cardRank = 1; cardRank <= 13; cardRank++){
			Card c = new Card(cardRank, Suits.spade);
			deck.add(c) ;
		}
		for(int cardRank = 1; cardRank <= 13; cardRank++){
			Card c = new Card(cardRank, Suits.heart);
			deck.add(c) ;
		}
		//shuffle();
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return none
	 * This method is used to shuffle a deck. It utilized random numbers to swaps the positions of randomly chosen cards in the deck.
	 */
	public void shuffle() {
		Random randomNum = new Random(); 
		for(int i = 0; i < NUM_SHUFFLES; i++) {
			int swapIndex1 = randomNum.nextInt(deck.size()) + 0;
			int swapIndex2 = randomNum.nextInt(deck.size()) + 0;
			Card tempCard = deck.get(swapIndex1);
			deck.set(swapIndex1, deck.get(swapIndex2));
			deck.set(swapIndex2, tempCard);
		}
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return none
	 * This method prints out all the cards remaining in the deck 
	 */
	public void printDeck() {
		for(int i = 0; i < deck.size(); i++){ 
			System.out.println(deck.get(i).toString()); //have to change this into console
		}
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return returns the Card at the top of the deck (the Card at index 0 of the deck)
	 * The method gets the first card from the deck, then removes it from the deck, and returns it. 
	 */
	public Card dealCard() {
		
		Card c = new Card();
		c = deck.get(0);
		deck.remove(0);
		return c;
		
	}
	

}
