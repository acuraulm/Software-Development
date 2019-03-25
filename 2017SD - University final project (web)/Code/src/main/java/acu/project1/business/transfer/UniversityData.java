package acu.project1.business.transfer;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UniversityData {

	private long id;
	private String name;
	
    private List<FacultyData> faculties = new ArrayList<FacultyData>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FacultyData> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<FacultyData> faculties) {
		this.faculties = faculties;
	}

	
}
