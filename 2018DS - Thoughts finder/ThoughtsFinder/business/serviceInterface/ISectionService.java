package acuraulm.ThoughtsFinder.business.serviceInterface;

import java.util.Set;

import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;

public interface ISectionService {

	void createSection(SectionDTO sectionDTO);
	void updateSection(long sectionId, SectionDTO sectionDTO);
	void deleteSectionById(long sectionId);
	Set<SectionDTO> findAllSections();
	SectionDTO findSectionById(long sectionId);
	
}
