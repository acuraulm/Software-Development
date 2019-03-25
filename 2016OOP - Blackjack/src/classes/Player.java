package classes;
public class Player {	
	public int playerCash = 1000;
	public String playerName;
	public int playerPoints = 0;
	public Player(){
	}
	public int getPlayerCash() {
		return playerCash;
	}
	public void setPlayerCash(int playerCash) {
		this.playerCash = playerCash;
	}
	public void addPlayerCash(int cash){
		playerCash+=cash;
	}
	public void removePlayerCash(int cash){
		playerCash-=cash;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}
	public int getPlayerPoints() {
		return playerPoints;
	}
	public void addPlayerPoints(int points){
		playerPoints+=points;
	}
}
