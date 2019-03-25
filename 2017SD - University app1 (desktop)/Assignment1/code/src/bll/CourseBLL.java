package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.*;
import dal.CourseDAO;
import entities.Course;

public class CourseBLL {

	private List<Validator<Course>> validators;

	public CourseBLL() {
		validators = new ArrayList<Validator<Course>>();
	//	validators.add(new EmailValidatorCourse());
	//	validators.add(new CourseAgeValidator());
	}

	public Course findCourseById(int id) {
		Course st = CourseDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The course with id =" + id + " was not found!");
		}
		return st;
	}
	public Course findCourseByName(String name) {
		Course st = CourseDAO.findByName(name);
		if (st == null) {
			throw new NoSuchElementException("The course with id =" + name + " was not found!");
		}
		return st;
	}
	public List<Course> findAllCourses() throws SQLException{
		return CourseDAO.findAll();
	}

	public void deleteCourse(Course course) throws SQLException {
		CourseDAO.delete(course);
	}
	
	public void updateCourse(Course course) throws SQLException {
		for (Validator<Course> v : validators) {
			v.validate(course);
		}
		CourseDAO.update(course);
	}
	
	public int insertCourse(Course course) {
		for (Validator<Course> v : validators) {
			v.validate(course);
		}
		return CourseDAO.insert(course);
	}
}
