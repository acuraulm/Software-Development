package entities;

public class Course {
	private int id;
	private String name;
	private int administrator_id;
	
	public Course(int id, String name, int administrator_id) {
		super();
		this.id = id;
		this.name = name;
		this.administrator_id = administrator_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdministrator_id() {
		return administrator_id;
	}
	public void setAdministrator_id(int administrator_id) {
		this.administrator_id = administrator_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
