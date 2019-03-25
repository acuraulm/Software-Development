package acu.assignment2.business.service;

import java.util.List;

import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;

public interface AdministratorService {

	void addAdministrator(String username, String name);
	void removeAdministrator(long id);
	void updateAdministrator(long id, String username, String name);
	void addCourseToAdmin(String course, long administratorId);
	List<AdministratorData> getAllAdministrators();
	AdministratorData findOneAdministrator(long id);
	
}
