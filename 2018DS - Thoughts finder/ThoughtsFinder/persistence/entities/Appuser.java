 package acuraulm.ThoughtsFinder.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="appuser")
public class Appuser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String name;
	
	
	@OneToMany(mappedBy="appuser")
    private Set<Thought> sharedThoughts = new HashSet<>();
	
	@OneToMany(mappedBy="appuser")
    private Set<Comment> comments = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Appuser_Thought", 
        joinColumns = { @JoinColumn(name = "appuser_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "thought_id") }
    )
    private Set<Thought> followedThoughts = new HashSet<>();

	
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Thought> getSharedThoughts() {
		return sharedThoughts;
	}

	public void setSharedThoughts(Set<Thought> sharedThoughts) {
		this.sharedThoughts = sharedThoughts;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Thought> getFollowedThoughts() {
		return followedThoughts;
	}

	public void setFollowedThoughts(Set<Thought> followedThoughts) {
		this.followedThoughts = followedThoughts;
	}
	
	
}
