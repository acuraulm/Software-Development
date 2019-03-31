package acuraulm.ThoughtsFinder.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="section")
public class Section {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "sections")
    private Set<Thought> thoughts = new HashSet<>();

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Thought> getThoughts() {
		return thoughts;
	}

	public void setThoughts(Set<Thought> thoughts) {
		this.thoughts = thoughts;
	}
	
	
}
