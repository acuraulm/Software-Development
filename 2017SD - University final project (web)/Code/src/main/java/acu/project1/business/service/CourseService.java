package acu.project1.business.service;

import java.util.List;

import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.TeacherData;

public interface CourseService {

	void removeCourse(long id);
	void addCourse(String name);
	List <CourseData> getAllCourses();
	void addStudentToCourse(long studentId, long courseId);
	void removeStudentFromCourse(long studentId, long courseId);
	List<CourseData> getAllTeacherCourses(long id);
	List<StudentData> getAllCourseStudents(long id);
}
