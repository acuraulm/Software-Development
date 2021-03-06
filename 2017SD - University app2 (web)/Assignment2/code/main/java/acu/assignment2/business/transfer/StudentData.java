package acu.assignment2.business.transfer;

import java.util.List;

public class StudentData {

    private long id;
    private String username;
    @Override
	public String toString() {
		return "StudentData [id=" + id + ", username=" + username + ", name=" + name + ", courses=" + courses.toString() + "]";
	}

	private String name;
    private List<CourseData> courses;
    
    public StudentData(){}

    public StudentData(long id,String username, String name, List<CourseData> courses){
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

	public void addCourse(CourseData course) {
		this.courses.add(course);
	}
	public void removeCourse(CourseData course) {
		this.courses.remove(course);
	}
	public List<CourseData> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseData> courses) {
		this.courses = courses;
	}
}
