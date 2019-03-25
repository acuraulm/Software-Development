package acu.assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import acu.assignment2.business.service.AdministratorService;
import acu.assignment2.business.service.CourseService;
import acu.assignment2.business.service.StudentService;
import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.business.transfer.StudentData;
import acu.assignment2.business.transfer.StudentDataBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private AdministratorService administratorService;
    private CourseService courseService;
    private StudentService studentService;
    
    @Autowired
    public DatabaseSeeder(
    		AdministratorService administratorService,CourseService courseService,StudentService studentService){
      
        this.administratorService = administratorService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<AdministratorData> administrators = new ArrayList<>();
        List<StudentData> students = new ArrayList<>();
        List<CourseData> courses = new ArrayList<>();
        
        System.out.println("\n ~~~~~~~~~~~~~ Creating students ~~~~~~~~~~~~~");
      //  studentService.
        studentService.addStudent("acuraulm", "Acu Raul");
        studentService.addStudent("scn", "Sirbu Carina");
        studentService.addStudent("adi_kanguru2007","Timis Adrian");
        studentService.addStudent("deyutza", "Ionutas Andreea");
        
        
        System.out.println("\n ~~~~~~~~~~~~~ Creating administrators ~~~~~~~~~~~~~");
      //  administratorService.addAdministrator("Blabla5", "B L A N A O");
        administratorService.addAdministrator("Default Administrator", "Default Administrator");
        administratorService.addAdministrator("popoct", "Pop Octavian");
        administratorService.addAdministrator("joldik", "Marius Joldos");
        administratorService.addAdministrator("altcineva", "Profesor DeCalitate");
        administratorService.addAdministrator("ent", "Eneia Todoran");
        
        System.out.println("\n ~~~~~~~~~~~~~ Creating courses ~~~~~~~~~~~~~");
        courseService.addCourse("Software Engineering");
        courseService.addCourse("Software Design");
        courseService.addCourse("Functional Programming");
        courseService.addCourse("Object Oriented Programming");
        
        System.out.println("\n ~~~~~~~~~~~~~ Course Administration ~~~~~~~~~~~~~");
      //  administratorService.addCourseToAdmin(course, administratorId);
        administratorService.addCourseToAdmin("Software Engineering", 5);
        administratorService.addCourseToAdmin("Software Design", 4);
        administratorService.addCourseToAdmin("Functional Programming", 2);
        administratorService.addCourseToAdmin("Object Oriented Programming", 3);
        administratorService.addCourseToAdmin("Curs in plus",1);
        administratorService.addCourseToAdmin("Curs in plus 2",1);
        administratorService.addCourseToAdmin("Curs in plus 3",1);

    System.out.println("\n ~~~~~~~~~~~~~ Enrollment ~~~~~~~~~~~~~");
        courseService.addStudentToCourse(1, 1);
        courseService.addStudentToCourse(1, 2);
        courseService.addStudentToCourse(2, 1);
      	System.out.println("------------------------------------admins");
        for(AdministratorData admin : administratorService.getAllAdministrators())
        	System.out.println(admin.toString());
        System.out.println("------------------------------------courses");
        for(CourseData course : courseService.getAllCourses())
        	System.out.println(course.toString());
        System.out.println("------------------------------------students");
        for(StudentData student : studentService.getAllStudents())
        	System.out.println(student.toString());
        
        StudentData studentBuilt = new StudentDataBuilder()
								                .setId((long)555)
								                .setName("Built name")
								        		.setUsername("builtusername")
								                .build();
        studentService.addStudent(studentBuilt.getUsername(), studentBuilt.getName());
        
    }
}
