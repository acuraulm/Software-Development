package acu.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import acu.project1.business.service.CourseService;
import acu.project1.business.service.GradeService;
import acu.project1.business.service.GroupaService;
import acu.project1.business.service.SpecializationService;
import acu.project1.business.service.StudentService;
import acu.project1.business.service.TeacherService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.GradeData;
import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.SpecializationData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.StudentDataBuilder;
import acu.project1.business.transfer.TeacherData;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	@Autowired
    private TeacherService teacherService;
	@Autowired
    private CourseService courseService;
	@Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GroupaService groupaService;
    @Autowired
    private SpecializationService specializationService;
 /*  
    @Autowired
    public DatabaseSeeder(
    		TeacherService teacherService,CourseService courseService,StudentService studentService){
      
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.studentService = studentService;
    }
*/
    @Override
    public void run(String... strings) throws Exception {
      	System.out.println("------------------------------------teachers");
        for(TeacherData admin : teacherService.getAllTeachers())
        	System.out.println(admin.toString());
        System.out.println("------------------------------------courses");
        for(CourseData course : courseService.getAllCourses())
        	System.out.println(course.toString());
        System.out.println("------------------------------------students");
        for(StudentData student : studentService.getAllStudents())
        	System.out.println(student.toString());
        System.out.println("------------------------------------grades");
        for(GradeData grade : gradeService.getAllGrades())
        	System.out.println(grade.toString());
        System.out.println("------------------------------------groupas");
        for(GroupaData groupa : groupaService.getAllGroupas())
        	System.out.println(groupa.toString());
        System.out.println("------------------------------------specializations");
        for(SpecializationData specialization : specializationService.getAllSpecializations())
        	System.out.println(specialization.toString());
    }
}
