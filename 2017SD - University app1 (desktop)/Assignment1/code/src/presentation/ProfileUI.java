package presentation;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JTable;
import bll.StudentProfileBLL;
import entities.Enrollment;
import bll.CourseBLL;
import bll.EnrollmentBLL;
import bll.GradeBLL;
import bll.GroupBLL;
import bll.RegularBLL;

import javax.swing.JScrollPane;
public class ProfileUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIDnumber;
	private JTextField txtGroup;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileUI frame = new ProfileUI(2);
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
	public ProfileUI(int userId) throws SQLException {
		setTitle("ProfileUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 291, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdnumber = new JLabel("IDNumber:");
		lblIdnumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdnumber.setBounds(10, 14, 65, 14);
		contentPane.add(lblIdnumber);
		
		txtIDnumber = new JTextField();
		txtIDnumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtIDnumber.setEnabled(false);
		txtIDnumber.setEditable(false);
		txtIDnumber.setColumns(10);
		txtIDnumber.setBounds(85, 11, 180, 20);
		txtIDnumber.setText(new StudentProfileBLL().findStudentProfileById(userId).getIdentification_number());
		contentPane.add(txtIDnumber);
		
		JLabel lblGroup = new JLabel("Group:");
		lblGroup.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGroup.setBounds(10, 45, 65, 14);
		contentPane.add(lblGroup);
		
		txtGroup = new JTextField();
		txtGroup.setHorizontalAlignment(SwingConstants.CENTER);
		txtGroup.setEnabled(false);
		txtGroup.setEditable(false);
		txtGroup.setColumns(10);
		txtGroup.setBounds(85, 42, 180, 20);
		txtGroup.setText(new GroupBLL().findGroupById(new StudentProfileBLL().findStudentProfileById(userId).getGroup_id()).getNumber() + "");
		contentPane.add(txtGroup);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 255, 207);
		contentPane.add(scrollPane);
		
		table = new JTable();
		DefaultTableModel modelEnr = new DefaultTableModel();
		modelEnr.addColumn("Course");
		modelEnr.addColumn("Grade");
			for(Enrollment e: new EnrollmentBLL().findAllEnrollments()){
					if(e.getStudent_id() == userId) {
					Object[] o={new CourseBLL().findCourseById(e.getCourse_id()).getName(),
							returnGrade(e)};
					modelEnr.addRow(o);	
					}
			}
		table.setModel(modelEnr);
		scrollPane.setViewportView(table);
	}
	Object returnGrade(Enrollment e) throws SQLException {
		if(new GradeBLL().findGradeByStudentId(new RegularBLL().findRegularById(e.getStudent_id()).getId(), e.getCourse_id()).getValue() == 1)
			return (Object) "Not graded";
		else
			return new GradeBLL().findGradeByStudentId(new RegularBLL().findRegularById(e.getStudent_id()).getId(), e.getCourse_id()).getValue();
	}
}
