package acu.project1.business.service;

import java.util.List;

import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.StudentData;

public interface GroupaService {

	void removeGroupa(long id);
	void addGroupa(String number);
	List <GroupaData> getAllGroupas();
	void addStudentToGroupa(long studentId, long groupaId);
	List<StudentData> getAllGroupaStudents(long id);
}
