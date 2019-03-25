package model;

import java.io.Serializable;

public abstract class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int accountID;
	private Person accountOwner;
	private String accountType;
	protected int accountBalance;

	public Account(int accountID, Person accountOwner, String accountType, int accountBalance) {
		this.accountID = accountID;
		this.accountOwner = accountOwner;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	
	public Account(int accountID) {
		this.accountID = accountID;
	}
	
	public int getAccountID() {
		return accountID;
	}
	
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public Person getAccountOwner() {
		return accountOwner;
	}
	
	public void setAccountOwner(Person accountOwner) {
		this.accountOwner = accountOwner;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public int getAccountBalance() {
		return accountBalance;
	}
	
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public abstract void addAccountBalance(int depositMoney);
	public abstract void subtractAccountBalance(int withdrawMoney);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountBalance;
		result = prime * result + accountID;
		result = prime * result + ((accountOwner == null) ? 0 : accountOwner.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountBalance != other.accountBalance)
			return false;
		if (accountID != other.accountID)
			return false;
		if (accountOwner == null) {
			if (other.accountOwner != null)
				return false;
		} else if (!accountOwner.equals(other.accountOwner))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Owner: "+ accountOwner + ", ID: "+ accountID + ", Type: " + accountType+ ", Balance: " + accountBalance;
	}

	
}
