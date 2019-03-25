package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.StudentProfileDAO;
import entities.StudentProfile;


public class StudentProfileBLL {

	private List<Validator<StudentProfile>> validators;

	public StudentProfileBLL() {
		validators = new ArrayList<Validator<StudentProfile>>();
	//	validators.add(new EmailValidatorStudentProfile());
	//	validators.add(new StudentProfileAgeValidator());
	}

	public StudentProfile findStudentProfileById(int id) {
		StudentProfile st = StudentProfileDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The studentProfile with id =" + id + " was not found!");
		}
		return st;
	}
	public List<StudentProfile> findAllStudentProfiles() throws SQLException{
		return StudentProfileDAO.findAll();
	}

	public void deleteStudentProfile(StudentProfile studentProfile) throws SQLException {
		StudentProfileDAO.delete(studentProfile);
	}
	
	public void updateStudentProfile(StudentProfile studentProfile) throws SQLException {
		for (Validator<StudentProfile> v : validators) {
			v.validate(studentProfile);
		}
		StudentProfileDAO.update(studentProfile);
	}
	
	public int insertStudentProfile(StudentProfile studentProfile) {
		for (Validator<StudentProfile> v : validators) {
			v.validate(studentProfile);
		}
		return StudentProfileDAO.insert(studentProfile);
	}
}
