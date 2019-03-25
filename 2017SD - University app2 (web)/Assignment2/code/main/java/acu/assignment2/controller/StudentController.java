package acu.assignment2.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import acu.assignment2.persistence.entities.Course;
import acu.assignment2.persistence.entities.Student;
import acu.assignment2.business.service.AdministratorService;
import acu.assignment2.business.service.CourseService;
import acu.assignment2.business.service.StudentService;
import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.business.transfer.StudentData;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
@Api(
        name = "Student API",
        description = "Provides a list of students",
        stage = ApiStage.RC)
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;
    private AdministratorService administratorService;
    
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, AdministratorService administratorService){
        this.studentService = studentService;
        this.courseService = courseService;
        this.administratorService = administratorService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel students from the database")
    public List<StudentData> getAll(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/all/admins", method = RequestMethod.GET)
    @ApiMethod(description = "Get all admins from the database")
    public List<AdministratorData> getAllAdmins(){
        return administratorService.getAllAdministrators();
    }    
    
    @RequestMapping(value = "/create/{username}/{name}", method = RequestMethod.POST)
    @ApiMethod(description = "Create a student and save it to the database")
    public List<StudentData> create(@ApiPathParam(name = "username") @PathVariable String username, @ApiPathParam(name = "name") @PathVariable String name){
        studentService.addStudent(username, name);
        return studentService.getAllStudents();
    }
    @RequestMapping(value = "/edit/{id}/{username}/{name}", method = RequestMethod.POST)
    @ApiMethod(description = "Edit a student and save it to the database")
    public List<StudentData> edit(@ApiPathParam(name = "id") @PathVariable long id, @ApiPathParam(name = "username") @PathVariable String username, @ApiPathParam(name = "name") @PathVariable String name){

        studentService.updateStudent(id, username, name);
        
        return studentService.getAllStudents();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Remove the hotel student with the provided ID from the database")
    public List<StudentData> remove(@ApiPathParam(name = "id") @PathVariable long id){
        studentService.removeStudent(id);

        return studentService.getAllStudents();
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Display administrator`s courses")
    public List<CourseData> getCourses(@ApiPathParam(name = "id") @PathVariable long id){
         return courseService.getAllAdministratorCourses(id);
    }
}
