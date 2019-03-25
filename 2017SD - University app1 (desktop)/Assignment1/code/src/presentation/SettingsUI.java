package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bll.AdministratorBLL;
import bll.RegularBLL;
import bll.ReportBLL;
import entities.Administrator;
import entities.Regular;
import entities.Report;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;

public class SettingsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtPnc;
	private JTextField txtName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsUI frame = new SettingsUI(true, 1);
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
	public SettingsUI(boolean isRegular, int userId) {
		setTitle("SettingsUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 283, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setEnabled(false);
		txtUsername.setEditable(false);
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setBounds(85, 11, 171, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		if(isRegular)
				txtUsername.setText(new RegularBLL().findRegularById(userId).getUsername());
		else
				txtUsername.setText(new AdministratorBLL().findAdministratorById(userId).getUsername());
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setColumns(10);
		txtPassword.setBounds(85, 42, 171, 20);
		if(isRegular)
			txtPassword.setText(new RegularBLL().findRegularById(userId).getPassword());
		else
			txtPassword.setText(new AdministratorBLL().findAdministratorById(userId).getPassword());
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(10, 14, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(10, 45, 65, 14);
		contentPane.add(lblPassword);
		
		if(isRegular){
		JLabel lblPnc = new JLabel("PNC:");
		lblPnc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPnc.setBounds(10, 107, 65, 14);
		contentPane.add(lblPnc);
		}
		if(isRegular){
		txtPnc = new JTextField();
		txtPnc.setHorizontalAlignment(SwingConstants.CENTER);
		txtPnc.setColumns(10);
		txtPnc.setBounds(85, 104, 171, 20);
		txtPnc.setText(new RegularBLL().findRegularById(userId).getPnc() + "");
		contentPane.add(txtPnc);
		}
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(10, 76, 65, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setColumns(10);
		txtName.setBounds(85, 73, 171, 20);
		if(isRegular)
			txtName.setText(new RegularBLL().findRegularById(userId).getName());
		else
			txtName.setText(new AdministratorBLL().findAdministratorById(userId).getName());
		contentPane.add(txtName);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(isRegular) {
					RegularBLL regularBll = new RegularBLL();
					try {
						int id = regularBll.findRegularByUsername(txtUsername.getText()).getId();
						String username = txtUsername.getText();
						String newPassword = txtPassword.getText();
						int pnc = Integer.parseInt(txtPnc.getText());
						String newName = txtName.getText();	
						regularBll.updateRegular(new Regular(id, username, newPassword, pnc, newName));
						new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student modified personal information. password: " + newPassword + ", pnc: "+ pnc + ", name: " + newName, userId));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					AdministratorBLL administratorBll = new AdministratorBLL();
					try {
						int id = administratorBll.findAdministratorByUsername(txtUsername.getText()).getId();
						String username = txtUsername.getText();
						String newPassword = txtPassword.getText();
						String newName = txtName.getText();
						administratorBll.updateAdministrator(new Administrator(id, username, newPassword, newName));
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnUpdate.setBounds(167, 138, 89, 23);
		contentPane.add(btnUpdate);
	}
}
