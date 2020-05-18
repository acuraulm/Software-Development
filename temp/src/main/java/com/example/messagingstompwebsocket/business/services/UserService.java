package com.example.messagingstompwebsocket.business.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.messagingstompwebsocket.business.serviceInterfaces.IDataTransferService;
import com.example.messagingstompwebsocket.business.serviceInterfaces.IUserService;
import com.example.messagingstompwebsocket.business.transfer.AuthorDTO;
import com.example.messagingstompwebsocket.business.transfer.BookDTO;
import com.example.messagingstompwebsocket.business.transfer.ReaderDTO;
import com.example.messagingstompwebsocket.business.transfer.UserDTO;
import com.example.messagingstompwebsocket.persistence.entities.Book;
import com.example.messagingstompwebsocket.persistence.entities.User;
import com.example.messagingstompwebsocket.persistence.entities.UserRole;
import com.example.messagingstompwebsocket.persistence.repositories.BookRepository;
import com.example.messagingstompwebsocket.persistence.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	IDataTransferService dataTransferService;

	@Override
	public UserDTO login(String username, String password) {
		
		UserDTO returnDTO = null;
		User user = userRepository.findByUsername(username);
		

		if(user != null) {
			if(user.getPassword().contentEquals(password)){
				if(user.getUserRole().equals(UserRole.AUTHOR))
				{
					returnDTO = dataTransferService.getAuthorDTO(user);
				}
				else
				{
					returnDTO = dataTransferService.getReaderDTO(user);
				}
			}
				
		}
		
		return returnDTO;
	}
	

	@Override
	public UserDTO signUp(UserDTO userDTO) {
		UserDTO returnDTO = null;
		
		User user = dataTransferService.getUser(userDTO);
		
		if(userRepository.save(user) != null)
			returnDTO = userDTO;
		
		return returnDTO;
	}


	@Override
	public AuthorDTO findAuthorDTOById(long id) {
		AuthorDTO returnDTO = null;
		Optional<User> optionalUser = userRepository.findById(id);
		User user = optionalUser.get();
		
		if(user != null) {
			dataTransferService.getAuthorDTO(user);
		}
		return returnDTO;
	}

	@Override
	public ReaderDTO findReaderDTObyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findByUsername(String username) {
		
		UserDTO returnDTO = null;
		
		User user = userRepository.findByUsername(username);
		if(user != null) {
			returnDTO = dataTransferService.getUserDTO(user);
		}
		else
		{
			System.out.println("User not found");
		}
		return returnDTO;
	}


}
