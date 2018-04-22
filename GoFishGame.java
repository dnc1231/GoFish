// GoFishGame.java 
/*
 * EE422C Project 2 submission by
 * Daniel Canterino and Suyash Adhikari
 * djc3323 and sa39579
 * 15460
 * Spring 2018
 * Slip days used: 0
 */

package gofish_assn;

import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author Daniel Canterino and Suyash Adhikari
 * @version 1.0
 * This class creates and then simulates a game of Go Fish between two players.
 * Rules: A player can create books of two when they have a matching pair within their hand. The game ends once all 26 possible books have been played.
 * 
 */
public class GoFishGame {
	int numOfBooks = 0;
	Deck gameDeck = new Deck();
	Player current;    
	Player next;
	File output = new File("C:\\Users\\Daniel\\eclipse-workspace\\Go_Fish\\GoFish_results.txt");
	PrintWriter results;
	
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return none
	 * GoFishGame creates and simulates a game of Go Fish between two players outputting a summary of the game to a text log file.
	 */
	public GoFishGame() {
		try{
			results = new PrintWriter(output);
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		current = player1;//player 1 starts/asks first
		next = player2;
		
		gameDeck.shuffle();//shuffles the deck
		
		for (int i = 0; i < 7; i++) {//deals 7 cards to each player's hand
			player1.addCardToHand(gameDeck.dealCard());
			player2.addCardToHand(gameDeck.dealCard());
		}
		while (player1.checkHandForBook() == true) {//checks for any initial books in the players hands
			numOfBooks++;
			results.println(player1.name + " books the " + player1.book.get(player1.getBookSize()-2).toString() + " and the " + player1.book.get(player1.getBookSize()-1).toString());
		}
		while (player2.checkHandForBook() == true) {
			numOfBooks++;
			results.println(player2.name + " books the " + player2.book.get(player2.getBookSize()-2).toString() + " and the " + player2.book.get(player2.getBookSize()-1).toString());
		}
		while (numOfBooks < 26 && gameDeck.deck.size() > 0) {//loops until all books have been made or there are no more cards left in the deck
			boolean playRound = false;
			playRound = playRound();//plays a round between the players and only updates the current player to the next player once the current player fails to get a card from the next player (otherwise it is still the current player's turn)
			if (playRound == false) {
				Player temp = current;
				current = next;
				next = temp;
			}
		}
		System.out.println();
		System.out.println("There are no more cards left in the deck.");
		System.out.println();
		results.println();
		results.println("There are no more cards left in the deck.");
		results.println();
		finishHands();//plays the last rounds between the players if there are no more cards left in the deck
		endGame();//outputs the final summary of the winner and each player's books
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		}
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return a boolean representing if the player was successful in getting a card from the opponent
	 *
	 * This function is private as it is not intended for an outside user to ever call this function independently.
	 *
	 * playRound chooses a random card from the current player's hand and asks the next player if a card of equal rank is in their hand.
	 * If the current player has no cards in their hand, they draw from the deck.
	 * Once asked if they have a card in their hand, the next player will search their hand.
	 * If they have one, the card is removed from the next player's hand and added into the current player's hand.
	 * The current player then books the pair and the function returns true representing the player was successful in their fish attempt and it is still their turn.
	 * If the next player does not have a card of equal rank, the current player goes fishing and draws the top card from the deck and adds it to their hand.
	 * The current player then checks if they drew a card to make a book, and if so, books the pair and increments the numOfBooks count.
	 * The function still returns false though in this case as it is no longer their turn after.
	 */
	private boolean playRound() {
		if (current.getHandSize() == 0) {//the case where the current player has no cards in their hand
			System.out.println(current.getName() + " has no cards in their hand.");
			results.println(current.getName() + " has no cards in their hand.");
			Card newCard = gameDeck.dealCard(); 
			System.out.println(current.getName() + " draws " + newCard.toString());
			results.println(current.getName() + " draws " + newCard.toString());
			current.addCardToHand(newCard);
			return false;
		}else {
			boolean foundCard = false;
			Random randomNum = new Random(); 
			int seekCard = randomNum.nextInt(current.getHandSize());//generates a random index of the card in the current player's hand to seek
			System.out.println(current.getName() + " asks - Do you have a " + current.hand.get(seekCard).toString().substring(0, current.hand.get(seekCard).toString().length() - 1) + "?");
			results.println(current.getName() + " asks - Do you have a " + current.hand.get(seekCard).toString().substring(0, current.hand.get(seekCard).toString().length() - 1) + "?");//substring removes the last character of the toString return string effectively removing the suit
			if(next.rankInHand(current.hand.get(seekCard)) == true) {//case where the current player is successful in asking
				Card matchCard = next.findCard(current.hand.get(seekCard));
				System.out.println(next.getName() + " says - Yes. I have a " + current.hand.get(seekCard).toString().substring(0, current.hand.get(seekCard).toString().length() - 1) + ".");
				results.println(next.getName() + " says - Yes. I have a " + current.hand.get(seekCard).toString().substring(0, current.hand.get(seekCard).toString().length() - 1) + ".");
				current.addCardToHand(next.removeCardFromHand(matchCard));
				foundCard = true;//changes foundCard to true so it is still the current player's turn after
				if (current.checkHandForBook() == true) {
					results.println(current.name + " books the " + current.book.get(current.getBookSize()-2).toString() + " and the " + current.book.get(current.getBookSize()-1).toString());
					numOfBooks++;
				}
				return foundCard;
			} else {//case where the current player is not successful and finding a card in the next player's hand
				System.out.println(next.getName() + " says - Go Fish");
				results.println(next.getName() + " says - Go Fish");
				Card newCard = gameDeck.dealCard();
				System.out.println(current.getName() + " draws " + newCard.toString());
				results.println(current.getName() + " draws " + newCard.toString());
				current.addCardToHand(newCard);
				if (current.checkHandForBook() == true) {//still checks to see if a book was drawn
					results.println(current.name + " books the " + current.book.get(current.getBookSize()-2).toString() + " and the " + current.book.get(current.getBookSize()-1).toString());
					numOfBooks++;
				}
				return foundCard;//regardless of whether a book was drawn, it is still now the next player's turn so still returns false
			}
		}
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return none
	 * 
	 * This function is private as it is not intended for an outside user to ever call this function independently.
	 * 
	 * finishHands finishes the game effectively after there are no cards left in the deck to draw.
	 * Until the total number of books equals 26 (or either player runs out of cards, but this cannot happen), the current player will ask the next player if they have the first card rank in their hand
	 * Since it is guaranteed to be in the next player's hand (as there are no cards left in the deck, so the remaining pairs must be split between the two players), the next player removes the card from their hand
	 * and the current player adds it to theirs.
	 * The current player then books the pair.
	 * Since they booked a pair, and they are always guaranteed to book a pair, the current player will never lose their turn so the current player will effectively gain all the next player's remaining cards
	 * at this point in the game.
	 */
	private void finishHands() {
		while (numOfBooks != 26 && current.getHandSize() > 0 && next.getHandSize() > 0) {
			System.out.println(current.getName() + " asks - Do you have a " + current.hand.get(0).toString().substring(0, current.hand.get(0).toString().length() - 1) + "?");//will only ask for first card in hand since cards are now guaranteed to be in other players hand
			results.println(current.getName() + " asks - Do you have a " + current.hand.get(0).toString().substring(0, current.hand.get(0).toString().length() - 1) + "?");
			if(next.rankInHand(current.hand.get(0)) == true) {
				Card matchCard = next.findCard(current.hand.get(0));
				System.out.println(next.getName() + " says - Yes. I have a " + current.hand.get(0).toString().substring(0, current.hand.get(0).toString().length() - 1) + ".");
				results.println(next.getName() + " says - Yes. I have a " + current.hand.get(0).toString().substring(0, current.hand.get(0).toString().length() - 1) + ".");
				current.addCardToHand(next.removeCardFromHand(matchCard));
				if (current.checkHandForBook() == true) {
					numOfBooks++;
					results.println(current.name + " books the " + current.book.get(current.getBookSize()-2).toString() + " and the " + current.book.get(current.getBookSize()-1).toString());
				}
			}
		}
	}
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return none
	 * 
	 * This function is private as it is not intended for an outside user to ever call this function independently.
	 * 
	 * Outputs statements describing the end game results.
	 * Displays who won, how many books each player had, what books each player had were.
	 */
	private void endGame() {
		System.out.println();
		results.println();
		if (current.book.size() > next.book.size()) {//case where the current player is the winner
			System.out.println(current.getName() + " wins with " + current.getBookSize()/2 + " books.\n");//book size over two since the book size is the total number of cards making up the books which are pairs
			System.out.println(current.bookToString());
			System.out.println(next.getName() + " has booked " + next.getBookSize()/2 + " pairs.\n");
			System.out.println(next.bookToString());
			
			results.println(current.getName() + " wins with " + current.getBookSize()/2 + " books.\n");//book size over two since the book size is the total number of cards making up the books which are pairs
			results.println(current.bookToString());
			results.println(next.getName() + " has booked " + next.getBookSize()/2 + " pairs.\n");
			results.println(next.bookToString());
		}else if (next.getBookSize() > current.getBookSize()) {//case where the next player is the winner
			System.out.println(next.getName() + " wins with " + next.getBookSize()/2 + " books.\n");
			System.out.println(next.bookToString());
			System.out.println(current.getName() + " has booked " + current.getBookSize()/2 + " pairs.\n");
			System.out.println(current.bookToString());
			
			results.println(next.getName() + " wins with " + next.getBookSize()/2 + " books.\n");
			results.println(next.bookToString());
			results.println(current.getName() + " has booked " + current.getBookSize()/2 + " pairs.\n");
			results.println(current.bookToString());
		}else {//if neither player one, the game must have ended in a tie
			System.out.println("The game has ended in a tie. Each player has " + current.getBookSize()/2 + " books.");
			System.out.println(next.getName() + " has booked the follwing pairs:");
			System.out.println(next.bookToString());
			System.out.println(current.getName() + " has booked the following pairs:");
			System.out.println(current.bookToString());
			
			results.println("The game has ended in a tie. Each player has " + current.getBookSize()/2 + " books.");
			results.println(next.getName() + " has booked the follwing pairs:");
			results.println(next.bookToString());
			results.println(current.getName() + " has booked the following pairs:");
			results.println(current.bookToString());
		}
		results.close();
	}
	
}
