package gofish_assn;

import java.util.ArrayList;
import gofish_assn.GoFishGame;

/**
 * 
 * @author Daniel Canterino and Suyash Adhikari
 * @version 1.0
 * This class creates a player to be used in the game Go Fish
 * 
 */

public class Player {
	
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> book = new ArrayList<Card>();
	String name;
	
	/**
	 * @author Daniel and Suyash
	 * @param name is the desired name of the player passed as a string
	 * @return Player object with name as the name field
	 * Constructer function for the class Player
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param c is an object Card desired to be added to the hand
	 * @return none
	 * adds Card c to the hand of the player
	 */
	public void addCardToHand(Card c) {
		hand.add(c);
	}
	
	
	
	/**
	 * @author Daniel and Suyash
	 * @param c is an object Card desired to be removed from the hand
	 * @return Card retCard the card that was removed from the hand of the player
	 * Removes Card c from the players hand and returns it
	 */
	public Card removeCardFromHand(Card c) {
		Card retCard = new Card();
		retCard = c;
		hand.remove(c);
		return retCard;
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return String name that is the player's name
	 * Getter function for the name field of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return String s that is the cards in the player's hand
	 * returns a string of all the cards in the player's hand
	 */
	public String handToString() {
		String s = new String();
		for(int i = 0; i < hand.size(); i++) {
			s = s + hand.get(i).toString() + "\n";
		}
		return s;
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return String of all the books the player has
	 * Returns a string that details all the books the player has made
	 */
	public String bookToString() {
		String s = new String();
		s = "";
		for (int i = 0; i < book.size(); i += 2) {//increments loop by twos since books are made only in pairs
			s = s + book.get(i).toString() + " and " + book.get(i + 1).toString() + System.getProperty("line.separator");
		}
		return s;
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return an integer of the total number of cards in the player's hand
	 * Getter function for the size field of the player
	 */
	public int getHandSize() {
		return hand.size();
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return integer of the total number of cards making up the book array list of the player
	 * Getter function for the size of the book field of the player
	 */
	public int getBookSize() {
		return book.size();
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return boolean representing if a book was found in the player's hand
	 * Checks the player's hand for a book, or if two cards within the player's had have the same rank.
	 * If they do, it removes the cards from the player's hand, and adds them to the book array list of the player.
	 * It outputs a statement stating which two cards the player booked.
	 * It then returns true.
	 * Otherwise, nothing happens to the players hand and the function returns false.
	 */
    public boolean checkHandForBook() {
    	Card c1;
    	Card c2;
    	for (int i = 0; i < hand.size(); i++) {
    		for (int j = i + 1; j < hand.size(); j++) {
    			if (hand.get(i).rank == hand.get(j).rank) {
    				c1 = hand.get(i);
    				c2 = hand.get(j);
    				hand.remove(j);
    				hand.remove(i);
    				book.add(c1);
    				book.add(c2);
    				System.out.println(name + " books the " + c1.toString() + " and the " + c2.toString());//can we change this to public?
    				return true;
    			}
    		}
    	}
    	return false;
    }

	/**
	 * @author Daniel and Suyash
	 * @param Card c which is the card to be checked if any of the cards within the player's hand have equal rank to.
	 * @return a boolean representing if a card of equal rank to the passed Card c is found
	 * checks every card in the player's hand to see if any have equal rank to the card passed into the function
	 */
    public boolean rankInHand(Card c) {
    	for (int i = 0; i < hand.size(); i++) {
    		if (hand.get(i).getRank() == c.getRank()) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /*
    //uses some strategy to choose one card from the player's
    //hand so they can say "Do you have a 4?"
    public Card chooseCardFromHand() {
    	Card c = new Card();
    	
    	return c;
    }
    */
    
    /*
    //Does the player have the card c in her hand?
    public boolean cardInHand(Card c) {
    	return hand.contains(c);
    }
    */
    

    //OPTIONAL
    // comment out if you decide to not use it    
    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c
    
    /*
    public boolean sameRankInHand(Card c) {
    	for (int i = 0; i < hand.size(); i++) {
    		if (hand.get(i).getRank() == c.getRank()) {
    			return true;
    		}
    	}
    	return false; //stubbed
    }
    */
    
	/**
	 * @author Daniel and Suyash
	 * @param Card c which is the card 
	 * @return a card in the player's hand of equal to the passed card
	 * Precondition: a card of the same rank to the passed card is already known to be within the hand
	 * finds a card of equal rank to the passed card in the player's hand and returns it.
	 * otherwise, it returns the same card passed
	 */
    public Card findCard(Card c) {
    	for (int i = 0; i < hand.size(); i++) {
    		if (hand.get(i).getRank() == c.rank) {
    			return hand.get(i);
    		}
    	}
    	return c;//need to add for else case
    }

}
