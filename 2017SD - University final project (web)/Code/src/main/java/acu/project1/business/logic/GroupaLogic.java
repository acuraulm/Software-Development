package acu.project1.business.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.project1.business.service.GroupaService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.StudentData;
import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Grade;
import acu.project1.persistence.entities.Groupa;
import acu.project1.persistence.entities.Student;
import acu.project1.persistence.entities.Teacher;
import acu.project1.persistence.repositories.GroupaRepository;
import acu.project1.persistence.repositories.StudentRepository;
import acu.project1.persistence.repositories.TeacherRepository;

@Service
public class GroupaLogic implements GroupaService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	GroupaRepository groupaRepository;
	@Autowired
	StudentRepository studentRepository;

	@Override
	public void removeGroupa(long id) {
		groupaRepository.delete(id);
	}


	@Override
	public void addGroupa(String number) {
		Groupa groupa = new Groupa();
		groupa.setNumber(number);
		groupaRepository.save(groupa);
	}


	@Override
	public List<GroupaData> getAllGroupas() {
		List<Groupa> groupas = groupaRepository.findAll();
		List<GroupaData> toReturn = new ArrayList<>();
		for(Groupa crs : groupas) {
			List<String> studentsS = new ArrayList<>();
			for(Student stud : crs.getStudents()) {
				if(!studentsS.contains(stud.getName()))
					studentsS.add(stud.getName());
			}
			toReturn.add(new GroupaData(crs.getId(), crs.getNumber(),crs.getSpecialization().getName(), studentsS));
		}
		return toReturn;
	}


	@Override
	public void addStudentToGroupa(long studentId, long groupaId) {
		Student student = studentRepository.findOne(studentId);
		Groupa grupa = groupaRepository.findOne(groupaId);
		student.setGroupa(grupa);
		grupa.addStudent(student);
		studentRepository.save(student);
		//groupaRepository.save(grupa);
		
	}



	@Override
	public List<StudentData> getAllGroupaStudents(long id) {
		/*Groupa groupa = groupaRepository.findOne(id);
		List<StudentData> students = new ArrayList<>();
		for(Student student : groupa.getStudents())
				students.add(new StudentData(student.getId(), student.getUsername(), student.getName(), null));
		return students;
		*/
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
			if(stud.getGroupa().getId() == id)
			toReturn.add(new StudentData(stud.getId(), stud.getUsername(), stud.getName(), stud.getGroupa().getNumber(), coursesD, gradesD));
		}
		return toReturn;
	}
	



}
