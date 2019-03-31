package acuraulm.ThoughtsFinder.business.transfer;

import java.util.HashSet;
import java.util.Set;

public class AppuserDTO {

	private long id;
	private String username;
	private String password;
	private String name;
	
    private Set<Long> sharedThoughtsIds = new HashSet<>();
    private Set<Long> commentsIds = new HashSet<>();	
    private Set<Long> followedThoughtsIds = new HashSet<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Set<Long> getSharedThoughtsIds() {
		return sharedThoughtsIds;
	}
	public void setSharedThoughtsIds(Set<Long> sharedThoughtsIds) {
		this.sharedThoughtsIds = sharedThoughtsIds;
	}
	public Set<Long> getCommentsIds() {
		return commentsIds;
	}
	public void setCommentsIds(Set<Long> commentsIds) {
		this.commentsIds = commentsIds;
	}
	public Set<Long> getFollowedThoughtsIds() {
		return followedThoughtsIds;
	}
	public void setFollowedThoughtsIds(Set<Long> followedThoughtsIds) {
		this.followedThoughtsIds = followedThoughtsIds;
	}

   

}
