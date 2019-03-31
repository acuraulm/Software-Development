package acuraulm.ThoughtsFinder.presentation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acuraulm.ThoughtsFinder.business.service.AppuserService;
import acuraulm.ThoughtsFinder.business.service.SectionService;
import acuraulm.ThoughtsFinder.business.service.ThoughtService;
import acuraulm.ThoughtsFinder.business.transfer.AppuserDTO;
import acuraulm.ThoughtsFinder.business.transfer.SectionDTO;
import acuraulm.ThoughtsFinder.business.transfer.ThoughtDTO;

@RestController
@RequestMapping(value = "/login")
@Api(
        name = "Thoughts API",
        description = "Provides a list of thoughts",
        stage = ApiStage.RC)
public class LoginController {

	@Autowired
	AppuserService appuserService;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.POST)
    @ApiMethod(description = "Orders the thoughts by the number of followers")
    public AppuserDTO verifyLogin(@ApiPathParam(name = "username") @PathVariable String username, @RequestBody String password){
		
		System.out.println(username + password);

    	return appuserService.verifyLogin(username, password);
    }
 
	
	
}
