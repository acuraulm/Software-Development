package acuraulm.ThoughtsFinder.business.serviceInterface;

import java.util.Set;

import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;

public interface IThoughtService {

	//void createThought(ThoughtDTO thoughtDTO);
	//void updateThought(ThoughtDTO thoughtDTO);
	//void deleteThoughtById(long thoughtId);
	Set<ThoughtDTO> findAllThoughts();
	ThoughtDTO findThoughtById(long thoughtId);
	Set<ThoughtDTO> findAllThoughtsByAppuserId(long appuserId);
	Set<ThoughtDTO> findAllThoughtsBySectionId(long sectionId);
	Set<ThoughtDTO> findAllThoughtsByContent(String content);
	
	void shareThought(ThoughtDTO thoughtDTO);
	void editThought(long thoughtId, ThoughtDTO thoughtDTO);
	void deleteThought(long thoughtId);
	
	void followThought(long appuserId, long thoughtId);
	void unfollowThought(long appuserId, long thoughtId);
	
	
	
}
