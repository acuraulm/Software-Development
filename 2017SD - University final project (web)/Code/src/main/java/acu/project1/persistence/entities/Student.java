package acu.project1.persistence.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String username;
    @Column
    private String name;

    @ManyToOne
	@JoinColumn(name="groupa_id")
	@JsonManagedReference
	private Groupa groupa;
    
    public Groupa getGroupa() {
		return groupa;
	}

	public void setGroupa(Groupa groupa) {
		this.groupa = groupa;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students",cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Grade> grades = new ArrayList<>();
    
    public long getId() {
        return id;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	//	course.addStudent(this);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	//	course.removeStudent(this);
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	public void addGrade(Grade grade) {
    	grade.setStudent(this);
    	this.grades.add(grade);
    }
    
    public void removeCourse(Grade grade) {
    	this.grades.remove(grade);
    }

}
