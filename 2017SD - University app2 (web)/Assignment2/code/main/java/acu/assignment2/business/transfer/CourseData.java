package acu.assignment2.business.transfer;

import java.util.List;

public class CourseData {



	@Override
	public String toString() {
		return "CourseData [id=" + id + ", name=" + name + ", administrator=" + administrator + ", students="
				+ students.toString() + "]";
	}

	private long id;
    private String name;
	private String administrator;
	private List<String> students;

	public CourseData(){}

    public CourseData(long id, String name, String administrator, List<String> students){
    	this.setId(id);
        this.setName(name);
        this.setAdministrator(administrator);
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
	
    public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}
}
