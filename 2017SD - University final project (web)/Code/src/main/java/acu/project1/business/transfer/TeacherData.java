package acu.project1.business.transfer;

import java.util.ArrayList;
import java.util.List;

import acu.project1.business.transfer.CourseData;

public class TeacherData {

    @Override
	public String toString() {
		return "TeacherData [id=" + id + ", username=" + username + ", name=" + name + ", courses=" + courses + "]";
	}

	private long id;
    private String username;
    private String name;
    private List<CourseData> courses;

	public TeacherData(){}

    public TeacherData(long id,String username, String name, List<CourseData> courses){
    	this.setId(id);
        this.setUsername(username);
        this.setName(name);
        this.setCourses(courses);
    }
    
    public void setId(long id) {
		this.id = id;
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

	public List<CourseData> getCourses() {
		return courses;
	}
	
	public void setCourses(List<CourseData> courses) {
		this.courses = courses;
	}
	
	public void addCourse(CourseData course) {
		this.courses.add(course);
		course.setTeacher(this.getName());
	}
	
	public void removeCourse(CourseData course) {
		this.courses.remove(course);
	}
	
}
