package acuraulm.ThoughtsFinder.business.transfer;

import java.util.HashSet;
import java.util.Set;



public class SectionDTO implements Comparable<SectionDTO> {
	
	private long id;
	private String name;
    private Set<Long> thoughtsIds = new HashSet<>();
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
	public Set<Long> getThoughtsIds() {
		return thoughtsIds;
	}
	public void setThoughtsIds(Set<Long> thoughtsIds) {
		this.thoughtsIds = thoughtsIds;
	}
	@Override
	public int compareTo(SectionDTO arg0) {
	
		return Integer.parseInt("" + this.getId()) - Integer.parseInt("" + arg0.getId());
	}

    
}
