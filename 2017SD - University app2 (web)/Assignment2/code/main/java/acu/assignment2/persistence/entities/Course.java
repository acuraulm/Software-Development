package acu.assignment2.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String name;
 
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	@ManyToOne
	@JoinColumn(name="administrator_id")
	@JsonManagedReference
	private Administrator administrator;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="course_student",
				joinColumns = {@JoinColumn(name = "fk_student")},
				inverseJoinColumns = {@JoinColumn(name = "fk_course")})
	private Set<Student> students = new HashSet<>();
	
    public long getId() {
        return id;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
		student.addCourse(this);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
		student.removeCourse(this);
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
