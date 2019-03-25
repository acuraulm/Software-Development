package acu.assignment2.business.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.assignment2.business.service.AdministratorService;
import acu.assignment2.business.service.StudentService;
import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.business.transfer.StudentData;
import acu.assignment2.persistence.entities.*;

import acu.assignment2.persistence.repository.AdministratorRepository;
import acu.assignment2.persistence.repository.CourseRepository;
import acu.assignment2.persistence.repository.StudentRepository;

@Service
public class StudentLogic implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;	


	@Override
	public void addStudent(String username, String name) {
		Student student = new Student();
		student.setUsername(username);
		student.setName(name);
		studentRepository.save(student);
	}

	@Override
	public void removeStudent(long id) {
		studentRepository.delete(id);
		
	}

	@Override
	public void updateStudent(long id, String username, String name) {
		Student stud = studentRepository.findOne(id);
		stud.setName(name);
		stud.setUsername(username);
		studentRepository.save(stud);
		
	}

	@Override
	public List<StudentData> getAllStudents() {
		List <Student> students = studentRepository.findAll();
		List <StudentData> toReturn = new ArrayList<>();
		for(Student stud : students) {
			Set<Course> courses = stud.getCourses();
			List<CourseData> coursesD = new ArrayList<>();
			for(Course curs: courses) {
				List<String> studentsS = new ArrayList<>();
				for(Student student : curs.getStudents()) {
					studentsS.add(student.getName());
				}
				coursesD.add(new CourseData(curs.getId(), curs.getName(), curs.getAdministrator().getName(), studentsS));
			}
			toReturn.add(new StudentData(stud.getId(), stud.getUsername(), stud.getName(), coursesD));
		}
		return toReturn;
	}

	@Override
	public StudentData findOneStudent(long id) {
		Student student = studentRepository.findOne(id);
		Set<Course> courses = student.getCourses();
		List<CourseData> coursesD = new ArrayList<>();
		for(Course crs : courses) {
			List<String> studentsS = new ArrayList<>();
			for(Student stud : crs.getStudents()) {
				studentsS.add(stud.getName());
			}
			coursesD.add(new CourseData(crs.getId(),crs.getName(), crs.getAdministrator().getName(), studentsS));
		}
		StudentData toReturn = new StudentData(student.getId(), student.getUsername(), student.getName(), coursesD);
		return toReturn;
	}

}
