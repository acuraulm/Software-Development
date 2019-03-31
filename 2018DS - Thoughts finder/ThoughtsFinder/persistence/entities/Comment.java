package acuraulm.ThoughtsFinder.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String content;
	@Column
	private Date date;
	
	@ManyToOne
    @JoinColumn(name="appuser_id", nullable=false)
    private Appuser appuser = new Appuser();
	
	@ManyToOne
    @JoinColumn(name="thought_id", nullable=false)
    private Thought thought = new Thought();

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

	public Thought getThought() {
		return thought;
	}

	public void setThought(Thought thought) {
		this.thought = thought;
	}
	
	
}
