package acu.assignment2.business.service;

import java.util.List;

import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.business.transfer.StudentData;

public interface CourseService {

	void removeCourse(long id);
	void addCourse(String name);
	List <CourseData> getAllCourses();
	void addStudentToCourse(long studentId, long courseId);
	List<CourseData> getAllAdministratorCourses(long id);
	List<StudentData> getAllCourseStudents(long id);
}
