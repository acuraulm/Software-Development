package com.example.messagingstompwebsocket.controllers;

import java.util.Set;

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
import com.example.messagingstompwebsocket.business.services.BookService;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;

@RestController
@RequestMapping(value = "/test")
@Api(
        name = "Thoughts API",
        description = "Provides a list of thoughts",
        stage = ApiStage.RC)
public class TestController {

	/*
	@Autowired
	BookProgressService bookProgressService;

	@Autowired
	ReviewService reviewService;
	*/
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ApiMethod(description = "Stores a book in the database")
    public UserDTO signUp(@RequestBody UserDTO userDTO){
	
		UserDTO returnDTO = null;
		System.out.println("Signing up.");
		
		if(userDTO != null)
			returnDTO = userService.signUp(userDTO);
		
    	return returnDTO;
    
    }
	
	@RequestMapping(value = "/findAllBooks", method = RequestMethod.GET)
    @ApiMethod(description = "Retrieves the books from the database")
    public Set<BookDTO> findAllBooks(){
		
		System.out.println("Retreiving books list.");
		
    	return bookService.findAllBooks();
    }
	
	@RequestMapping(value = "/publishBook", method = RequestMethod.POST)
    @ApiMethod(description = "Stores a book in the database")
    public BookDTO publishBook(@RequestBody BookDTO bookDTO){
	
		BookDTO returnDTO = null;
		System.out.println("Publishing book.");
		
		if(bookDTO != null)
			returnDTO = bookService.publishBook(bookDTO);
		
    	return returnDTO;
    }
	
}
