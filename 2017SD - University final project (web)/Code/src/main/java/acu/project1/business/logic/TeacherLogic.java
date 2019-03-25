package acu.project1.business.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.project1.business.service.TeacherService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.TeacherData;
import acu.project1.persistence.entities.*;
import acu.project1.persistence.repositories.CourseRepository;
import acu.project1.persistence.repositories.TeacherRepository;

@Service
public class TeacherLogic implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	CourseRepository courseRepository;

	@Override
	public void addTeacher(String username, String name) {
		Teacher admin = new Teacher();
		admin.setName(name);
		admin.setUsername(username);
		teacherRepository.save(admin);
	}

	@Override
	public void removeTeacher(long id) {
		teacherRepository.delete(id);
		
	}

	@Override
	public void updateTeacher(long id, String username, String name) {
		Teacher admin = teacherRepository.findOne(id);
		admin.setName(name);
		admin.setUsername(username);
		teacherRepository.save(admin);
	}

	@Override
	public void addCourseToAdmin(String course, long teacherId) {
		Teacher admin = teacherRepository.findOne(teacherId);
		if(courseRepository.findByName(course) != null) {
			Course curs = courseRepository.findByName(course);
			curs.setTeacher(admin);
			admin.addCourse(curs);
			courseRepository.save(curs);
			//teacherRepository.save(admin);
		}
		else {
			Course courseN = new Course();
			courseN.setName(course);
			courseN.setTeacher(admin);
			admin.addCourse(courseN);
			courseRepository.save(courseN);
			//teacherRepository.save(admin);
		}
	}

	@Override
	public List<TeacherData> getAllTeachers() {
		List<TeacherData> toReturn = new ArrayList<>();
		
		for(Teacher admin : teacherRepository.findAll()) {
			List<CourseData> courses = new ArrayList<>();
			for(Course course : admin.getCourses()) {

				courses.add(new CourseData(course.getId(), course.getName(), course.getTeacher().getName(), null));
			}	
			toReturn.add(new TeacherData(admin.getId(), admin.getUsername(), admin.getName(), courses));
		}
		return toReturn;
	}

	@Override
	public TeacherData findOneTeacher(long id) {
		Teacher admin = teacherRepository.findOne(id);
		List<CourseData> courses = new ArrayList<>();
		for(Course course : admin.getCourses())
			courses.add(new CourseData(course.getId(), course.getName(), course.getTeacher().getName(), null));
		return new TeacherData(admin.getId(), admin.getUsername(), admin.getName(), courses);
	}
	
	

}
