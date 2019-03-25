package acu.project1.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import acu.project1.business.service.GroupaService;
import acu.project1.business.service.SpecializationService;
import acu.project1.business.service.StudentService;
import acu.project1.business.service.TeacherService;
import acu.project1.business.transfer.GroupaData;
import acu.project1.business.transfer.SpecializationData;
import acu.project1.business.transfer.StudentData;
import acu.project1.business.transfer.TeacherData;
import acu.project1.persistence.repositories.StudentRepository;
import acu.project1.persistence.repositories.TeacherRepository;

import java.util.List;
import acu.project1.business.validators.*;
@RestController
@RequestMapping(value = "/index")
@Api(
        name = "Index API",
        description = "Provides a list of indexs",
        stage = ApiStage.RC)
public class IndexController {
	@Autowired
     SpecializationService specializationService;
	@Autowired
	 GroupaService groupaService;
	@Autowired
	TeacherRepository teacherService;
	@Autowired
	StudentRepository studentService;
	
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel indexs from the database")
    public List<SpecializationData> getAll(){
        return specializationService.getAllSpecializations();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public List<GroupaData> getGroupas(@ApiPathParam(name = "id") @PathVariable long id){
    	return specializationService.getAllSpecializationGroupas(id);
    }
    @RequestMapping(value = "/student/{username}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public boolean loginStud(@ApiPathParam(name = "username") @PathVariable String username){
    	Validator<String> validator = new StudentUsernameValidator();
		if (validator.validate(username))
			if(studentService.findByUsername(username) != null) {
	    		System.out.println("ITS A SUTDDEENT");
	    		return true;
	    	}
    	return false;
    	
    }
    @RequestMapping(value = "/teacher/{username}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public boolean loginTeacher(@ApiPathParam(name = "username") @PathVariable String username){

    	Validator<String> validator = new StudentUsernameValidator();
		if (validator.validate(username))
			if(teacherService.findByUsername(username) != null) {
	    		System.out.println("ITS A TEACHER");
	    		return true;
	    	}
    	return false;
    	
    }
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public List<StudentData> getStudents(@ApiPathParam(name = "id") @PathVariable long id){
    	return groupaService.getAllGroupaStudents(id);
    }
    
}
