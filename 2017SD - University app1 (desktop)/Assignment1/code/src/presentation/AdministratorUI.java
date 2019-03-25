package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import bll.AdministratorBLL;

public class AdministratorUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1665233488833079367L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorUI frame = new AdministratorUI(1);
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
	public AdministratorUI(int adminId) {
		setTitle("AdministratorUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 692, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\prof2.png"));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblWelcome.setBounds(0, 0, 173, 180);
		contentPane.add(lblWelcome);
		
		JButton btnSettings = new JButton("");
		btnSettings.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\149242.png"));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SettingsUI(false, new AdministratorBLL().findAdministratorById(adminId).getId()).setVisible(true);
			}
		});
		btnSettings.setBounds(194, 22, 145, 140);
		contentPane.add(btnSettings);
		
		JButton btnStudents = new JButton("");
		btnStudents.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\users-icon.png"));
		btnStudents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new GradeUI(adminId).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStudents.setBounds(349, 22, 145, 140);
		contentPane.add(btnStudents);
		
		JButton btnReports = new JButton("");
		btnReports.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\63-Paperclip,-add-files-icon.png"));
		btnReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new ReportsUI().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnReports.setBounds(504, 24, 145, 138);
		contentPane.add(btnReports);
		
		this.setLocationRelativeTo(null);
	}

}
