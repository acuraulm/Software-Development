package presentation;
import bll.*;
import entities.Report;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainUI {

	private JFrame frmAssignmenta;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmAssignmenta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmAssignmenta = new JFrame();
		frmAssignmenta.setTitle("Assignment-A1");
		frmAssignmenta.setBounds(100, 100, 282, 155);
		frmAssignmenta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAssignmenta.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setBounds(85, 11, 171, 20);
		frmAssignmenta.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				     try {
						decideUser();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   }
			}
		});
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setColumns(10);
		txtPassword.setBounds(85, 42, 171, 20);
		frmAssignmenta.getContentPane().add(txtPassword);
		frmAssignmenta.setLocationRelativeTo(null);
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					decideUser();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnLogIn.setBounds(117, 84, 139, 23);
		frmAssignmenta.getContentPane().add(btnLogIn);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(10, 14, 65, 14);
		frmAssignmenta.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 45, 65, 14);
		frmAssignmenta.getContentPane().add(lblPassword);
		
		JLabel lblNewHere = new JLabel("New here?");
		lblNewHere.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewHere.setBounds(24, 73, 65, 14);
		frmAssignmenta.getContentPane().add(lblNewHere);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new RegisterUI().setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRegister.setBounds(20, 87, 69, 20);
		frmAssignmenta.getContentPane().add(btnRegister);
	}
	@SuppressWarnings("deprecation")
	void decideUser() throws SQLException {
		DecisionBLL decisionBll = new DecisionBLL();
		if(decisionBll.isValid(txtUsername.getText(), txtPassword.getText())){
			if(decisionBll.isAdmin()) {
				AdministratorUI administratorUI = new AdministratorUI(new AdministratorBLL().findAdministratorByUsername(txtUsername.getText()).getId());
				administratorUI.setVisible(true);
			}
			else {
				RegularUI regularUI = new RegularUI(new RegularBLL().findRegularByUsername(txtUsername.getText()).getId());
				new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student logged in", new RegularBLL().findRegularByUsername(txtUsername.getText()).getId()));
				regularUI.setVisible(true);
			}		
		}
		else {
			System.out.println("Error. Username or password incorrect!");
		}
	}
}
