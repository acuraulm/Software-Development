package com.example.messagingstompwebsocket.business.serviceInterfaces;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.messagingstompwebsocket.business.transfer.AuthorDTO;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;
import com.example.messagingstompwebsocket.business.transfer.ReaderDTO;
import com.example.messagingstompwebsocket.business.transfer.UserDTO;

public interface IUserService {

	UserDTO login(String username, String password);
	UserDTO signUp(UserDTO userDTO);
	AuthorDTO findAuthorDTOById(long id);
	ReaderDTO findReaderDTObyId(long id);
	UserDTO findByUsername(String username);	
	
}
