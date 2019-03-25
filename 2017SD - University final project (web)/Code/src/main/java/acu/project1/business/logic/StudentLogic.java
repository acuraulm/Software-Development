package acu.project1.business.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.project1.business.service.StudentService;
import acu.project1.business.service.TeacherService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.TeacherData;
import acu.project1.persistence.entities.*;
import acu.project1.persistence.repositories.CourseRepository;
import acu.project1.persistence.repositories.StudentRepository;
import acu.project1.persistence.repositories.TeacherRepository;

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
				coursesD.add(new CourseData(curs.getId(), curs.getName(), curs.getTeacher().getName(), studentsS));
			}
			List<Grade> grades = stud.getGrades();
			List<GradeData> gradesD = new ArrayList<>();
			for(Grade nota: grades) {
				gradesD.add(new GradeData(nota.getId(),nota.getValue(),nota.getDate(),nota.getCourse_id(),nota.getStudent().getName()));
			}
			if(stud.getGroupa() != null)
				toReturn.add(new StudentData(stud.getId(), stud.getUsername(), stud.getName(), stud.getGroupa().getNumber(), coursesD, gradesD));
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
			coursesD.add(new CourseData(crs.getId(),crs.getName(), crs.getTeacher().getName(), studentsS));
		}
		List<Grade> grades = student.getGrades();
		List<GradeData> gradesD = new ArrayList<>();
		for(Grade nota: grades) {
			gradesD.add(new GradeData(nota.getId(),nota.getValue(),nota.getDate(),nota.getCourse_id(),nota.getStudent().getName()));
		}
		StudentData toReturn = new StudentData(student.getId(), student.getUsername(), student.getName(),student.getGroupa().getNumber(), coursesD, gradesD);
		return toReturn;
	}

}
