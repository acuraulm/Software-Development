package entities;

public class StudentProfile {

	private int id;
	private String identification_number;
	private int group_id;
	private int regular_id;
	public StudentProfile(int id, String identification_number, int group_id, int regular_id) {
		super();
		this.id = id;
		this.identification_number = identification_number;
		this.group_id = group_id;
		this.regular_id = regular_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentification_number() {
		return identification_number;
	}
	public void setIdentification_number(String identification_number) {
		this.identification_number = identification_number;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getRegular_id() {
		return regular_id;
	}
	public void setRegular_id(int regular_id) {
		this.regular_id = regular_id;
	}
	
}
