package acu.project1.business.transfer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import acu.project1.persistence.entities.Specialization;

public class GroupaData {
	
    @Override
	public String toString() {
		return "GroupaData [id=" + id + ", number=" + number + ", students=" + students + "]";
	}
	private long id;
	private String number;
	private String specialization;
	private List<String> students = new ArrayList<>();
	
	public long getId() {
		return id;
	}
	public GroupaData() {
		super();
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public GroupaData(long id, String number, String specialization, List<String> students) {
		super();
		this.id = id;
		this.specialization = specialization;
		this.number = number;
		this.students = students;
	}
	public List<String> getStudents() {
		return students;
	}
	public void setStudents(List<String> students) {
		this.students = students;
	}
	public void addStudent(String studentData) {
		this.students.add(studentData);
	}
	public void removeStudent(String studentData) {
		this.students.remove(studentData);
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
}
