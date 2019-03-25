package acu.project1.business.service;

import java.util.List;

import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.StudentData;

public interface GradeService {

	void removeGrade(long id);
	List <GradeData> getAllGrades();
	void addStudentToGrade(long studentId, long gradeId);
}
