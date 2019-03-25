package presentation;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import bll.*;
import entities.Course;
import entities.Enrollment;
import entities.Grade;
import entities.Report;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradeUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableStudents;
	private JTextField txtGrade;
	private JComboBox<String> listCourses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeUI frame = new GradeUI(1);
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
	public GradeUI(int adminId) throws SQLException {
		setTitle("GradeUI");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 450, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listCourses = new JComboBox<String>();
		listCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateModel(listCourses.getSelectedItem().toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		DefaultComboBoxModel<String> DLM = new DefaultComboBoxModel<String>();
		List<Course> coursesList = new CourseBLL().findAllCourses();
	    for (int i = 0; i < coursesList.size(); i++) {
	        if(coursesList.get(i).getAdministrator_id() == adminId)
	    	DLM.addElement(coursesList.get(i).getName());
	        
	    }
	    listCourses.setModel(DLM);
		//JComboBox<?> listCourses = new JComboBox();

		listCourses.setBounds(10, 28, 414, 20);
		//listCourses.setModel(new DefaultComboBoxModel(new CourseBLL().findAllCourses().toArray()));
		contentPane.add(listCourses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 414, 176);
		contentPane.add(scrollPane);
		
		tableStudents = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Student");
		model.addColumn("Grade");
		for(Enrollment e: new EnrollmentBLL().findAllEnrollments()){
			if(e.getCourse_id() == new CourseBLL().findCourseByName(listCourses.getSelectedItem().toString()).getId()) {
				Object[] o={new RegularBLL().findRegularById(e.getStudent_id()).getName(),
						returnGrade(e)
				};
				model.addRow(o);	
				}
			}
		tableStudents.setModel(model);
		scrollPane.setViewportView(tableStudents);
		
		txtGrade = new JTextField();
		txtGrade.setBounds(49, 278, 46, 31);
		contentPane.add(txtGrade);
		txtGrade.setColumns(10);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourse.setBounds(10, 11, 207, 14);
		contentPane.add(lblCourse);
		
		JLabel lblStudentsEnrolled = new JLabel("Students Enrolled");
		lblStudentsEnrolled.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentsEnrolled.setBounds(10, 59, 207, 14);
		contentPane.add(lblStudentsEnrolled);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(10, 286, 56, 14);
		contentPane.add(lblGrade);
		
		JButton btnAddGrade = new JButton("Edit grade");
		btnAddGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddGrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					//int studId = new GradeBLL().findGradeByStudentId(idStud, idCourse)
					new GradeBLL().updateGrade(new Grade(0, new Date(System.currentTimeMillis()), Integer.parseInt(txtGrade.getText()), new CourseBLL().findCourseByName(listCourses.getSelectedItem().toString()).getId(), 0, new RegularBLL().findRegularByName(tableStudents.getValueAt(tableStudents.getSelectedRow(), 0).toString()).getId()));
					
					new ReportBLL().insertReport(new Report(new ReportBLL().findAllReports().size() + 1, new Date(System.currentTimeMillis()), "Student recieved grade: " + txtGrade.getText() + " at course: " + listCourses.getSelectedItem().toString() , new RegularBLL().findRegularByName(tableStudents.getValueAt(tableStudents.getSelectedRow(), 0).toString()).getId()));
					updateModel(listCourses.getSelectedItem().toString());
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddGrade.setBounds(272, 278, 152, 31);
		contentPane.add(btnAddGrade);
		
		JButton btnEditStudent = new JButton("Edit student");
		btnEditStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new SettingsUI(true, new RegularBLL().findRegularByName(tableStudents.getValueAt(tableStudents.getSelectedRow(), 0).toString()).getId()).setVisible(true);
			}
		});
		btnEditStudent.setBounds(110, 278, 152, 31);
		contentPane.add(btnEditStudent);
	}
	
	Object returnGrade(Enrollment e) throws SQLException {
		if(new GradeBLL().findGradeByStudentId(new RegularBLL().findRegularById(e.getStudent_id()).getId(), e.getCourse_id()).getValue() == 1)
			return (Object) "Not graded";
		else
			return new GradeBLL().findGradeByStudentId(new RegularBLL().findRegularById(e.getStudent_id()).getId(), e.getCourse_id()).getValue();
	}
	void updateModel(String courseName) throws SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Student");
		model.addColumn("Grade");
			for(Enrollment e: new EnrollmentBLL().findAllEnrollments()){
				if(e.getCourse_id() == new CourseBLL().findCourseByName(listCourses.getSelectedItem().toString()).getId()) {
					Object[] o={new RegularBLL().findRegularById(e.getStudent_id()).getName(),
							returnGrade(e)
					};
				model.addRow(o);	
				}
			}
		tableStudents.setModel(model);
	}
}
