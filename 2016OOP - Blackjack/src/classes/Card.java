package classes;
public class Card {
	public int cardValue;
	public String cardName;
	public Card(String cardName, int cardValue){
		this.cardName = cardName;
		this.cardValue = cardValue;
	}
	public int getCardValue() {
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
