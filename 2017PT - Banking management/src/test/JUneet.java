package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingsAccount;
import model.SpendingsAccount;

public class JUneet {

	
	@Test
	public void testAddPerson(){
		Person P1 = new Person(0,"Acu Raul", 20);
		Account A1 = new SpendingsAccount(0, P1, "spendings", 199);
		
		HashSet<Account> Acc1 = new HashSet<Account>();
		
		Acc1.add(A1);
		HashMap<Person, HashSet<Account>> hashMap = new HashMap<Person, HashSet<Account>>();
		hashMap.put(P1, Acc1);
		Bank bankMare = new Bank(hashMap);
		
		System.out.println(bankMare.viewAllAccounts());
		System.out.println(bankMare.viewAllPersons());
		
		HashMap<Person, HashSet<Account>> emptyMap = new HashMap<Person, HashSet<Account>>();
		Bank bankPre = new Bank(emptyMap);
		bankPre.addPerson(P1);
		bankPre.addAccount(A1, P1);
		System.out.println(bankPre.viewAllAccounts());
		System.out.println(bankPre.viewAllPersons());
		assertEquals(bankPre, bankMare);
		
	}
	
	@Test
	public void testWithdraw(){
		Person P1 = new Person(0,"Acu Raul", 20);
		Account A1 = new SavingsAccount(0, P1, "spendings", 200);
		HashSet<Account> Acc1 = new HashSet<Account>();
		Acc1.add(A1);
		HashMap<Person, HashSet<Account>> hashMap = new HashMap<Person, HashSet<Account>>();
		hashMap.put(P1, Acc1);
		int initialBalance = A1.getAccountBalance();
		int money = 100;
		A1.subtractAccountBalance(money);
		System.out.println("Initial balance: " + initialBalance);
		System.out.println("After withdrawal: "+ A1.getAccountBalance());
		assertEquals(A1.getAccountBalance(), initialBalance - money - money/10 );
		
	}

}
