package entities;

import java.util.Date;

public class Grade {

	private int id;
	private Date date;
	private int value;
	private int course_id;
	private int course_Administrator_id;
	private int student_id;
	public Grade(int id, Date date, int value, int course_id, int course_Administrator_id, int student_id) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.course_id= course_id;
		this.course_Administrator_id = course_Administrator_id;
		this.setStudent_id(student_id);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getCourse_Administrator_id() {
		return course_Administrator_id;
	}

	public void setCourse_Administrator_id(int course_Administrator_id) {
		this.course_Administrator_id = course_Administrator_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
}
