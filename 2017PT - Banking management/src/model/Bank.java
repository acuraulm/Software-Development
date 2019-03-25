package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Bank implements BankProc, Serializable {

	HashMap<Person, HashSet<Account>> bank;
	
	public Bank(HashMap<Person, HashSet<Account>> bank) {
		super();
		this.bank = bank;
	}

	public void addPerson(Person person) {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		int initialSize = bank.size();
		assert person != null: "Person null";
		assert !bank.containsKey(person): "Duplicate person";
		bank.put(person, new HashSet<Account>());
		assert bank.containsKey(person):" Person not added";
		assert bank.size() == initialSize + 1: " Person not added";
		assert isWellFormed(): "Invalid bank";
	}

	public void removePerson(Person person) {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		int initialSize = bank.size();
		assert person != null: "Person null";
		assert bank.containsKey(person): "Person does not exist";
		bank.remove(person);
		assert !bank.containsKey(person):" Person not removed";
		assert bank.size() == initialSize - 1: " Person not removed";
		assert isWellFormed(): "Invalid bank";
		
	}
	public void editPerson(Person person, String name, int age){
		// TODO Auto-generated method stub
				assert isWellFormed(): "Invalid bank";
				int initialSize = bank.size();
				assert person != null: "Person null";
				assert bank.containsKey(person): "Person does not exist";
				person.setPersonAge(age);
				person.setPersonName(name);
				assert !bank.containsKey(person):" Person not removed";
				assert bank.size() == initialSize: " Person not removed";
				assert isWellFormed(): "Invalid bank";
	}
	
	public List<Person> viewAllPersons() {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		List<Person> p = new ArrayList<Person>();
		for (Person pe : bank.keySet())
			if (pe != null)
				p.add(pe);
		assert isWellFormed(): "Invalid bank";
		return p;
	}
	public Person viewPersonByID(int personId) {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		Person p = new Person(personId);
		for (Person pe : bank.keySet())
			if (pe != null && pe.getPersonID() == personId)
				p = pe;
		assert isWellFormed(): "Invalid bank";
		return p;
	}
	public Account viewAccountByID(int accountId, Person person){
		// TODO Auto-generated method stub
				assert isWellFormed(): "Invalid bank";
				for(Account ac: bank.get(person)){
					if(ac.getAccountID() == accountId)
							return ac;
				}	
				assert isWellFormed(): "Invalid bank";
				return null;
	}
	public void addAccount(Account account, Person person) {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		assert person != null: "Person null";
		assert account != null: "Account null";
		assert !bank.get(person).contains(account): "Duplicate account";
		bank.get(person).add(account);
		bank.put(person, bank.get(person));
		assert bank.get(person).contains(account): "Account not added";
		assert isWellFormed(): "Invalid bank";
	}


	public void removeAccount(Account account, Person person) {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		assert person != null: "Person null";
		assert account != null: "Account null";
		assert bank.get(person).contains(account):"Account does not exist";
		bank.get(person).remove(account);
		assert !bank.get(person).contains(account): "Account not deleted";
		assert isWellFormed(): "Invalid bank";
		
	}

	public List<Account> viewAllAccountsOf(Person person) {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		List<Account> a = new ArrayList<Account>();
		for(Account ac: bank.get(person)){
					a.add(ac);
		}	
		assert isWellFormed(): "Invalid bank";
		return a;
		
	}

	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return this!=null;
	}

	@Override
	public List<Account> viewAllAccounts() {
		// TODO Auto-generated method stub
		assert isWellFormed(): "Invalid bank";
		List<Account> a = new ArrayList<Account>();
		for(Person p : bank.keySet()){
			if(p!=null)
		for(Account ac: bank.get(p)){
			if(ac!=null)
					a.add(ac);
		}	
		}
		assert isWellFormed(): "Invalid bank";
		return a;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
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
		Bank other = (Bank) obj;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		return true;
	}

}