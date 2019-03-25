package acu.project1.business.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acu.project1.business.service.GradeService;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.StudentData;
import acu.project1.persistence.entities.Grade;
import acu.project1.persistence.entities.Student;
import acu.project1.persistence.entities.Teacher;
import acu.project1.persistence.repositories.GradeRepository;
import acu.project1.persistence.repositories.StudentRepository;
import acu.project1.persistence.repositories.TeacherRepository;

@Service
public class GradeLogic implements GradeService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	GradeRepository gradeRepository;
	@Autowired
	StudentRepository studentRepository;

	@Override
	public void removeGrade(long id) {
		gradeRepository.delete(id);	}


	@Override
	public List<GradeData> getAllGrades() {
		List<Grade> grades = gradeRepository.findAll();
		List<GradeData> toReturn = new ArrayList<>();
		for(Grade nota: grades)
			toReturn.add(new GradeData(nota.getId(), nota.getValue(), nota.getDate(), nota.getCourse_id(), nota.getStudent().getName()));
		return toReturn;
	}


	@Override
	public void addStudentToGrade(long studentId, long gradeId) {
		Student student = studentRepository.findOne(studentId);
		Grade nota = gradeRepository.findOne(gradeId);
		student.addGrade(nota);
		nota.setStudent(student);
		studentRepository.save(student);
		//gradeRepository.save(nota);
		
	}



}
