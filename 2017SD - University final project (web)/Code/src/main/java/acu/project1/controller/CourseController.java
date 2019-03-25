package acu.project1.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import acu.project1.business.service.CourseService;
import acu.project1.business.transfer.CourseData;
import acu.project1.business.transfer.StudentData;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
@Api(
        name = "Course API",
        description = "Provides a list of courses",
        stage = ApiStage.RC)
public class CourseController {

    private CourseService courseService;
   
    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all courses from the database")
    public List<CourseData> getAll(){
        return courseService.getAllCourses();
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get all students from the course")
    public List<StudentData> getAllStudentsOfCourse(@ApiPathParam(name = "id") @PathVariable long id){
        return courseService.getAllCourseStudents(id);
    }
    
    @RequestMapping(value = "/enroll/{id1}/{id2}", method = RequestMethod.GET)
    @ApiMethod(description = "Get all students from the course")
    public List<StudentData> getAllStudentsOfCourse(@ApiPathParam(name = "id1") @PathVariable long id1, @ApiPathParam(name = "id2") @PathVariable long id2){
        courseService.addStudentToCourse(id2, id1);
        return courseService.getAllCourseStudents(id1);
    }
    
    @RequestMapping(value = "/create/{name}/", method = RequestMethod.POST)
    @ApiMethod(description = "Create a course and save it to the database")
    public List<CourseData> create(@ApiPathParam(name = "name") @PathVariable String name){
        courseService.addCourse(name);
        return courseService.getAllCourses();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Remove the course with the provided ID from the database")
    public List<CourseData> remove(@ApiPathParam(name = "id") @PathVariable long id){
        courseService.removeCourse(id);

        return courseService.getAllCourses();
    }
}
