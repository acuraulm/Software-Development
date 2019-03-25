package acu.assignment2.business.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.assignment2.business.service.AdministratorService;
import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.business.transfer.StudentData;
import acu.assignment2.persistence.entities.*;

import acu.assignment2.persistence.repository.AdministratorRepository;
import acu.assignment2.persistence.repository.CourseRepository;

@Service
public class AdministratorLogic implements AdministratorService {
	@Autowired
	AdministratorRepository administratorRepository;
	@Autowired
	CourseRepository courseRepository;
/*	
	@Autowired
	public AdministratorLogic(AdministratorRepository administratorRepository, CourseRepository courseRepository) {
		this.administratorRepository = administratorRepository;
		this.courseRepository = courseRepository;
	}
*/
	@Override
	public void addAdministrator(String username, String name) {
		Administrator admin = new Administrator();
		admin.setName(name);
		admin.setUsername(username);
		administratorRepository.save(admin);
	}

	@Override
	public void removeAdministrator(long id) {
		administratorRepository.delete(id);
		
	}

	@Override
	public void updateAdministrator(long id, String username, String name) {
		Administrator admin = administratorRepository.findOne(id);
		admin.setName(name);
		admin.setUsername(username);
		administratorRepository.save(admin);
	}

	@Override
	public void addCourseToAdmin(String course, long administratorId) {
		Administrator admin = administratorRepository.findOne(administratorId);
		if(courseRepository.findByName(course) != null) {
			Course curs = courseRepository.findByName(course);
			curs.setAdministrator(admin);
			admin.addCourse(curs);
			courseRepository.save(curs);
			//administratorRepository.save(admin);
		}
		else {
			Course courseN = new Course();
			courseN.setName(course);
			courseN.setAdministrator(admin);
			admin.addCourse(courseN);
			courseRepository.save(courseN);
			//administratorRepository.save(admin);
		}
	}

	@Override
	public List<AdministratorData> getAllAdministrators() {
		List<AdministratorData> toReturn = new ArrayList<>();
		
		for(Administrator admin : administratorRepository.findAll()) {
			List<CourseData> courses = new ArrayList<>();
			for(Course course : admin.getCourses()) {

				courses.add(new CourseData(course.getId(), course.getName(), course.getAdministrator().getName(), null));
			}	
			toReturn.add(new AdministratorData(admin.getId(), admin.getUsername(), admin.getName(), courses));
		}
		return toReturn;
	}

	@Override
	public AdministratorData findOneAdministrator(long id) {
		Administrator admin = administratorRepository.findOne(id);
		List<CourseData> courses = new ArrayList<>();
		for(Course course : admin.getCourses())
			courses.add(new CourseData(course.getId(), course.getName(), course.getAdministrator().getName(), null));
		return new AdministratorData(admin.getId(), admin.getUsername(), admin.getName(), courses);
	}
	
	

}
