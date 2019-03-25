package acu.project1.business.transfer;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    @Override
	public String toString() {
		return "StudentData [id=" + id + ", username=" + username + ", name=" + name + ", groupa=" + groupa
				+ ", courses=" + courses + ", grades=" + grades + "]";
	}

	private long id;
    private String username;
	private String name;
	private String groupa;
    private List<CourseData> courses = new ArrayList<CourseData>();
    private List<GradeData> grades = new ArrayList<GradeData>();
    public StudentData(){}
    private StudentData(StudentDataBuilder studentDataBuilder) {
		this.name = studentDataBuilder.name;
		this.username = studentDataBuilder.username;
		this.groupa = studentDataBuilder.groupa;
		this.courses= studentDataBuilder.courses;
		this.grades = studentDataBuilder.grades;
	}
    public StudentData(long id,String username, String name, String groupa,List<CourseData> courses, List<GradeData> grades){
        this.setId(id);
    	this.setUsername(username);
        this.setName(name);
        this.setGroupa(groupa);
        this.setCourses(courses);
        this.setGrades(grades);
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

	public List<GradeData> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeData> grades) {
		this.grades = grades;
	}
	
	public void addGrade(GradeData grade) {
		this.grades.add(grade);
		grade.setStudent(this.getName());
	}

	public void removeGrade(GradeData grade) {
		this.grades.remove(grade);
	}
	public String getGroupa() {
		return groupa;
	}

	public void setGroupa(String groupa) {
		this.groupa = groupa;
	}
	
	public static class StudentDataBuilder {

		private long id;
	    private String username;
		private String name;
		private String groupa;
		
		public StudentDataBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public StudentDataBuilder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public StudentDataBuilder setCoordinates(String groupa) {
			this.groupa = groupa;
			return this;
		}

		private List<CourseData> courses = new ArrayList<CourseData>();
		
		public StudentDataBuilder setCourses(List<CourseData> courses) {
			this.courses = courses;
			return this;
		}
	    private List<GradeData> grades = new ArrayList<GradeData>();
	    public StudentDataBuilder setGrades(List<GradeData> grades) {
			this.grades = grades;
			return this;
		}
		public StudentData build() {
			return new StudentData(this);
		}

	}
	
}
