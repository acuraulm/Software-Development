package acu.assignment2.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import acu.assignment2.business.service.AdministratorService;
import acu.assignment2.business.transfer.AdministratorData;
import acu.assignment2.business.transfer.CourseData;
import acu.assignment2.persistence.entities.Administrator;
import acu.assignment2.persistence.entities.Student;
import acu.assignment2.persistence.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/administrator")
@Api(
        name = "Administrator API",
        description = "Provides a list of administrators",
        stage = ApiStage.RC)
public class AdministratorController {
	@Autowired
     AdministratorService administratorService;
 
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel administrators from the database")
    public List<AdministratorData> getAll(){
        return administratorService.getAllAdministrators();
    }
    

    @RequestMapping(value = "/create/{username}/{name}", method = RequestMethod.POST)
    @ApiMethod(description = "Create an administrator and save it to the database")
    public List<AdministratorData> create(@ApiPathParam(name = "username") @PathVariable String username, @ApiPathParam(name = "name") @PathVariable String name){
        
    	administratorService.addAdministrator(username,name);
        
        return administratorService.getAllAdministrators();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Remove the hotel administrator with the provided ID from the database")
    public List<AdministratorData> remove(@ApiPathParam(name = "id") @PathVariable long id){
        administratorService.removeAdministrator(id);

        return administratorService.getAllAdministrators();
    }
}
