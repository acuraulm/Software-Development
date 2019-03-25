package model;

import java.util.List;

public interface BankProc {
	
	/**
	 * @pre person != null
	 * @pre !bank.containsKey(person)
	 * @post bank.containsKey(person)
	 * @post bank.size()++
	 * @param person
	 * @return
	 */
	public void addPerson(Person person);
	
	/**
	* @pre person != null
	* @pre bank.cotainsKey(person)
	* @post !bank.containsKey(person)
	* @post bank.size()--
	* @param person
	* @return
	*/
	public void removePerson(Person person);
	
	/**
	* @pre person != null
	* @pre bank.cotainsKey(person)
	* @post bank.containsKey(person)
	* @nochange bank.size()
	* @param person
	* @param name
	* @param age
	* @return
	*/
	public void editPerson(Person person, String name, int age);
	
	/**
	 * @pre !bank.isEmtpy()
	 * @nochange bank.size()
	 * @return
	 */
	public List<Person> viewAllPersons();
	
	/**
	 * @pre account != null
	 * @pre person != null
	 * @param account
	 */
	public void addAccount(Account account, Person person);
	
	/**
	 * @pre account != null
	 * @pre person != null
	 * @param account
	 */
	public void removeAccount(Account account, Person person);
	
	/**
	 * @pre !bank.isEmtpy()
	 * @nochange bank.size()
	 * @return
	 */
	public List<Account> viewAllAccounts();
	/**
	 * @pre !bank.isEmtpy()
	 * @nochange bank.size()
	 * @param person
	 * @return
	 */
	public List<Account> viewAllAccountsOf(Person person);
	
	/**
	 * 
	 * @return
	 */
	public boolean isWellFormed();
	
	
}
/*
/**
 * @pre account != null
 * @pre person != null
 * @param account
 * @param person
 * @param owner
 * @param type
 * @param balance

public void editAccount(Account account, Person person, Person owner, String type, int balance);
*/