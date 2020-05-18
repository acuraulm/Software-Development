package com.example.messagingstompwebsocket.controllers;

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

import com.example.messagingstompwebsocket.business.services.UserService;
import com.example.messagingstompwebsocket.business.transfer.UserDTO;

@RestController
@RequestMapping(value = "/login")
@Api(
        name = "Thoughts API",
        description = "Provides a list of thoughts",
        stage = ApiStage.RC)
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public UserDTO loginTeacher(@ApiPathParam(name = "username") @PathVariable String username){
		
		System.out.println("Login get: " + username + '\n');
		
		UserDTO returnDTO = userService.findByUsername(username);
		
    	return returnDTO;
    	
    }

	@RequestMapping(value = "/{username}", method = RequestMethod.POST)
    @ApiMethod(description = "Orders the thoughts by the number of followers")
    public UserDTO loginStudent(@ApiPathParam(name = "username") @PathVariable String username, @RequestBody String password){
		
		System.out.println("Login post: " + username + ','+ password + '\n');

		UserDTO returnDTO = new UserDTO();
		returnDTO.setName("Blabla");
		
    	return returnDTO;
    }
	
}
