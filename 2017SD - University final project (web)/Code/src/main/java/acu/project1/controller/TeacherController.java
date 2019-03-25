package acu.project1.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import acu.project1.business.service.CourseService;
import acu.project1.business.service.TeacherService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.TeacherData;
import acu.project1.controller.StudentController.Returneaza;
import acu.project1.persistence.entities.Student;
import acu.project1.persistence.entities.Teacher;
import acu.project1.persistence.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
@Api(
        name = "Teacher API",
        description = "Provides a list of teachers",
        stage = ApiStage.RC)
public class TeacherController {
	@Autowired
     TeacherService teacherService;
	@Autowired
	 CourseService courseService;
	
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel teachers from the database")
    public List<CourseData> getAll(){
        return courseService.getAllTeacherCourses(7);
    }
    
    public class Returneaza2{
    	StudentData student;
    	int grade;
    	
    	public Returneaza2(StudentData student, int grade) {
    		this.student = student;
    		this.grade = grade;
    	}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + grade;
			result = prime * result + ((student == null) ? 0 : student.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Returneaza2 other = (Returneaza2) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (grade != other.grade)
				return false;
			if (student == null) {
				if (other.student != null)
					return false;
			} else if (!student.equals(other.student))
				return false;
			return true;
		}

		private TeacherController getOuterType() {
			return TeacherController.this;
		}
    	
    	
    }
    
    @RequestMapping(value = "/all/{courseid}", method = RequestMethod.GET)
    @ApiMethod(description = "Get all students of a course")
    public List<StudentData> getAllStudents(@ApiPathParam(name = "courseid") @PathVariable long courseid){
    	List<StudentData> studentsOf = courseService.getAllCourseStudents(courseid);
    	List<Returneaza2> returneaza = new ArrayList<>();
    	for(StudentData student : studentsOf) {
    		System.out.println(student.toString());
    			returneaza.add(new Returneaza2(student,0));
    		/*for(Returneaza2 returne : returneaza) {
    		if(student.getGrades()!=null)
    		for(GradeData grade : student.getGrades()) {
    			if(grade.getCourse_id() == courseid) {
    				returne.grade = grade.getValue();
    			}
    		}
    		}
    		*/
    	}
    	return studentsOf;
    }
    
    @RequestMapping(value = "/create/{username}/{name}", method = RequestMethod.POST)
    @ApiMethod(description = "Create an teacher and save it to the database")
    public List<TeacherData> create(@ApiPathParam(name = "username") @PathVariable String username, @ApiPathParam(name = "name") @PathVariable String name){
        
    	teacherService.addTeacher(username,name);
        
        return teacherService.getAllTeachers();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public List<TeacherData> remove(@ApiPathParam(name = "id") @PathVariable long id){
        teacherService.removeTeacher(id);

        return teacherService.getAllTeachers();
    }
}
