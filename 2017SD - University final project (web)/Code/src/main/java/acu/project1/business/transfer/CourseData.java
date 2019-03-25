package acu.project1.business.transfer;

import java.util.List;

public class CourseData {



	@Override
	public String toString() {
		return "CourseData [id=" + id + ", name=" + name + ", teacher=" + teacher + ", students=" + students + "]";
	}
	private long id;
    private String name;
	private String teacher;
	private List<String> students;

	public CourseData(){}

    public CourseData(long id, String name, String teacher, List<String> students){
    	this.setId(id);
        this.setName(name);
        this.setTeacher(teacher);
        this.setStudents(students);
    }
    
    public void setId(long id) {
    	this.id = id;
    }
    public long getId() {
        return id;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public List<String> getStudents() {
		return students;
	}

	public void addStudent(StudentData student) {
		this.students.add(student.getName());
		student.getCourses().add(this);
	}
	public void setStudents(List<String> students) {
		this.students = students;
	}
}
