package acuraulm.ThoughtsFinder.business.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acuraulm.ThoughtsFinder.business.serviceInterface.ISectionService;
import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;
import acuraulm.ThoughtsFinder.persistence.entities.Section;
import acuraulm.ThoughtsFinder.persistence.repositories.SectionRepository;

@Service
@Transactional
public class SectionService implements ISectionService {

	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	DataTransferService dataTransferService;
	
	@Override
	public void createSection(SectionDTO sectionDTO) {
		System.out.print("Creating section");	
		try{
			Section section = new Section();
			section.setName(sectionDTO.getName());
			sectionRepository.save(section);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
	}

	@Override
	public void updateSection(long sectionId, SectionDTO sectionDTO) {
		System.out.print("Updating section");	
		try{
			Section section = sectionRepository.getOne(sectionId);
			section.setName(sectionDTO.getName());
			sectionRepository.save(section);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		
	}

	@Override
	public void deleteSectionById(long sectionId) {
		System.out.print("Deleting Section");	
		try{
			sectionRepository.deleteById(sectionId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
	}

	@Override
	public Set<SectionDTO> findAllSections() {
		System.out.print("Finding sections");
		Set<SectionDTO> sectionDTOs = new HashSet<SectionDTO>();
		try{
			for(Section section:sectionRepository.findAll())
				sectionDTOs.add(dataTransferService.getSectionDTO(section));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return sectionDTOs;
	}

	@Override
	public SectionDTO findSectionById(long sectionId) {
		System.out.print("Finding Section by id");
		SectionDTO sectionDTO = new SectionDTO();
		try{
			sectionDTO = dataTransferService.getSectionDTO(sectionRepository.getOne(sectionId));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.print(".....done.\n");
		return sectionDTO;
	}

}
