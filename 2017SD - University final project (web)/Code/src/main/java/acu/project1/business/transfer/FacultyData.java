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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class FacultyData {

	private long id;
	private String name;

    private UniversityData university;
	
    private List<DepartmentData> departments = new ArrayList<DepartmentData>();
	
    private List<DomainData> domains = new ArrayList<DomainData>();

	private String profile;
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
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

	public UniversityData getUniversity() {
		return university;
	}

	public void setUniversity(UniversityData university) {
		this.university = university;
	}

	public List<DepartmentData> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentData> departments) {
		this.departments = departments;
	}

	public List<DomainData> getDomains() {
		return domains;
	}

	public void setDomains(List<DomainData> domains) {
		this.domains = domains;
	}
	
	
}
