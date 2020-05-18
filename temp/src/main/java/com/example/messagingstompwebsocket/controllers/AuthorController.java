package com.example.messagingstompwebsocket.controllers;

import java.util.Set;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.messagingstompwebsocket.business.services.UserService;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;

@RestController
@RequestMapping(value = "/author")
@Api(
        name = "Thoughts API",
        description = "Provides a list of thoughts",
        stage = ApiStage.RC)
public class AuthorController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/writtenBooks/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Remove the hotel teacher with the provided ID from the database")
    public Set<BookDTO> getWrittenBooks(@ApiPathParam(name = "id") @PathVariable long id){
		
		System.out.println("getWrittenBooks get: " + id + '\n');
		
		
    	return userService.findAuthorDTOById(id).getWrittenBooks();
    	
    }
}
