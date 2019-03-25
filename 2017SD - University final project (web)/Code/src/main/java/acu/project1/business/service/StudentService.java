package acu.project1.business.service;
import java.util.List;

import acu.project1.business.transfer.StudentData;
public interface StudentService {

	void addStudent(String username, String name);
	void removeStudent(long id);
	List <StudentData> getAllStudents();
	StudentData findOneStudent(long id);
	void updateStudent(long id, String username, String name);
}
