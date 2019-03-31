package acuraulm.ThoughtsFinder.business.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import acuraulm.ThoughtsFinder.business.serviceInterface.IDataTransferService;
import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.CommentDTO;
import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;
import acuraulm.ThoughtsFinder.persistence.entities.Appuser;
import acuraulm.ThoughtsFinder.persistence.entities.Comment;
import acuraulm.ThoughtsFinder.persistence.entities.Section;
import acuraulm.ThoughtsFinder.persistence.entities.Thought;

@Service
public class DataTransferService implements IDataTransferService {

	@Override
	public AppuserDTO getAppuserDTO(Appuser appuser) {
		
		AppuserDTO appuserDTO = new AppuserDTO();
		
		appuserDTO.setId(appuser.getId());
		appuserDTO.setName(appuser.getName());
		appuserDTO.setPassword(appuser.getPassword());
		appuserDTO.setUsername(appuser.getUsername());
		
		Set<Long> sharedThoughtsIds = new HashSet<Long>();
		Set<Long> followedThoughtsIds = new HashSet<Long>();
		Set<Long> commentsIds = new HashSet<Long>();
		
		for(Thought sharedThought:appuser.getSharedThoughts())
			sharedThoughtsIds.add(sharedThought.getId());
		for(Thought followedThought:appuser.getFollowedThoughts())
			followedThoughtsIds.add(followedThought.getId());
		for(Comment comment:appuser.getComments())
			commentsIds.add(comment.getId());
		
		appuserDTO.setSharedThoughtsIds(sharedThoughtsIds);
		appuserDTO.setFollowedThoughtsIds(followedThoughtsIds);
		appuserDTO.setCommentsIds(commentsIds);
		
		
		return appuserDTO;
	}

	@Override
	public ThoughtDTO getThoughtDTO(Thought thought) {
		
		ThoughtDTO thoughtDTO = new ThoughtDTO();
		
		thoughtDTO.setId(thought.getId());
		thoughtDTO.setContent(thought.getContent());
		thoughtDTO.setDate(thought.getDate());
		thoughtDTO.setAppuserId(thought.getAppuser().getId());
		thoughtDTO.setAppuserName(thought.getAppuser().getName());
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		thoughtDTO.setDateString(df.format(thought.getDate()));
		Set<Long> commentsIds = new HashSet<Long>();
		Set<Long> followersIds = new HashSet<Long>();
		Set<Long> sectionsIds = new HashSet<Long>();
		
		for(Comment comment:thought.getComments())
			commentsIds.add(comment.getId());
		for(Appuser follower:thought.getFollowers())
			followersIds.add(follower.getId());
		for(Section section:thought.getSections())
			sectionsIds.add(section.getId());
			
		thoughtDTO.setCommentsIds(commentsIds);
		thoughtDTO.setFollowersIds(followersIds);
		thoughtDTO.setSectionsIds(sectionsIds);
		
		
		return thoughtDTO;
	}

	@Override
	public CommentDTO getCommentDTO(Comment comment) {
		
		CommentDTO commentDTO = new CommentDTO();
		
		commentDTO.setId(comment.getId());
		commentDTO.setContent(comment.getContent());
		commentDTO.setDate(comment.getDate());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		commentDTO.setDateString(df.format(commentDTO.getDate()));
		commentDTO.setAppuserId(comment.getAppuser().getId());
		commentDTO.setAppuserName(comment.getAppuser().getName());
		return commentDTO;
	}

	@Override
	public SectionDTO getSectionDTO(Section section) {
		
		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setId(section.getId());
		sectionDTO.setName(section.getName());
		
		Set<Long> thoughtsIds = new HashSet<Long>();
		for(Thought thought:section.getThoughts())
			thoughtsIds.add(thought.getId());
		
		sectionDTO.setThoughtsIds(thoughtsIds);
		
		return sectionDTO;
	}


}
