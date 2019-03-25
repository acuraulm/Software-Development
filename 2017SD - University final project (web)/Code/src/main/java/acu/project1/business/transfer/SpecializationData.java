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

import acu.project1.persistence.entities.Groupa;

public class SpecializationData {

    @Override
	public String toString() {
		return "SpecializationData [id=" + id + ", name=" + name + ", groupas=" + groupas + "]";
	}
	private long id;
	private String name;
	private List<String> groupas = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public SpecializationData() {
		super();
	}
	public SpecializationData(long id, String name, List<String> groupas) {
		super();
		this.id = id;
		this.name = name;
		this.groupas = groupas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getGroupas() {
		return groupas;
	}
	public void setGroupas(List<String> groupas) {
		this.groupas = groupas;
	}
	public void addGroupa(GroupaData groupa) {
		this.groupas.add(groupa.getNumber());
		groupa.setSpecialization(this.getName());
	}
	public void removeGroupa(Groupa groupa) {
		this.groupas.remove(groupa.getNumber());
	}
}
