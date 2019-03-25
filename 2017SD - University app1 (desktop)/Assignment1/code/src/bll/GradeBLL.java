package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.GradeDAO;
import entities.Grade;


public class GradeBLL {

	private List<Validator<Grade>> validators;

	public GradeBLL() {
		validators = new ArrayList<Validator<Grade>>();
		validators.add(new GradeValidator());
	}

	public Grade findGradeById(int id) {
		Grade st = GradeDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The grade with id =" + id + " was not found!");
		}
		return st;
	}
	public Grade findGradeByStudentId(int idStud, int idCourse) throws SQLException{
		Grade st = GradeDAO.findByStudentId(idStud, idCourse);
		if (st == null) {
			System.out.println("The grade with student_id =" + idStud + "or course_id" + idCourse + " was not found!");
		}
		return st;
	}
	public List<Grade> findAllGrades() throws SQLException{
		return GradeDAO.findAll();
	}

	public void deleteGrade(Grade grade) throws SQLException {
		GradeDAO.delete(grade);
	}
	
	public void updateGrade(Grade grade) throws SQLException {
		for (Validator<Grade> v : validators) {
			v.validate(grade);
		}
		GradeDAO.update(grade);
	}
	
	public int insertGrade(Grade grade) {
		for (Validator<Grade> v : validators) {
			v.validate(grade);
		}
		return GradeDAO.insert(grade);
	}
}
