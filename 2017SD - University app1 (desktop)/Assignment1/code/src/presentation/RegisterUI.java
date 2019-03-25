package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bll.AdministratorBLL;
import bll.RegularBLL;
import bll.ReportBLL;
import entities.Administrator;
import entities.Regular;
import entities.Report;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtName;
	private JTextField txtPNC;
	private JLabel lblPNC;
	boolean previousState = false;
	private JButton btnRegister;
	private JCheckBox boxAdministrator;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI frame = new RegisterUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterUI() {

		setTitle("RegisterUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 277, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(10, 11, 79, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 36, 79, 14);
		contentPane.add(lblPassword);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(10, 61, 79, 14);
		contentPane.add(lblName);
		
		lblPNC = new JLabel("PNC:");
		lblPNC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPNC.setBounds(10, 85, 79, 14);
		
		
		boxAdministrator = new JCheckBox("Administrator");
		boxAdministrator.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(previousState == true) {
					previousState = false;
					setSize();
				}else {
					previousState = true;
					setSize();
				}
				System.out.println(previousState);
			}
		});
		boxAdministrator.setSelected(false);
		boxAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
		boxAdministrator.setBounds(10, 82, 119, 23);
		contentPane.add(boxAdministrator);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(99, 8, 146, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(99, 33, 146, 20);
		contentPane.add(txtPassword);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(99, 58, 146, 20);
		contentPane.add(txtName);
		
		txtPNC = new JTextField();
		txtPNC.setColumns(10);
		txtPNC.setBounds(99, 83, 146, 20);
		
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0){
				if(boxAdministrator.isSelected())
				{
					try {
					AdministratorBLL administratorBll = new AdministratorBLL();
					Administrator administrator;
						administrator = new Administrator(administratorBll.findAllAdministrators().size() + 1,txtUsername.getText(),txtPassword.getText(),txtName.getText());
					
					int idA = administratorBll.insertAdministrator(administrator);
					if (idA > 0) {
						administratorBll.findAdministratorById(idA);
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					try {
					RegularBLL regularBll = new RegularBLL();
					Regular regular = new Regular(regularBll.findAllRegulars().size()+1, txtUsername.getText(), txtPassword.getText(), Integer.parseInt(txtPNC.getText()), txtName.getText());
					int idR = regularBll.insertRegular(regular);
					if (idR > 0) {
						regularBll.findRegularById(idR);
					}
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student opened Settings section", new ReportBLL().findAllReports().size() + 1));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRegister.setBounds(156, 82, 89, 23);
		contentPane.add(btnRegister);
		setSize();
	}
	
	public void setSize() {
		if(previousState) {
			this.setBounds(100, 100, 277, 151);
			this.setLocationRelativeTo(null);
			contentPane.remove(lblPNC);
			contentPane.remove(txtPNC);
			boxAdministrator.setBounds(10, 82, 119, 23);
			btnRegister.setBounds(156, 82, 89, 23);
		
		}
		else{
			this.setBounds(100, 100, 277, 181);
			this.setLocationRelativeTo(null);
			contentPane.add(lblPNC);
			contentPane.add(txtPNC);
			boxAdministrator.setBounds(10, 107, 119, 23);
			btnRegister.setBounds(156, 107, 89, 23);
		}
	}
}
