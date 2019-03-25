package classes;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	public Card card(){
		ArrayList<Card> deck = new ArrayList<>();	
		/* Hearts Cards */
		deck.add(new Card("2 of Hearts",2));
		deck.add(new Card("3 of Hearts",3));
		deck.add(new Card("4 of Hearts",4));
		deck.add(new Card("5 of Hearts",5));
		deck.add(new Card("6 of Hearts",6));
		deck.add(new Card("7 of Hearts",7));
		deck.add(new Card("8 of Hearts",8));
		deck.add(new Card("9 of Hearts",9));
		deck.add(new Card("10 of Hearts",10));
		deck.add(new Card("Jack of Hearts",10));
		deck.add(new Card("Queen of Hearts",10));
		deck.add(new Card("King of Hearts",10));
		deck.add(new Card("Ace of Hearts",11));
		/* Clubs Cards */
		deck.add(new Card("2 of Clubs",2));
		deck.add(new Card("3 of Clubs",3));
		deck.add(new Card("4 of Clubs",4));
		deck.add(new Card("5 of Clubs",5));
		deck.add(new Card("6 of Clubs",6));
		deck.add(new Card("7 of Clubs",7));
		deck.add(new Card("8 of Clubs",8));
		deck.add(new Card("9 of Clubs",9));
		deck.add(new Card("10 of Clubs",10));
		deck.add(new Card("Jack of Clubs",10));
		deck.add(new Card("Queen of Clubs",10));
		deck.add(new Card("King of Clubs",10));
		deck.add(new Card("Ace of Clubs",11));
		/*Spades Cards */
		deck.add(new Card("2 of Spades",2));
		deck.add(new Card("3 of Spades",3));
		deck.add(new Card("4 of Spades",4));
		deck.add(new Card("5 of Spades",5));
		deck.add(new Card("6 of Spades",6));
		deck.add(new Card("7 of Spades",7));
		deck.add(new Card("8 of Spades",8));
		deck.add(new Card("9 of Spades",9));
		deck.add(new Card("10 of Spades",10));
		deck.add(new Card("Jack of Spades",10));
		deck.add(new Card("Queen of Spades",10));
		deck.add(new Card("King of Spades",10));
		deck.add(new Card("Ace of Spades",11));
		/* Diamonds Cards */
		deck.add(new Card("2 of Diamonds",2));
		deck.add(new Card("3 of Diamonds",3));
		deck.add(new Card("4 of Diamonds",4));
		deck.add(new Card("5 of Diamonds",5));
		deck.add(new Card("6 of Diamonds",6));
		deck.add(new Card("7 of Diamonds",7));
		deck.add(new Card("8 of Diamonds",8));
		deck.add(new Card("9 of Diamonds",9));
		deck.add(new Card("10 of Diamonds",10));
		deck.add(new Card("Jack of Diamonds",10));
		deck.add(new Card("Queen of Diamonds",10));
		deck.add(new Card("King of Diamonds",10));
		deck.add(new Card("Ace of Diamonds",11));	
		Random rand = new Random();
		int  randomIndex = rand.nextInt(51);
		return deck.get(randomIndex);
}
}