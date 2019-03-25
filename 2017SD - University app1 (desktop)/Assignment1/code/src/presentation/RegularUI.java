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
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import bll.RegularBLL;
import bll.ReportBLL;
import entities.Report;
public class RegularUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegularUI frame = new RegularUI(1);
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
	public RegularUI(int regularId) {
		setTitle("StudentUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 692, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\graduate.png"));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblWelcome.setBounds(504, 0, 259, 256);
		contentPane.add(lblWelcome);
		
		JButton btnSettings = new JButton("");
		btnSettings.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\149242.png"));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				try {
					new SettingsUI(true, new RegularBLL().findRegularById(regularId).getId()).setVisible(true);
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student opened Settings section", regularId));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSettings.setBounds(332, 22, 145, 140);
		contentPane.add(btnSettings);
		
		JButton btnProfile = new JButton("");
		btnProfile.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\103339.png"));
		btnProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new ProfileUI(regularId).setVisible(true);
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student opened Profile menu", regularId));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnProfile.setBounds(177, 22, 145, 140);
		contentPane.add(btnProfile);
		
		JButton btnEnrollment = new JButton("");
		btnEnrollment.setIcon(new ImageIcon("P:\\Workspace Eclipse\\Assignment-A1\\src\\401k-enrollment-meetings.png"));
		btnEnrollment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new EnrollmentUI(regularId).setVisible(true);
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student opened Enrollment menu", regularId));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnrollment.setBounds(22, 22, 145, 140);
		contentPane.add(btnEnrollment);
		this.setLocationRelativeTo(null);
	}

}
