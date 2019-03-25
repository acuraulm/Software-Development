package acu.project1.business.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.project1.business.service.DomainService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.StudentData;
import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Student;
import acu.project1.persistence.entities.Teacher;
import acu.project1.persistence.repositories.CourseRepository;
import acu.project1.persistence.repositories.StudentRepository;
import acu.project1.persistence.repositories.TeacherRepository;

@Service
public class DomainLogic implements DomainService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;

	@Override
	public void removeCourse(long id) {
		courseRepository.delete(id);
		teacherRepository.findOne(courseRepository.findOne(id).getTeacher().getId()).removeCourse(courseRepository.findOne(id));
	}


	@Override
	public void addCourse(String name) {
		Teacher admin = teacherRepository.findOne((long) 1);
		Course course = new Course();
		course.setName(name);
		course.setTeacher(admin);
		admin.addCourse(course);
		courseRepository.save(course);
		teacherRepository.save(admin);
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
			toReturn.add(new CourseData(crs.getId(), crs.getName(), crs.getTeacher().getName(), studentsS));
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
	public List<CourseData> getAllTeacherCourses(long id) {
		List<Course> courses = courseRepository.findAll();
		List<CourseData> toReturn = new ArrayList<>();
		for(Course crs : courses) {
			List<String> studentsS = new ArrayList<>();
			for(Student stud : crs.getStudents()) {
				studentsS.add(stud.getName());
			}
			if(crs.getTeacher().getId() == id)
			toReturn.add(new CourseData(crs.getId(), crs.getName(), crs.getTeacher().getName(), studentsS));
		}
		return toReturn;
	}


	@Override
	public List<StudentData> getAllCourseStudents(long id) {
		Course course = courseRepository.findOne(id);
		List<StudentData> students = new ArrayList<>();
		for(Student student : course.getStudents())
				students.add(new StudentData(student.getId(), student.getUsername(), student.getName(), null, null, null));
		return students;
	}


	@Override
	public void removeStudentFromCourse(long studentId, long courseId) {
		Student student = studentRepository.findOne(studentId);
		Course curs = courseRepository.findOne(courseId);
		student.removeCourse(curs);
		curs.removeStudent(student);
		studentRepository.save(student);
		
	}
	



}
