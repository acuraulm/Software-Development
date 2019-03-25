package presentation;
import bll.ReportBLL;
import bll.GradeBLL;
import entities.Grade;
import entities.Report;
import bll.EnrollmentBLL;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JButton;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import bll.CourseBLL;
import entities.Course;
import entities.Enrollment;
import bll.AdministratorBLL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class EnrollmentUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEnrolled;
	private JTable tableAvailable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnrollmentUI frame = new EnrollmentUI(2);
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
	public EnrollmentUI(int userId) throws SQLException {

		setTitle("EnrollmentUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 767, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DefaultTableModel modelEnr = new DefaultTableModel();
		modelEnr.addColumn("Course");
		modelEnr.addColumn("Teacher");
			for(Enrollment e: new EnrollmentBLL().findAllEnrollments()){
					if(e.getStudent_id() == userId) {
				Object[] o={new CourseBLL().findCourseById(e.getCourse_id()).getName(),new AdministratorBLL().findAdministratorById(new CourseBLL().findCourseById(e.getCourse_id()).getAdministrator_id()).getName()};
					modelEnr.addRow(o);	
					}
			}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(499, 32, 240, 218);
		contentPane.add(scrollPane_1);
		
	
		tableEnrolled = new JTable();
		scrollPane_1.setViewportView(tableEnrolled);
		tableEnrolled.setModel(modelEnr);
		
		JButton btnEnroll = new JButton("Enroll -->");
		btnEnroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
				
				try {
					new EnrollmentBLL().insertEnrollment(new Enrollment(tableAvailable.getSelectedRow(), userId));
					new GradeBLL().insertGrade(new Grade(new GradeBLL().findAllGrades().size()+1, new Date(System.currentTimeMillis()),1, new CourseBLL().findCourseByName(tableAvailable.getValueAt(tableAvailable.getSelectedRow(), 0).toString()).getId(),
							new CourseBLL().findCourseByName(tableAvailable.getValueAt(tableAvailable.getSelectedRow(), 0).toString()).getAdministrator_id(),
							userId));
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student enrolled in: " + (tableAvailable.getValueAt(tableAvailable.getSelectedRow(), 0)).toString(), userId));
					updateEnrollment(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnroll.setBounds(300, 35, 149, 78);
		contentPane.add(btnEnroll);
		
		JButton btnUnenroll = new JButton("<-- Unenroll");
		btnUnenroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String courseName = (tableEnrolled.getValueAt(tableEnrolled.getSelectedRow(), 0)).toString();
				try {
					new EnrollmentBLL().deleteEnrollment(new Enrollment(new CourseBLL().findCourseByName(courseName).getId(),userId));
					new GradeBLL().deleteGrade(new GradeBLL().findGradeByStudentId(userId, new CourseBLL().findCourseByName(courseName).getId()));
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student unenrolled from: " + courseName, userId));
					updateEnrollment(userId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUnenroll.setBounds(300, 157, 149, 78);
		contentPane.add(btnUnenroll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 240, 218);
		contentPane.add(scrollPane);
		
		tableAvailable = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Course");
		model.addColumn("Teacher");
			for(Course c: new CourseBLL().findAllCourses()){
					Object[] o={c.getName(),new AdministratorBLL().findAdministratorById(c.getAdministrator_id()).getName()};
					model.addRow(o);	
			}		
		tableAvailable.setModel(model);
		scrollPane.setViewportView(tableAvailable);
		
		JLabel lblCoursesAvailable = new JLabel("Available courses");
		lblCoursesAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoursesAvailable.setBounds(31, 11, 206, 14);
		contentPane.add(lblCoursesAvailable);
		
		JLabel lblCoursesEnrolledIn = new JLabel("Courses enrolled in");
		lblCoursesEnrolledIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoursesEnrolledIn.setBounds(514, 11, 206, 14);
		contentPane.add(lblCoursesEnrolledIn);
			
	}

	void updateEnrollment(int userId) throws SQLException {
		DefaultTableModel modelEnr = new DefaultTableModel();
		modelEnr.addColumn("Course");
		modelEnr.addColumn("Teacher");
			for(Enrollment e: new EnrollmentBLL().findAllEnrollments()){
					if(e.getStudent_id() == userId) {
					Object[] o={new CourseBLL().findCourseById(e.getCourse_id()).getName(),new AdministratorBLL().findAdministratorById(new CourseBLL().findCourseById(e.getCourse_id()).getAdministrator_id()).getName()};
					modelEnr.addRow(o);	
					}
			}
		tableEnrolled.setModel(modelEnr);
		
	}
}
