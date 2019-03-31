package acuraulm.ThoughtsFinder.business.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acuraulm.ThoughtsFinder.business.serviceInterface.IThoughtService;
import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;
import acuraulm.ThoughtsFinder.persistence.entities.Appuser;
import acuraulm.ThoughtsFinder.persistence.entities.Section;
import acuraulm.ThoughtsFinder.persistence.entities.Thought;
import acuraulm.ThoughtsFinder.persistence.repositories.AppuserRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.SectionRepository;
import acuraulm.ThoughtsFinder.persistence.repositories.ThoughtRepository;

@Service
@Transactional
public class ThoughtService implements IThoughtService {
	
	@Autowired
	ThoughtRepository thoughtRepository;
	@Autowired
	AppuserRepository appuserRepository;
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	DataTransferService dataTransferService;
	
	@Override
	public ThoughtDTO findThoughtById(long thoughtId) {
		System.out.print("Finding Thought by id");
		ThoughtDTO thoughtDTO = new ThoughtDTO();
		try{
			thoughtDTO = dataTransferService.getThoughtDTO(thoughtRepository.getOne(thoughtId));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return thoughtDTO;
	}

	@Override
	public Set<ThoughtDTO> findAllThoughtsByAppuserId(long appuserId) {
		System.out.print("Finding thoughts by appuserId");
		Set<ThoughtDTO> thoughtsDTOs = new HashSet<ThoughtDTO>();
		try{	
			for(Thought thought:thoughtRepository.findAll())
				if(thought.getAppuser().getId() == appuserId)
					thoughtsDTOs.add(dataTransferService.getThoughtDTO(thought));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return thoughtsDTOs;
	}

	@Override
	public void shareThought(ThoughtDTO thoughtDTO) {
		System.out.print("Sharing thought");	
		try{
			Appuser appuser = appuserRepository.getOne(thoughtDTO.getAppuserId());
			Thought thought = new Thought();
			thought.setAppuser(appuser);
			thought.setDate(thoughtDTO.getDate());
			thought.setContent(thoughtDTO.getContent());
			
			Set<Section> sections = new HashSet<Section>();
			for(long sectionId:thoughtDTO.getSectionsIds())
				for(Section section:sectionRepository.findAll())
					if(section.getId() == sectionId) {
						sections.add(section);
						section.getThoughts().add(thought);
					}
			thought.setSections(sections);
			appuser.getSharedThoughts().add(thought);
			
			thoughtRepository.save(thought);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		
	}

	@Override
	public void editThought(long thoughtId, ThoughtDTO thoughtDTO) {
		System.out.print("Editing thought");	
		try{
			Thought thought = thoughtRepository.getOne(thoughtId);
			thought.setDate(thoughtDTO.getDate());
			thought.setContent(thoughtDTO.getContent());
			
			thoughtRepository.save(thought);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
	}

	@Override
	public void deleteThought(long thoughtId) {
		System.out.print("Deleting thought");	
		try{
			thoughtRepository.deleteById(thoughtId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
	}

	@Override
	public void followThought(long appuserId, long thoughtId) {
		System.out.print("Following	 thought");	
		try{
			Appuser appuser = appuserRepository.getOne(appuserId);
			Thought thought = thoughtRepository.getOne(thoughtId);
			
			appuser.getFollowedThoughts().add(thought);
			thought.getFollowers().add(appuser);
			
			thoughtRepository.save(thought);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		
	}

	@Override
	public void unfollowThought(long appuserId, long thoughtId) {
		System.out.print("Unfollowing thought");	
		try{
			Appuser appuser = appuserRepository.getOne(appuserId);
			Thought thought = thoughtRepository.getOne(thoughtId);
			
			appuser.getFollowedThoughts().remove(thought);
			thought.getFollowers().remove(appuser);
			
			thoughtRepository.save(thought);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		
	}

	@Override
	public Set<ThoughtDTO> findAllThoughts() {
		System.out.print("Finding thoughts");
		Set<ThoughtDTO> thoughtDTOs = new HashSet<ThoughtDTO>();
		try{
			for(Thought thought:thoughtRepository.findAll())
				thoughtDTOs.add(dataTransferService.getThoughtDTO(thought));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return thoughtDTOs;
	}
	
	@Override
	public Set<ThoughtDTO> findAllThoughtsBySectionId(long sectionId) {
		System.out.print("Finding thoughts by appuserId");
		Set<ThoughtDTO> thoughtsDTOs = new HashSet<ThoughtDTO>();
		try{	
			for(Thought thought:thoughtRepository.findAll())
				for(Section section:thought.getSections())
					if(section.getId() == sectionId)
						thoughtsDTOs.add(dataTransferService.getThoughtDTO(thought));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return thoughtsDTOs;
	}

	@Override
	public Set<ThoughtDTO> findAllThoughtsByContent(String content) {
		System.out.print("Finding thoughts by content");
		Set<ThoughtDTO> thoughtsDTOs = new HashSet<ThoughtDTO>();
		try{	
			for(Thought thought:thoughtRepository.findAll())
				if(thought.getContent().contains(content))
						thoughtsDTOs.add(dataTransferService.getThoughtDTO(thought));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return thoughtsDTOs;
	}


}
