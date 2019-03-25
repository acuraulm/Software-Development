package acu.project1.persistence.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Groupa {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Column
	private String number;
	
	@ManyToOne
	@JoinColumn(name="specialization_id")
	@JsonManagedReference
	private Specialization specialization;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "groupa", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Student> students = new ArrayList<>();
	
	public Specialization getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	public long getId() {
		return id;
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
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public void addStudent(Student student) {
		student.setGroupa(this);
		this.students.add(student);
	}
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
}
