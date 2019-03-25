package presentation;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.TextArea;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import bll.*;
import entities.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ReportsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtStart;
	private JTextField txtEnd;
	private TextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportsUI frame = new ReportsUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ReportsUI() throws SQLException {
		setTitle("ReportsUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 461, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> DLM = new DefaultComboBoxModel<String>();
	    for(Regular r : new RegularBLL().findAllRegulars()) {
	    	DLM.addElement(r.getName());
	    }
	    comboBox.setModel(DLM);
		comboBox.setBounds(64, 8, 371, 20);
		contentPane.add(comboBox);
		
		JLabel lblStudent = new JLabel("Student:");
		lblStudent.setBounds(10, 11, 75, 14);
		contentPane.add(lblStudent);
		
		JLabel lblPeriodOfTime = new JLabel("Period of time:");
		lblPeriodOfTime.setBounds(10, 41, 87, 14);
		contentPane.add(lblPeriodOfTime);
		
		txtStart = new JTextField();
		txtStart.setBounds(99, 39, 95, 20);
		txtStart.setText("1990-01-28");
		contentPane.add(txtStart);
		txtStart.setColumns(10);
		
		txtEnd = new JTextField();
		txtEnd.setColumns(10);
		txtEnd.setBounds(217, 39, 95, 20);
		txtEnd.setText("2990-01-20");
		contentPane.add(txtEnd);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setBounds(192, 41, 21, 14);
		contentPane.add(lblTo);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					updateText(new RegularBLL().findRegularByName(comboBox.getSelectedItem().toString()).getId());
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(340, 39, 95, 23);
		contentPane.add(btnGenerate);
		
		textArea = new TextArea();
		textArea.setBounds(10, 78, 425, 278);
		contentPane.add(textArea);
	}

	void updateText(int idStud) throws SQLException, ParseException {
		textArea.setText("");
		DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
		Date dateS;
		Date dateE;
		dateS = formatter.parse(txtStart.getText());
		dateE = formatter.parse(txtEnd.getText());
		for(Report r : new ReportBLL().findAllReportsByStudentId(idStud)) {
			Date auxDate = formatter.parse(r.getDate().toString());
			if(auxDate.before(dateE) && auxDate.after(dateS)) {
				textArea.setText(textArea.getText() + r.getDate() +": " + r.getDetails() + "\n");			
			}
		System.out.println(r.getDate());
		}
	}
}
