package acu.project1.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import acu.project1.business.service.CourseService;
import acu.project1.business.service.GradeService;
import acu.project1.business.service.StudentService;
import acu.project1.business.service.TeacherService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.TeacherData;
import acu.project1.persistence.entities.Course;
import acu.project1.persistence.entities.Student;
import acu.project1.persistence.repositories.GradeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping(value = "/student")
@Api(
        name = "Student API",
        description = "Provides a list of students",
        stage = ApiStage.RC)
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;
    private TeacherService teacherService;
    @Autowired
    GradeService gradeService;
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, TeacherService teacherService){
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

/*    @RequestMapping(value = "/coursesOfStudent", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel students from the database")
    public Map<CourseData, String> getCoursesOfStudent(){
    	Map<CourseData, String> map = new HashMap<CourseData, String>();
    	List<CourseData> courses = studentService.findOneStudent(1).getCourses();
    	List<GradeData> grades = studentService.findOneStudent(1).getGrades();
    	for(CourseData course : courses) {
    		for(GradeData grade : grades) {
    			if(grade.getCourse_id() == (int)course.getId()) {
    				map.put(course,grade.getValue() + "");
    			}
    		}
    	}
    	return map;

    }*/
    @RequestMapping(value = "/coursesOfStudent", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel students from the database")
    public List<Returneaza> getCoursesOfStudent(){
    	List<Returneaza> returneaza = new ArrayList<>();
    	List<CourseData> courses = studentService.findOneStudent(1).getCourses();
    	List<GradeData> grades = studentService.findOneStudent(1).getGrades();
    	for(CourseData course : courses) {
    		returneaza.add(new Returneaza(course, 0));
    		for(Returneaza returne : returneaza) {
    			for(GradeData grade : grades) {
        			if(grade.getCourse_id() == (int)returne.course.getId()) {
        				returne.grade = grade.getValue();
        			}
        		}
    		}
    		
    	}
    	return returneaza;

    }
    public class Returneaza{
    	
    	@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((course == null) ? 0 : course.hashCode());
			result = prime * result + grade;
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
			Returneaza other = (Returneaza) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (course == null) {
				if (other.course != null)
					return false;
			} else if (!course.equals(other.course))
				return false;
			if (grade != other.grade)
				return false;
			return true;
		}

		public CourseData course;
    	public int grade;
    	
    	public Returneaza(CourseData course, int grade) {
    		this.course = course;
    		this.grade = grade;
    	}

		private StudentController getOuterType() {
			return StudentController.this;
		}
    }

    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    @ApiMethod(description = "Get all admins from the database")
    public List<CourseData> getAllCourses(){
        List<CourseData> courses = new ArrayList<>();
        System.out.println(courses.toString());
        List<CourseData> coursesOfStudent = studentService.findOneStudent(1).getCourses();
        System.out.println("ISHFSAFSAHFJSAKFSAFFJSASAFSAFSAFSAFSAFASFSAFSAFAS");
        System.out.println(coursesOfStudent.toString());
        for(CourseData course1 : courseService.getAllCourses()) {
        	for(CourseData course2 : coursesOfStudent) {
        		if(!course1.getName().equals(course2.getName()) && !courses.contains(course1))
        			courses.add(course1);
        	}
        }
        List<CourseData> coursesRet = new ArrayList<>();
        for(CourseData course1 : courses) {
        	for(CourseData course2 : coursesOfStudent) {
        		if(!course1.getName().equals(course2.getName()) && !courses.contains(course1))
        			coursesRet.add(course1);
        	}
        }
        System.out.println("ISHFSAFSAHFJSAKFSAFFJSASAFSAFSAFSAFSAFASFSAFSAFAS");
        System.out.println(coursesRet.toString());
        return courses;

    }    
    @RequestMapping(value = "/enroll/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    List<Returneaza> enrollInCourse(@ApiPathParam(name = "id") @PathVariable long id){
    	courseService.addStudentToCourse(1,id);
    	List<Returneaza> returneaza = new ArrayList<>();
    	List<CourseData> courses = studentService.findOneStudent(1).getCourses();
    	List<GradeData> grades = studentService.findOneStudent(1).getGrades();
    	for(CourseData course : courses) {
    		returneaza.add(new Returneaza(course, 0));
    		for(Returneaza returne : returneaza) {
    			for(GradeData grade : grades) {
        			if(grade.getCourse_id() == (int)returne.course.getId()) {
        				returne.grade = grade.getValue();
        			}
        		}
    		}
    		
    	}
    	return returneaza;
    }
    @Autowired
    GradeRepository gradeRepository;
    
    @RequestMapping(value = "/unenroll/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    List<Returneaza> unenrollFromCourse(@ApiPathParam(name = "id") @PathVariable long id){
    	courseService.removeStudentFromCourse(1,id);
    	List<Returneaza> returneaza = new ArrayList<>();
    	List<CourseData> courses = studentService.findOneStudent(1).getCourses();
    	List<GradeData> grades = studentService.findOneStudent(1).getGrades();
    	for(CourseData course : courses) {
    		returneaza.add(new Returneaza(course, 0));
    		for(Returneaza returne : returneaza) {
    			for(GradeData grade : grades) {
        			if(grade.getCourse_id() == (int)returne.course.getId()) {
        				returne.grade = grade.getValue();
        			}
        		}
    		}
    		
    	}
    	return returneaza;
    }
    
    @RequestMapping(value = "/grade/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    int getGrade(@ApiPathParam(name = "id") @PathVariable long id){
    	List<GradeData> grades = studentService.findOneStudent(1).getGrades();
    	int gradee = 0;
    	for(GradeData grade : grades) {
    		if (grade.getCourse_id() == id)
    			gradee = grade.getValue();
    	}
    	return gradee;
    }
   
}
