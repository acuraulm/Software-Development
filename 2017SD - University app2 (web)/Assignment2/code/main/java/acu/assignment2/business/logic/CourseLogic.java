package acu.assignment2.business.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.assignment2.business.service.CourseService;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.business.transfer.StudentData;
import acu.assignment2.persistence.entities.Administrator;
import acu.assignment2.persistence.entities.Course;
import acu.assignment2.persistence.entities.Student;
import acu.assignment2.persistence.repository.AdministratorRepository;
import acu.assignment2.persistence.repository.CourseRepository;
import acu.assignment2.persistence.repository.StudentRepository;

@Service
public class CourseLogic implements CourseService {
	@Autowired
	AdministratorRepository administratorRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;

	@Override
	public void removeCourse(long id) {
		courseRepository.delete(id);
		administratorRepository.findOne(courseRepository.findOne(id).getAdministrator().getId()).removeCourse(courseRepository.findOne(id));
	}


	@Override
	public void addCourse(String name) {
		Administrator admin = administratorRepository.findOne((long) 1);
		Course course = new Course();
		course.setName(name);
		course.setAdministrator(admin);
		admin.addCourse(course);
		courseRepository.save(course);
		administratorRepository.save(admin);
	}


	@Override
	public List<CourseData> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		List<CourseData> toReturn = new ArrayList<>();
		for(Course crs : courses) {
			List<String> studentsS = new ArrayList<>();
			for(Student stud : crs.getStudents()) {
				studentsS.add(stud.getName());
			}
			toReturn.add(new CourseData(crs.getId(), crs.getName(), crs.getAdministrator().getName(), studentsS));
		}
		return toReturn;
	}


	@Override
	public void addStudentToCourse(long studentId, long courseId) {
		Student student = studentRepository.findOne(studentId);
		Course curs = courseRepository.findOne(courseId);
		student.addCourse(curs);
		curs.addStudent(student);
		studentRepository.save(student);
		//courseRepository.save(curs);
		
	}


	@Override
	public List<CourseData> getAllAdministratorCourses(long id) {
		List<Course> courses = courseRepository.findAll();
		List<CourseData> toReturn = new ArrayList<>();
		for(Course crs : courses) {
			List<String> studentsS = new ArrayList<>();
			for(Student stud : crs.getStudents()) {
				studentsS.add(stud.getName());
			}
			if(crs.getAdministrator().getId() == id)
			toReturn.add(new CourseData(crs.getId(), crs.getName(), crs.getAdministrator().getName(), studentsS));
		}
		return toReturn;
	}


	@Override
	public List<StudentData> getAllCourseStudents(long id) {
		Course course = courseRepository.findOne(id);
		List<StudentData> students = new ArrayList<>();
		for(Student student : course.getStudents())
				students.add(new StudentData(student.getId(), student.getUsername(), student.getName(), null));
		return students;
	}
	



}
