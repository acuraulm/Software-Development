package acu.project1.business.transfer;

import java.util.Date;

public class GradeData {
	
	private long id;
	private int value;
	private Date date;
	private int course_id;
	private String student;
	
	@Override
	public String toString() {
		return "GradeData [id=" + id + ", value=" + value + ", date=" + date + ", course_id=" + course_id + ", student="
				+ student + "]";
	}

	public GradeData() {}

	public GradeData(long id, int value, Date date, int course_id, String student) {
		super();
		this.id = id;
		this.value = value;
		this.date = date;
		this.course_id = course_id;
		this.student = student;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}
	
}
	
