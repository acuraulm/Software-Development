package model;

public class SpendingsAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	public SpendingsAccount(int accountID, Person accountOwner, String accountType, int accountBalance) {
		super(accountID, accountOwner, accountType, accountBalance);
		// TODO Auto-generated constructor stub
	}
	
	public void addAccountBalance(int depositMoney){
		this.accountBalance += depositMoney;
	}
	public void subtractAccountBalance(int withdrawMoney){
		if(withdrawMoney <= this.accountBalance)
			this.accountBalance -= withdrawMoney;
	}
}
