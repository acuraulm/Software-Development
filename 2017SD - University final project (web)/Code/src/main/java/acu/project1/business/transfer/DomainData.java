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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class DomainData {

	private long id;

	private String name;

    private List<SpecializationData> specializations = new ArrayList<SpecializationData>();

    private FacultyData faculty;
	
	public FacultyData getFaculty() {
		return faculty;
	}

	public void setFaculty(FacultyData faculty) {
		this.faculty = faculty;
	}

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

	public List<SpecializationData> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(List<SpecializationData> specializations) {
		this.specializations = specializations;
	}
	
}
