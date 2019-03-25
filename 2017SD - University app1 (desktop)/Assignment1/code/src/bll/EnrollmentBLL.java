package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.EnrollmentDAO;
import entities.Enrollment;


public class EnrollmentBLL {

	private List<Validator<Enrollment>> validators;

	public EnrollmentBLL() {
		validators = new ArrayList<Validator<Enrollment>>();
	//	validators.add(new EmailValidatorEnrollment());
	//	validators.add(new EnrollmentAgeValidator());
	}

	public Enrollment findEnrollmentById(int id) {
		Enrollment st = EnrollmentDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The enrollment with id =" + id + " was not found!");
		}
		return st;
	}
	public List<Enrollment> findAllEnrollments() throws SQLException{
		return EnrollmentDAO.findAll();
	}

	public void deleteEnrollment(Enrollment enrollment) throws SQLException {
		EnrollmentDAO.delete(enrollment);
	}
	
	public void updateEnrollment(Enrollment enrollment) throws SQLException {
		for (Validator<Enrollment> v : validators) {
			v.validate(enrollment);
		}
	//	EnrollmentDAO.update(enrollment);
	}
	
	public int insertEnrollment(Enrollment enrollment) {
		for (Validator<Enrollment> v : validators) {
			v.validate(enrollment);
		}
		return EnrollmentDAO.insert(enrollment);
	}
}
