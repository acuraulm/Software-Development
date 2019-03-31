package acuraulm.ThoughtsFinder.persistence.entities;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="thought")
public class Thought {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String content;
	@Column
	private Date date;
	
	@ManyToOne
    @JoinColumn(name="appuser_id", nullable=true)
    private Appuser appuser = new Appuser();
	
	@OneToMany(mappedBy="thought")
    private Set<Comment> comments = new HashSet<>();
	
	@ManyToMany(mappedBy = "followedThoughts")
    private Set<Appuser> followers = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Thought_Section", 
        joinColumns = { @JoinColumn(name = "thought_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "section_id") }
    )
    private Set<Section> sections = new HashSet<>();

	
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Appuser getAppuser() {
		return appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Appuser> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Appuser> followers) {
		this.followers = followers;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

		

}
