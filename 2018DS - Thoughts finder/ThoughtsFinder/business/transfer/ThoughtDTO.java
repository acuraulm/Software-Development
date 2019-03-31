package acuraulm.ThoughtsFinder.business.transfer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class ThoughtDTO {

	private long id;
	private String content;
	
	private Date date;
	private String dateString;
	
    private Long appuserId;
    private String appuserName;
    
    private Set<Long> commentsIds = new HashSet<>();	
    private Set<Long> followersIds = new HashSet<>();	
    private Set<Long> sectionsIds = new HashSet<>();
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAppuserName() {
		return appuserName;
	}
	public void setAppuserName(String appuserName) {
		this.appuserName = appuserName;
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
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Long getAppuserId() {
		return appuserId;
	}
	public void setAppuserId(Long appuserId) {
		this.appuserId = appuserId;
	}
	public Set<Long> getCommentsIds() {
		return commentsIds;
	}
	public void setCommentsIds(Set<Long> commentsIds) {
		this.commentsIds = commentsIds;
	}
	public Set<Long> getFollowersIds() {
		return followersIds;
	}
	public void setFollowersIds(Set<Long> followersIds) {
		this.followersIds = followersIds;
	}
	public Set<Long> getSectionsIds() {
		return sectionsIds;
	}
	public void setSectionsIds(Set<Long> sectionsIds) {
		this.sectionsIds = sectionsIds;
	}
    
}
