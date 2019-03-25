package acu.assignment2.persistence.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String username;
    @Column
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "administrator", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Course> courses = new ArrayList<>();
    
    @Override
	public String toString() {
		return "Administrator [id=" + id + ", username=" + username + ", name=" + name + ", courses=" + courses.toString() + "]";
	}

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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
    	course.setAdministrator(this);
    	this.courses.add(course);
    }
    
    public void removeCourse(Course course) {
    	this.courses.remove(course);
    }
	
}
