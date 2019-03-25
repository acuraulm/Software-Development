package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Gui implements Serializable{

	private JFrame frame;
	private JScrollPane scrollPane;
	private JLabel lblPersonId;
	private JLabel lblAccountId;
	private JButton btnAccounts;
	private JButton btnPersons;
	private JButton btnPersonAdd;
	private JButton btnPersonEdit;
	private JButton btnPersonRemove;
	private JButton btnAccountAdd;
	private JButton btnAccountWithdraw;
	private JButton btnAccountDeposit;
	private JButton btnAccountRemove;
	private JPanel panelAccounts;
	private JPanel panelPersons;
	private JButton btnSpendings;
	private JButton btnSavings;
	HashMap<Person, HashSet<Account>> hashMap = new HashMap<Person, HashSet<Account>>();
	//Bank bank = new Bank(hashMap);
	Bank bank;
	private JTextField txtPersonID;
	private JTextField txtPersonAge;
	private JTextField txtPersonName;
	private JTextField txtAccountID;
	private JTextField txtAccountOwner;
	private JTextField txtAccountBalance;
	private JTextField txtMoney;
	private String accType = "Savings";
	
	JTable table;
	JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Gui() throws ClassNotFoundException, IOException {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, IOException {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnPersons = new JButton("Persons");
		btnPersons.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelPersons.setVisible(true);
				panelAccounts.setVisible(false);
				btnPersons.setEnabled(false);
				btnAccounts.setEnabled(true);
				try {
					readBank();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tabS();
			}
		});
		btnPersons.setBounds(10, 11, 313, 57);
		frame.getContentPane().add(btnPersons);
		
		btnAccounts = new JButton("Accounts");
		btnAccounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelAccounts.setVisible(true);
				panelPersons.setVisible(false);
				btnPersons.setEnabled(true);
				btnAccounts.setEnabled(false);
				try {
					readBank();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tabA();
			}
		});
		btnAccounts.setBounds(362, 11, 313, 57);
		frame.getContentPane().add(btnAccounts);
		
		panelPersons = new JPanel();
		panelPersons.setVisible(false);
		panelPersons.setBounds(10, 79, 664, 301);
		frame.getContentPane().add(panelPersons);
		panelPersons.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 91, 2, 2);
		panelPersons.add(scrollPane);
		
		lblPersonId = new JLabel("Person ID:");
		lblPersonId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPersonId.setBounds(10, 248, 71, 14);
		panelPersons.add(lblPersonId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setBounds(10, 273, 55, 14);
		panelPersons.add(lblName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAge.setBounds(130, 248, 55, 14);
		panelPersons.add(lblAge);
		
		txtPersonID = new JTextField();
		txtPersonID.setBounds(91, 245, 41, 20);
		panelPersons.add(txtPersonID);
		txtPersonID.setColumns(10);
		
		txtPersonName = new JTextField();
		txtPersonName.setColumns(10);
		txtPersonName.setBounds(70, 270, 166, 20);
		panelPersons.add(txtPersonName);
		
		txtPersonAge = new JTextField();
		txtPersonAge.setColumns(10);
		txtPersonAge.setBounds(195, 245, 41, 20);
		panelPersons.add(txtPersonAge);
		
		btnPersonAdd = new JButton("Add");
		btnPersonAdd.setBounds(246, 248, 117, 42);
		btnPersonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Person(0, txtPersonName.getText(), Integer.parseInt(txtPersonAge.getText())), new HashSet<Account>());
				bank.addPerson(new Person(Integer.parseInt(txtPersonID.getText()), txtPersonName.getText(), Integer.parseInt(txtPersonAge.getText())));
				System.out.println(bank.viewAllPersons());
				tabS();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelPersons.add(btnPersonAdd);
		
		btnPersonEdit = new JButton("Edit");
		btnPersonEdit.setBounds(390, 248, 117, 42);
		btnPersonEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Person(0, txtPersonName.getText(), Integer.parseInt(txtPersonAge.getText())), new HashSet<Account>());
				bank.editPerson(bank.viewPersonByID(Integer.parseInt(txtPersonID.getText())), txtPersonName.getText(), Integer.parseInt(txtPersonAge.getText()));
				System.out.println(bank.viewAllPersons());
				tabS();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelPersons.add(btnPersonEdit);
		
		btnPersonRemove = new JButton("Remove");
		btnPersonRemove.setBounds(537, 248, 117, 42);
		btnPersonRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Person(0, txtPersonName.getText(), Integer.parseInt(txtPersonAge.getText())), new HashSet<Account>());
				bank.removePerson(bank.viewPersonByID(Integer.parseInt(txtPersonID.getText())));
				System.out.println(bank.viewAllPersons());
				tabS();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelPersons.add(btnPersonRemove);

		panelAccounts = new JPanel();
		panelAccounts.setVisible(false);
		panelAccounts.setBounds(10, 79, 664, 334);
		frame.getContentPane().add(panelAccounts);
		panelAccounts.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 91, 2, 2);
		panelAccounts.add(scrollPane);
		
		lblAccountId = new JLabel("Account ID:");
		lblAccountId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAccountId.setBounds(10, 248, 71, 14);
		panelAccounts.add(lblAccountId);
		
		JLabel lblNameA = new JLabel("Owner:");
		lblNameA.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNameA.setBounds(10, 273, 55, 14);
		panelAccounts.add(lblNameA);
		
		JLabel lblAgeA = new JLabel("Balance:");
		lblAgeA.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAgeA.setBounds(130, 248, 55, 14);
		panelAccounts.add(lblAgeA);
		
		txtAccountID = new JTextField();
		txtAccountID.setBounds(91, 245, 41, 20);
		panelAccounts.add(txtAccountID);
		txtAccountID.setColumns(10);
		
		txtAccountOwner = new JTextField();
		txtAccountOwner.setColumns(10);
		txtAccountOwner.setBounds(70, 270, 166, 20);
		panelAccounts.add(txtAccountOwner);
		
		txtAccountBalance = new JTextField();
		txtAccountBalance.setColumns(10);
		txtAccountBalance.setBounds(195, 245, 41, 20);
		panelAccounts.add(txtAccountBalance);
		
		btnAccountAdd = new JButton("Add");
		btnAccountAdd.setBounds(246, 248, 117, 42);
		btnAccountAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Account(0, txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText())), new HashSet<Account>());
				//bank.addAccount(new Account(Integer.parseInt(txtAccountID.getText()), txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText())));
				if(accType == "Spendings")
				bank.addAccount(new SpendingsAccount(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID(Integer.parseInt(txtAccountOwner.getText())), "Spendings", Integer.parseInt(txtAccountBalance.getText())), bank.viewPersonByID(Integer.parseInt(txtAccountOwner.getText())));
				if(accType == "Savings")
				bank.addAccount(new SavingsAccount(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID(Integer.parseInt(txtAccountOwner.getText())), "Savings", Integer.parseInt(txtAccountBalance.getText())), bank.viewPersonByID(Integer.parseInt(txtAccountOwner.getText())));
				System.out.println(bank.viewAllAccounts());
				tabA();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelAccounts.add(btnAccountAdd);
		
		btnAccountWithdraw = new JButton("Withdraw");
		btnAccountWithdraw.setBounds(390, 248, 117, 19);
		btnAccountWithdraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Account(0, txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText())), new HashSet<Account>());
				//bank.editAccount(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText())), txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText()));
				//if(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText())))).getAccountType() == "Spendings")
				if(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText())))).getAccountBalance() > Integer.parseInt(txtMoney.getText()))	
					bank.viewAccountByID(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText())))).subtractAccountBalance(Integer.parseInt(txtMoney.getText()));
				else
					System.out.println("Not enough money");
				//if(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText())))).getAccountType() == "Savings")
										
				System.out.println(bank.viewAllAccounts());
				tabA();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelAccounts.add(btnAccountWithdraw);
	
		btnAccountDeposit = new JButton("Deposit");
		btnAccountDeposit.setBounds(390, 275, 117, 18);
		btnAccountDeposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Account(0, txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText())), new HashSet<Account>());
				//bank.editAccount(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText())), txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText()));
				
				bank.viewAccountByID(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText())))).addAccountBalance(Integer.parseInt(txtMoney.getText()));
				System.out.println(bank.viewAllAccounts());
				tabA();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelAccounts.add(btnAccountDeposit);
		
		btnAccountRemove = new JButton("Remove");
		btnAccountRemove.setBounds(537, 248, 117, 42);
		btnAccountRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//hashMap.put(new Account(0, txtAccountName.getText(), Integer.parseInt(txtAccountAge.getText())), new HashSet<Account>());
				//bank.removeAccount(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText())));
				bank.removeAccount(bank.viewAccountByID(Integer.parseInt(txtAccountID.getText()), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText())))), bank.viewPersonByID((Integer.parseInt(txtAccountOwner.getText()))));	
				System.out.println(bank.viewAllAccounts());
				tabA();
				try {
					writeBank();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelAccounts.add(btnAccountRemove);
		
		btnSavings = new JButton("Savings");
		btnSavings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				accType = "Savings";
				btnSpendings.setEnabled(true);
				btnSavings.setEnabled(false);
			}
		});
		btnSavings.setBounds(41, 303, 89, 23);
		panelAccounts.add(btnSavings);
		
		btnSpendings = new JButton("Spendings");
		btnSpendings.setBounds(145, 303, 89, 23);
		btnSpendings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				accType = "Spendings";
				btnSpendings.setEnabled(false);
				btnSavings.setEnabled(true);
			}
		});
		panelAccounts.add(btnSpendings);
		
		txtMoney = new JTextField();
		txtMoney.setHorizontalAlignment(SwingConstants.CENTER);
		txtMoney.setBounds(390, 304, 117, 22);
		panelAccounts.add(txtMoney);
		txtMoney.setColumns(10);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(344, 307, 46, 14);
		panelAccounts.add(lblMoney);
	}
	public void tabS(){
		table = new JTable();
		table.setBounds(10, 11, 644, 226);
		table.setVisible(true);
		panelPersons.add(table);
		DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Age"}, 0);
		String[] header = {"ID", "Name", "Age"};
		model.addRow(header);
		for(Person p:bank.viewAllPersons()){
	          model.addRow(new Object[]{p.getPersonID(),p.getPersonName(), p.getPersonAge()});
	     }
	    table.setModel(model);
	}
	public void tabA(){
		table_1 = new JTable();
		table_1.setBounds(10, 11, 644, 226);
		panelAccounts.add(table_1);
		DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Owner", "Type", "Balance"}, 0);
		String[] header = {"ID", "Owner", "Type", "Balance"};
		model.addRow(header);
		for(Account a:bank.viewAllAccounts()){
	          model.addRow(new Object[]{a.getAccountID(),a.getAccountOwner(), a.getAccountType(), a.getAccountBalance()});
	     }
	    table_1.setModel(model);
	}
	
	public void writeBank() throws IOException{
		FileOutputStream writeB = new FileOutputStream("bank.ser");
		ObjectOutputStream writeBos = new ObjectOutputStream(writeB);
		writeBos.writeObject(bank);
		writeBos.close();
		writeB.close();
	}
	public void readBank() throws IOException, ClassNotFoundException{
		FileInputStream readB = new FileInputStream("bank.ser");
		ObjectInputStream readBis = new ObjectInputStream(readB);
		bank = (Bank) readBis.readObject();
		readBis.close();
		readB.close();
	}
}

