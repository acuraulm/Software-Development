package entities;

import java.util.Date;

public class Report {
	private int id = 0;
	private Date date;
	private String details;
	private int student_id;
	public Report(int id, Date date, String details, int student_id) {
		super();
		this.id = id;
		this.date = date;
		this.details = details;
		this.student_id = student_id;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}


}