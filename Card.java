package gofish_assn;

public class Card {
	
	public enum Suits {club, diamond, heart, spade};
	
	static int TOP_RANK = 13; //King
	static int LOW_RANK = 1; //Ace
	
	final int KING = 13;
	final int QUEEN = 12;
	final int JACK = 11;
	final int ACE = 1;
	
	int rank;  //1 is an Ace
	Suits suit;
	
	/**
	 * @author Daniel and Suyash
	 * @param none
	 * @return none
	 * Constructor for a card class, which defaults to ane Ace of Spades
	 */
	public Card() {
		rank = 1;
		suit = Suits.spade;
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param an int r for the rank that the card should have, and a char s that specifies the suit of the card
	 * @return none
	 * Constructor for a card class, which creates a card of rank r and suit s
	 */
	public Card(int r, char s) {
		//should we throw exceptions
		rank  = r;
		suit = toSuit(s);
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param an int r for the rank that the card should have, and a Suit s that specifies the suit of the card
	 * @return none
	 * Constructor for a card class, which creates a card of rank r and suit s
	 */
	public Card(int r, Suits s) {
		rank = r; 
		suit = s; 
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  a char c that specifies the suit of the card
	 * @return a Suits, which is Suit representation of char c
	 * Converts a character into the corresponding Suits
	 */
	private Suits toSuit(char c) {
		if(c == 'c' || c == 'C'){
			return Suits.club;
		} else if(c == 'd' || c == 'D'){
			return Suits.diamond;
		} else if(c == 'h' || c == 'H'){
			return Suits.heart;
		} else if(c == 's' || c == 'S'){
			return Suits.spade;
		} else {
			return Suits.spade; //what should the default be?
		}
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  a Suits s that specifies the suit of the card
	 * @return a String, returns the string representation of the Suit of a card
	 * Returns the string form of the suit s
	 */
	private String suitToString(Suits s)
	{
		if(s == Suits.spade){
			return "s";
		} else if(s == Suits.club){
			return "c";
		} else if(s == Suits.diamond){
			return "d";
		} else if(s == Suits.heart){
			return "h";
		} else {
			return "enter correct string"; //Defaults
		}
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  an int r that represents a rank
	 * @return a String form of the int rank
	 * Returns the String form of the rank of a card
	 */
	private String rankToString(int r)
	{
		switch(r){
			case KING:
				return "K";
			
			case QUEEN:
				return "Q";
			
			case JACK:
				return "J";
			
			case ACE:
				return "A";
			
			default: return Integer.toString(rank); //am i allowed to do this??
		}
	}
		
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return returns the rank of the Card
	 * A getter for the rank of the card
	 */
	public int getRank() {
		return rank;
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return returns the suit of the Card
	 * A getter for the suit of the card
	 */
	public Suits getSuit() {
		return suit;
	}
	
	
	/**
	 * @author Daniel and Suyash
	 * @param  none
	 * @return returns a string that contains the rank and suit of the card
	 * This method is used to return a string form of a Card that includes the rank and suit of the Card. 
	 */
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + suitToString(getSuit());
		
		return s;
	}
}
