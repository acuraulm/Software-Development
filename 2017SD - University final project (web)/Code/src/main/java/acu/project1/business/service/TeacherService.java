package acu.project1.business.service;

import java.util.List;

import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.TeacherData;

public interface TeacherService {

	void addTeacher(String username, String name);
	void removeTeacher(long id);
	void updateTeacher(long id, String username, String name);
	void addCourseToAdmin(String course, long teacherId);
	List<TeacherData> getAllTeachers();
	TeacherData findOneTeacher(long id);
	
}
