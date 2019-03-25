package classes;
public class Dealer {
	public int dealerPoints = 0;
	public Dealer(){
	}
	public int getDealerPoints() {
		return dealerPoints;
	}
	public void setDealerPoints(int dealerPoints) {
		this.dealerPoints = dealerPoints;
	}
	public void addDealerPoints(int points){
		dealerPoints+=points;
	}	
}
