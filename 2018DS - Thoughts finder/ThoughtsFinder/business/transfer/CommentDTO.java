package acuraulm.ThoughtsFinder.business.transfer;

import java.util.Date;



public class CommentDTO {

	private long id;
	private String content;
	private Date date;
	private String dateString;
	
    private Long appuserId;
    private String appuserName;
    
    private Long thoughtId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Long getAppuserId() {
		return appuserId;
	}
	public void setAppuserId(Long appuserId) {
		this.appuserId = appuserId;
	}
	public Long getThoughtId() {
		return thoughtId;
	}
	public void setThoughtId(Long thoughtId) {
		this.thoughtId = thoughtId;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getAppuserName() {
		return appuserName;
	}
	public void setAppuserName(String appuserName) {
		this.appuserName = appuserName;
	}

    
    

}
