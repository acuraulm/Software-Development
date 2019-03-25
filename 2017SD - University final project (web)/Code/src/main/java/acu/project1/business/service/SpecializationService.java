package acu.project1.business.service;

import java.util.List;

import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.SpecializationData;

public interface SpecializationService {

	void removeSpecialization(long id);
	void addSpecialization(String number);
	List <SpecializationData> getAllSpecializations();
	void addGroupaToSpecialization(long groupaId, long specializationId);
	List<GroupaData> getAllSpecializationGroupas(long id);
}
