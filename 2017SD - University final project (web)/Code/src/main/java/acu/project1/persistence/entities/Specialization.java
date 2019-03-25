package acu.project1.persistence.entities;

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

@Entity
@Table
public class Specialization {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Column
	private String name;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "specialization", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Groupa> groupas = new ArrayList<>();
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
	public List<Groupa> getGroupas() {
		return groupas;
	}
	public void setGroupas(List<Groupa> groupas) {
		this.groupas = groupas;
	}
	public void addGroupa(Groupa groupa) {
		this.groupas.add(groupa);
		groupa.setSpecialization(this);
	}
	public void removeGroupa(Groupa groupa) {
		this.groupas.remove(groupa);
	}
	
	
}
